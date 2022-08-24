package com.andikscript.simpleusermongodb.service.user;

import com.andikscript.simpleusermongodb.handling.*;
import com.andikscript.simpleusermongodb.model.mongo.RefreshToken;
import com.andikscript.simpleusermongodb.model.mongo.User;
import com.andikscript.simpleusermongodb.model.mongo.Verify;
import com.andikscript.simpleusermongodb.payload.JwtResponse;
import com.andikscript.simpleusermongodb.payload.RefreshTokenRequest;
import com.andikscript.simpleusermongodb.payload.RefreshTokenResponse;
import com.andikscript.simpleusermongodb.payload.UserPassRequest;
import com.andikscript.simpleusermongodb.model.mail.Email;
import com.andikscript.simpleusermongodb.repository.mongo.UserRepository;
import com.andikscript.simpleusermongodb.security.jwt.JwtUtils;
import com.andikscript.simpleusermongodb.security.refresh.RefreshTokenService;
import com.andikscript.simpleusermongodb.security.service.UserDetailsImpl;
import com.andikscript.simpleusermongodb.service.mail.EmailService;
import com.andikscript.simpleusermongodb.service.sms.SendMessageService;
import com.andikscript.simpleusermongodb.service.verify.VerifyService;
import com.andikscript.simpleusermongodb.util.RandomNumberWord;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final RefreshTokenService refreshTokenService;

    private final RandomNumberWord randomNumberWord;

    private final EmailService emailService;

    private final SendMessageService sendMessageService;

    private final VerifyService verifyService;

    public UserImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                    AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                    RefreshTokenService refreshTokenService, RandomNumberWord randomNumberWord,
                    EmailService emailService, SendMessageService sendMessageService,
                    VerifyService verifyService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.randomNumberWord = randomNumberWord;
        this.emailService = emailService;
        this.sendMessageService = sendMessageService;
        this.verifyService = verifyService;
    }

    @Override
    public void createUser(User user) throws UserAlready {
        if (userRepository.findByUsername(user.getUsername()).isPresent() ||
                userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlready();
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setConfirmed(randomNumberWord.random());
        userRepository.save(user);
        Email email = new Email();
        email.setReceived(user.getEmail());
        email.setSubject("User Confirmed");
        email.setMessage("click link below for confirmed : http://localhost:8080/api/email/" +
                user.getConfirmed() + "/confirmed");
        emailService.sendEmail(email);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void getVerify(UserPassRequest userPassRequest) throws UserNotConfirmed {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userPassRequest.getUsername(),
                        userPassRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (!userDetails.getConfirmed().equals("confirmed")) {
            throw new UserNotConfirmed();
        }
        String code = randomNumberWord.randomNumber();
        sendMessageService.sendMessage(userDetails.getPhone(), "Your verification : " + code);

        verifyService.createVerify(new Verify(userDetails.getId(), code));
    }

    @Override
    public JwtResponse authUser(String code) throws UserNotVerify {
        Optional<Verify> getVerify = verifyService.findByCode(code);

        if (!getVerify.isPresent()) {
            throw new UserNotVerify();
        }

        verifyService.deleteByCode(code);

        Optional<User> getUser = userRepository.findById(getVerify.get().getIdUser());

        String jwt = jwtUtils.generateJwtToken(getUser.get().getUsername());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(getUser.get().getId());
        return new JwtResponse(
                        jwt, refreshToken.getToken(), getUser.get().getUsername(), Arrays.asList(getUser.get().getRoles()));
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws RefreshTokenExpired {
        String request = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(request)
                .map(refreshTokenService::verifyExpired)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getIdUser().getUsername());
                    return new RefreshTokenResponse(token, request);
                })
                .orElseThrow(() -> new RefreshTokenExpired());
    }

    @Override
    public void findConfirmed(String confirmed) throws UserNotRegister{
        Optional<User> user = userRepository.findByConfirmed(confirmed);

        if (!user.isPresent()) {
            throw new UserNotRegister();
        }

        user.get().setConfirmed("confirmed");
        userRepository.save(user.get());
    }
}
