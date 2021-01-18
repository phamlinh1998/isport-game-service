package com.game.service.app.controller;

import com.game.service.app.response.LuckySpinRewardResponse;
import com.game.service.domain.entities.LuckySpinReward;
import com.game.service.domain.services.LuckySpinRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/games/luckyspin")
public class GameController {
    @Autowired
    private LuckySpinRewardService luckySpinRewardService;

    @GetMapping("configs")
    public List<LuckySpinRewardResponse> getReward(){
        return luckySpinRewardService.getReward();
    }

    @GetMapping("spin")
    public LuckySpinReward getLuckySpinReward(@RequestHeader(required = false) Integer userId){
        return luckySpinRewardService.getLuckySpinReward(userId);
    }

}
