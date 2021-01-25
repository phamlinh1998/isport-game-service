package com.game.service.app.controller;

import com.game.service.app.response.LuckySpinGiftResponse;
import com.game.service.domain.entities.data.LuckySpinWalletConfig;
import com.game.service.domain.services.LuckySpinGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("v1/games/luckyspin")
public class GameController {
    @Autowired
    private LuckySpinGiftService luckySpinGiftService;

    @GetMapping("configs")
    public List<LuckySpinGiftResponse> getGift(){
        return luckySpinGiftService.getGift();
    }

    @GetMapping("spin")
    public LuckySpinGiftResponse getLuckySpinGift() throws IOException {
        return luckySpinGiftService.getLuckySpinGift();
    }

}
