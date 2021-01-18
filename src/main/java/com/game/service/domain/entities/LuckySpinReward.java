package com.game.service.domain.entities;

import com.game.service.domain.entities.type.LuckySpinState;
import com.game.service.domain.entities.type.LuckySpinType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "lucky_spin_reward")
public class LuckySpinReward extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "lucky_spin_sequence";

    @Id
    private Integer id;

    @Field(name = "name")
    private String name;

    @Field(name = "type")
    private LuckySpinType type;

    @Field(name = "rewardLabel1")
    private String rewardLabel1;

    @Field(name = "rewardValue1")
    private String rewardValue1;

    @Field(name = "rewardLabel2")
    private String rewardLabel2;

    @Field(name = "rewardValue2")
    private String rewardValue2;

    @Field(name = "rate")
    private Float rate;

    @Field(name = "state")
    private LuckySpinState state;

    @Field(name = "icon")
    private String icon;
}


