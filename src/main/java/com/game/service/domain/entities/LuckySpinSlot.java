package com.game.service.domain.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "lucky_spin_slot")
public class LuckySpinSlot {
    @Transient
    public static final String SEQUENCE_NAME = "lucky_spin_slot_sequence";

    @Id
    private Integer id;

    @Field(name = "gift_id")
    private Integer giftId;

    @Field(name = "position")
    private Integer position;

}
