package com.game.service.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@Slf4j
public class Wallet {

    @Field(name = "balance")
    private Double balance = 0.0;
}
