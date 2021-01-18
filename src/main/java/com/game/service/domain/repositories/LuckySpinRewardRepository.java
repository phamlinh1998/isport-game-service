package com.game.service.domain.repositories;

import com.game.service.domain.entities.LuckySpinReward;
import com.game.service.domain.entities.type.LuckySpinState;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LuckySpinRewardRepository extends MongoRepository<LuckySpinReward, Integer> {

    LuckySpinReward findLuckySpinRewardById(Integer id);

//    List<LuckySpinReward> findAllByOrderByCreatedAtDesc();

    List<LuckySpinReward> findLuckySpinRewardByState(LuckySpinState state);
}
