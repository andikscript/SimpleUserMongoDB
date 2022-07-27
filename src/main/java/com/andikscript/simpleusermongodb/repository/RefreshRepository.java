package com.andikscript.simpleusermongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository extends MongoRepository<RefreshRepository, String> {
}
