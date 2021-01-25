package com.game.service.domain.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
public class Gift {
    @Transient
    public static final String SEQUENCE_NAME = "gift_sequence";

    @Id
    private Integer id;

    @Field(name = "gift_day")
    private LocalDate gift_day;

    @Field(name = "lucky_spin_gift_id")
    private Integer luckySpinGiftId;

    @Field(name = "quantity")
    private Integer quantity;

}
