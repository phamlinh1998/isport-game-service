package com.game.service.domain.entities;

import com.game.service.domain.entities.type.LuckySpinGiftFrequency;
import com.game.service.domain.entities.type.LuckySpinGiftType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document(collection = "lucky_spin_gift")
public class LuckySpinGift extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "lucky_spin_gift_sequence";

    @Id
    private Integer id;

    @Field(name = "item_label")
    private String itemLabel;

    @Field(name = "item_value")
    private String itemValue;

    @Field(name = "diamond")
    private Integer diamond;

    @Field(name = "point")
    private Integer point;

    @Field(name = "image_url")
    private String imageUrl;

    @Field(name = "type")
    private LuckySpinGiftType type;

    @Field(name = "odds")
    private Integer odds;

    @Field(name = "max_reward")
    private Integer maxReward;

    @Field(name = "frequency")
    private LuckySpinGiftFrequency frequency;
}
