package com.andikscript.simpleusermongodb.repository.mongo;

import com.andikscript.simpleusermongodb.model.mongo.Verify;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifyRepository extends MongoRepository<Verify, String> {

    Optional<Verify> findByVerifyCode(String code);

    void deleteByVerifyCode(String code);
}
