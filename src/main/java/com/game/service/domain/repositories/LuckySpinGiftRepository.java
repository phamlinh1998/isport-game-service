package com.game.service.domain.repositories;

import com.game.service.domain.entities.LuckySpinGift;
import com.game.service.domain.entities.type.LuckySpinGiftFrequency;
import com.game.service.domain.entities.type.LuckySpinGiftType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface LuckySpinGiftRepository extends MongoRepository<LuckySpinGift,Integer> {

    LuckySpinGift findLuckySpinGiftById(int id);

    LuckySpinGift findLuckySpinGiftByType(LuckySpinGiftType type);

    List<LuckySpinGift> findLuckySpinGiftByFrequency(LuckySpinGiftFrequency frequency);
}
