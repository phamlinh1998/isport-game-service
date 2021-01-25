package com.game.service.domain.services;

import com.game.service.domain.entities.DatabaseSequence;
import com.game.service.domain.repositories.ConfigRepository;
import com.game.service.domain.repositories.GiftRepository;
import com.game.service.domain.repositories.LuckySpinGiftRepository;
import com.game.service.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Objects;

public class BaseService {
  @Autowired
  protected LuckySpinGiftRepository luckySpinGiftRepository;

  @Autowired protected GiftRepository giftRepository;

  @Autowired protected ConfigRepository configRepository;

  @Autowired protected UserRepository userRepository;

  @Autowired protected MongoOperations mongoOperations;

  public Integer generateSequence(String seqName) {
    DatabaseSequence counter = mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
        new Update().inc("seq",1), FindAndModifyOptions.options().returnNew(true).upsert(true),
        DatabaseSequence.class);
    return !Objects.isNull(counter) ? counter.getSeq() : 1;
  }

}
