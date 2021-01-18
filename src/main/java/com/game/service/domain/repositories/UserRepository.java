package com.game.service.domain.repositories;


import com.game.service.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
    User findUserByUsername(String username);

    User findUserById(Integer id);
}

