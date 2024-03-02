package com.kislun.testtask.service;

import com.kislun.testtask.exception.UserAlreadyExistException;
import com.kislun.testtask.model.auth.AuthBody;
import com.kislun.testtask.model.user.User;
import com.kislun.testtask.repo.UserRepository;
import com.kislun.testtask.security.EncryptionService;
import com.kislun.testtask.security.JWTService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;

    public UserService(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public User registerUser(AuthBody authBody) throws UserAlreadyExistException {
        if (userRepository.findByUsernameIgnoreCase(authBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        User user = new User();
        user.setUsername(authBody.getUsername());
        user.setPassword(encryptionService.encryptPassword(authBody.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(AuthBody authBody) {
        Optional<User> opUser = userRepository.findByUsernameIgnoreCase(authBody.getUsername());
        if (opUser.isPresent()) {
            User user = opUser.get();
            if (encryptionService.checkPassword(authBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
