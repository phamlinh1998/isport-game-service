package com.game.service.domain.services;

import com.game.service.app.response.LuckySpinGiftResponse;
import com.game.service.domain.entities.Config;
import com.game.service.domain.entities.Gift;
import com.game.service.domain.entities.LuckySpinGift;
import com.game.service.domain.entities.type.LuckySpinGiftFrequency;
import com.game.service.domain.entities.type.LuckySpinGiftType;
import com.game.service.domain.exceptions.ResourceNotFoundException;
import com.game.service.domain.utils.JsonParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@Log4j2
public class LuckySpinGiftService extends BaseService {

    public List<LuckySpinGiftResponse> getGift(){
        Config config = configRepository.findConfigByKey("lucky_spin_config ");

        if(config == null)
            throw new ResourceNotFoundException();
        try {
            List<LuckySpinGiftResponse> luckySpinGiftRespon = JsonParser.arrayList(config.getValue(),LuckySpinGiftResponse.class);
            return luckySpinGiftRespon;
        }catch (Exception e){
            return null;
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public LuckySpinGiftResponse getLuckySpinGift() throws IOException {

        int probabilityCount = 100;
        String min = "min";
        String max = "max";
        Integer tempInt = 0;
        Map<String,Map<String,Integer>> prizesMap = new HashMap<>();
        List<LuckySpinGift> luckySpinGiftList = luckySpinGiftRepository.findLuckySpinGiftByFrequency(LuckySpinGiftFrequency.DAY);

        Config config = configRepository.findConfigByKey("minigame_luckyspin_wallet");

        LuckySpinGift spinGift = luckySpinGiftRepository.findLuckySpinGiftByType(LuckySpinGiftType.LOST_TURN);

        //set odds LOST_TURN
        for (LuckySpinGift luckySpinGift : luckySpinGiftList) {
            Gift gift = giftRepository.findGiftByLuckySpinGiftId(luckySpinGift.getId());
            if(gift != null){
                if( !luckySpinGift.getType().equals(LuckySpinGiftType.LOST_TURN) && luckySpinGift.getMaxReward() == gift.getQuantity()){
                    spinGift.setOdds(spinGift.getOdds() + luckySpinGift.getOdds());
                }
            }

        }

        //tao mang random
        for (LuckySpinGift luckySpinGift : luckySpinGiftList) {
            Gift gift = giftRepository.findGiftByLuckySpinGiftId(luckySpinGift.getId());
            if(gift == null){
                gift = new Gift();
                gift.setQuantity(0);
            }

            Map<String,Integer> oddsMap = new HashMap<>();
            if(luckySpinGift.getType().equals(LuckySpinGiftType.LOST_TURN)){
                oddsMap.put(min,tempInt);
                tempInt = tempInt + luckySpinGift.getOdds();
                oddsMap.put(max,tempInt);
                prizesMap.put(luckySpinGift.getId().toString(),oddsMap);
            }
            else if(luckySpinGift.getMaxReward() > gift.getQuantity()){
                oddsMap.put(min,tempInt);
                tempInt = tempInt + luckySpinGift.getOdds();
                oddsMap.put(max,tempInt);
                prizesMap.put(luckySpinGift.getId().toString(),oddsMap);

            }else if(luckySpinGift.getType().equals(LuckySpinGiftType.DIAMON) && luckySpinGift.getDiamond() < Integer.parseInt(config.getValue())){

                oddsMap.put(min,tempInt);
                tempInt = tempInt + luckySpinGift.getOdds();
                oddsMap.put(max,tempInt);
                prizesMap.put(luckySpinGift.getId().toString(),oddsMap);
            }

        }

        //Random
        int index = (int) (Math.random() * probabilityCount);
        LuckySpinGift luckySpinGift = new LuckySpinGift();
        Set<String> prizesIds = prizesMap.keySet();

        for(String prizesId : prizesIds){
            Map<String,Integer> oddsMap = prizesMap.get(prizesId);
            Integer minNum = oddsMap.get(min);
            Integer maxNum = oddsMap.get(max);

            if(minNum <= index && maxNum > index){
                luckySpinGift = luckySpinGiftRepository.findLuckySpinGiftById(Integer.parseInt(prizesId));
                break;
            }
        }

        //set lai wallet diamon
        if(luckySpinGift.getType().equals(LuckySpinGiftType.DIAMON)){
            int valueWallet = Integer.parseInt(config.getValue());
            config.setValue(String.valueOf(valueWallet-luckySpinGift.getDiamond()));
            configRepository.save(config);
        }

        // tao bang gift
        Gift gift = giftRepository.findGiftByLuckySpinGiftId(luckySpinGift.getId());
        if(gift == null){
            Gift giftNew = new Gift();
            giftNew.setId(generateSequence(Gift.SEQUENCE_NAME));
            giftNew.setGift_day(LocalDate.now());
            giftNew.setLuckySpinGiftId(luckySpinGift.getId());
            giftNew.setQuantity(1);
            giftRepository.save(giftNew);
        }else{
            gift.setQuantity(gift.getQuantity()+1);
            giftRepository.save(gift);
        }

        LuckySpinGiftResponse luckySpinGiftResponse = new LuckySpinGiftResponse();

        luckySpinGiftResponse.setGiftId(luckySpinGift.getId());
        luckySpinGiftResponse.setLabel(luckySpinGift.getItemLabel());
        luckySpinGiftResponse.setImageUrl(luckySpinGift.getImageUrl());

        return luckySpinGiftResponse;
    }
}
