package com.game.service.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "config")
@NoArgsConstructor
public class Config {

    @Transient
    public static final String SEQUENCE_NAME = "config_sequence";

    @Id
    private Integer id;

    @Field(name = "key")
    private String key;

    @Field(name = "value")
    private String value;
}
