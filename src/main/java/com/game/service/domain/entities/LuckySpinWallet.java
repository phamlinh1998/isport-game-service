package com.game.service.domain.entities;

import org.springframework.data.mongodb.core.mapping.Field;

public class LuckySpinWallet {
    @Field(name = "wallet")
    private Long wallet = 0L;
}
