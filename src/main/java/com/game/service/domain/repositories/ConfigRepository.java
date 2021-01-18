package com.game.service.domain.repositories;

import com.game.service.domain.entities.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<Config, String> {
    Config findConfigByKey(String key);

    void deleteByKey(String key);
}
