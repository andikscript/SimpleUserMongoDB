package com.andikscript.simpleusermongodb.repository;

import com.andikscript.simpleusermongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
