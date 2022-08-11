package com.andikscript.simpleusermongodb.service.verify;

import com.andikscript.simpleusermongodb.model.mongo.Verify;
import com.andikscript.simpleusermongodb.repository.mongo.VerifyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerifyImpl implements VerifyService {

    private final VerifyRepository verifyRepository;

    public VerifyImpl(VerifyRepository verifyRepository) {
        this.verifyRepository = verifyRepository;
    }

    @Override
    public void createVerify(Verify verify) {
        verifyRepository.save(verify);
    }

    @Override
    public Optional<Verify> findByCode(String code) {
        return verifyRepository.findByVerifyCode(code);
    }

    @Override
    public void deleteByCode(String code) {
        verifyRepository.deleteByVerifyCode(code);
    }
}
