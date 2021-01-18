package com.game.service.domain.services;

import com.game.service.app.response.LuckySpinRewardResponse;
import com.game.service.domain.entities.Config;
import com.game.service.domain.entities.LuckySpinReward;
import com.game.service.domain.entities.User;
import com.game.service.domain.exceptions.ResourceNotFoundException;
import com.game.service.domain.repositories.ConfigRepository;
import com.game.service.domain.repositories.LuckySpinRewardRepository;
import com.game.service.domain.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Log4j2
public class LuckySpinRewardService {
    @Autowired private ModelMapper modelMapper;

    @Autowired
    private LuckySpinRewardRepository luckySpinRewardRepository;

    @Autowired private ConfigRepository configRepository;

    @Autowired private UserRepository userRepository;

    public List<LuckySpinRewardResponse> getReward(){
        List<LuckySpinRewardResponse> luckySpinRewardResponses = new ArrayList<>();

        Config config = configRepository.findConfigByKey("minigame_luckyspin_config");
        if(config == null)
            throw new ResourceNotFoundException();
        String configValue = config.getValue().substring(1,config.getValue().length()-1);
        String[] listString = configValue.split(",");

        List<Integer> listId = Stream.of(listString).map(Integer::parseInt).collect(Collectors.toList());
        for(int luckySpinId : listId){
            LuckySpinReward luckySpinReward = luckySpinRewardRepository.findLuckySpinRewardById(luckySpinId);
            if(luckySpinReward == null)
                throw new ResourceNotFoundException();
            LuckySpinRewardResponse response = modelMapper.toLuckySpinRewardResponse(luckySpinReward);
            luckySpinRewardResponses.add(response);
        }

//        List<LuckySpinReward> luckySpinRewards = luckySpinRewardRepository.findLuckySpinRewardByState(LuckySpinState.ACTIVE);
//
//        for (LuckySpinReward luckySpinReward : luckySpinRewards){
//            LuckySpinRewardResponse response = new LuckySpinRewardResponse();
//
//            response.setId(luckySpinReward.getId());
//            response.setIcon(luckySpinReward.getIcon());
//
//            luckySpinRewardResponses.add(response);
//        }

        return luckySpinRewardResponses;
    }


//    public String getReward(){
//        Config config = configRepository.findConfigByKey("minigame_luckyspin_config");
//        if(config == null)
//            throw new ResourceNotFoundException();
//        return config.getValue();
//    }

    public LuckySpinReward getLuckySpinReward(Integer userId){

//        User user = userRepository.findUserById(userId);
//        if(user == null)
//            throw new ResourceNotFoundException();
//
//        if(user.getLuckySpin()==0)
//            throw new ResourceNotFoundException();

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
        for (int luckySpinId : listId){
            LuckySpinReward luckySpinReward = luckySpinRewardRepository.findLuckySpinRewardById(luckySpinId);
            if(luckySpinReward == null)
                throw new ResourceNotFoundException();

            random.add(luckySpinReward.getRate(),luckySpinReward);
        }

        LuckySpinReward luckySpinReward = random.next();
//        int spin = user.getLuckySpin() -1;
//        if(luckySpinReward.getId() ==86){
//            spin ++;
//            user.setLuckySpin(spin);
//            userRepository.save(user);
//        }
        return luckySpinReward;
    }

}
