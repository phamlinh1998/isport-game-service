package com.game.service.domain.services;

import com.game.service.app.response.LuckySpinRewardResponse;
import com.game.service.domain.entities.LuckySpinReward;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public LuckySpinRewardResponse toLuckySpinRewardResponse (LuckySpinReward luckySpinReward){
        LuckySpinRewardResponse response = new LuckySpinRewardResponse();

        response.setId(luckySpinReward.getId());
        response.setIcon(luckySpinReward.getIcon());
        return response;
    }
}
