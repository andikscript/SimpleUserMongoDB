package com.andikscript.simpleusermongodb.security.refresh;

import com.andikscript.simpleusermongodb.model.mongo.RefreshToken;
import com.andikscript.simpleusermongodb.model.mongo.User;
import com.andikscript.simpleusermongodb.repository.mongo.RefreshRepository;
import com.andikscript.simpleusermongodb.repository.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@PropertySource("classpath:my.properties")
public class RefreshTokenService {

    @Value("${SimpleUserMongoDB.app.jwtRefreshExpiration}")
    private Long refreshTokenDuration;

    @Autowired
    private RefreshRepository refreshRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshRepository.findByToken(token);
    }

    public void deleteByUser(User user) {
        refreshRepository.deleteByIdUser(user);
    }

    public RefreshToken createRefreshToken(String id) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setIdUser(userRepository.findById(id).get());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiredDate(Instant.now().plusMillis(refreshTokenDuration));
        refreshToken = refreshRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpired(RefreshToken refreshToken) {
        if (refreshToken.getExpiredDate().compareTo(Instant.now()) < 0) {
            refreshRepository.delete(refreshToken);
        }

        return refreshToken;
    }
}
