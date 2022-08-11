package com.andikscript.simpleusermongodb.service.verify;

import com.andikscript.simpleusermongodb.model.mongo.Verify;

import java.util.Optional;

public interface VerifyService {

    void createVerify(Verify verify);

    Optional<Verify> findByCode(String code);

    void deleteByCode(String code);
}
