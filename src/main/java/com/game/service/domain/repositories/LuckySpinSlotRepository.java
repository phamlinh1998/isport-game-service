package com.game.service.domain.repositories;

import com.game.service.domain.entities.LuckySpinSlot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LuckySpinSlotRepository extends MongoRepository<LuckySpinSlot, Integer> {
    LuckySpinSlot findLuckySpinSlotById(int id);

    List<LuckySpinSlot> findLuckySpinSlotByIdIn(List<Integer> ids);

    List<LuckySpinSlot> findLuckySpinSlotByOrderByPositionAsc();
}
