package com.game.service.domain.services;

import com.game.service.app.response.LuckySpinRewardResponse;
import com.game.service.domain.entities.Config;
import com.game.service.domain.entities.LuckySpinReward;
import com.game.service.domain.entities.type.LuckySpinState;
import com.game.service.domain.exceptions.ResourceNotFoundException;
import com.game.service.domain.repositories.ConfigRepository;
import com.game.service.domain.repositories.LuckySpinRewardRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Log4j2
public class LuckySpinRewardService {

    @Autowired
    private LuckySpinRewardRepository luckySpinRewardRepository;

    @Autowired private ConfigRepository configRepository;

    public List<LuckySpinRewardResponse> getReward(){
        List<LuckySpinRewardResponse> luckySpinRewardResponses = new ArrayList<>();
        List<LuckySpinReward> luckySpinRewards = luckySpinRewardRepository.findLuckySpinRewardByState(LuckySpinState.ACTIVE);

        for (LuckySpinReward luckySpinReward : luckySpinRewards){
            LuckySpinRewardResponse response = new LuckySpinRewardResponse();

            response.setId(luckySpinReward.getId());
            response.setIcon(luckySpinReward.getIcon());

            luckySpinRewardResponses.add(response);
        }

        return luckySpinRewardResponses;
    }
//    public String getReward(){
//        Config config = configRepository.findConfigByKey("minigame_luckyspin_config");
//        if(config == null)
//            throw new ResourceNotFoundException();
//        return config.getValue();
//    }

    public LuckySpinReward getLuckySpinReward(){
        Config config = configRepository.findConfigByKey("minigame_luckyspin_config");
        if(config == null)
            throw new ResourceNotFoundException();
        String configValue = config.getValue().substring(1,config.getValue().length()-1);
        String[] listString = configValue.split(",");

        List<Integer> listId = Stream.of(listString).map(Integer::parseInt).collect(Collectors.toList());

//        Random random = new Random();
//        int max = listId.size();
//        int min =1;
//        int id = listId.get(random.nextInt((max -min) +1 )) + min;
//        int id = listId.get(random.nextInt(listId.size()));
//        LuckySpinReward luckySpinReward = luckySpinRewardRepository.findLuckySpinRewardById(id);
//        if(luckySpinReward == null)
//            throw new ResourceNotFoundException();

        RandomCollection<LuckySpinReward> random = new RandomCollection<>();
        for (int luckySpinid : listId){
            LuckySpinReward luckySpinReward = luckySpinRewardRepository.findLuckySpinRewardById(luckySpinid);
            if(luckySpinReward == null)
                throw new ResourceNotFoundException();

            random.add(luckySpinReward.getRate(),luckySpinReward);
        }
        return random.next();
    }

    // tao ham random
    public class RandomCollection<E> {
        private final NavigableMap<Float, E> map = new TreeMap<Float, E>();
        private final Random random;
        private float total = 0;

        public RandomCollection() {
            this(new Random());
        }

        public RandomCollection(Random random) {
            this.random = random;
        }

        public RandomCollection<E> add(float percent, E result) {
            if (percent <= 0) return this;
            total += percent;
            map.put(total, result);
            return this;
        }

        public E next() {
            float value = random.nextFloat() * total;
            return map.higherEntry(value).getValue();
        }
    }
}
