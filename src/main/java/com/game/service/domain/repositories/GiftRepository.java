package com.game.service.domain.repositories;

import com.game.service.domain.entities.Gift;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiftRepository extends MongoRepository<Gift,Integer> {
    Gift findGiftByLuckySpinGiftId(int giftId);
}
