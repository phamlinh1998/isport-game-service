package com.game.service.domain.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    @Field(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @Field(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();

}
