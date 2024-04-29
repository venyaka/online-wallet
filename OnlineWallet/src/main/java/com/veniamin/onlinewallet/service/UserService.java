package com.veniamin.onlinewallet.service;

import com.veniamin.onlinewallet.dto.PhoneCodeDto;
import com.veniamin.onlinewallet.entity.User;
import com.veniamin.onlinewallet.repository.UserRepository;

import com.veniamin.onlinewallet.responce.JwtResponse;
import com.veniamin.onlinewallet.security.jwt.JwtUtils;
import com.veniamin.onlinewallet.validation.PhoneValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public boolean signIn(String phone) {
        PhoneValidator phoneValidator = new PhoneValidator();
        if (phoneValidator.isValidPhoneNumber(phone)) {
            User userFromDB = userRepository.findByPhone(phone);
            User user = userFromDB == null ? new User() : userFromDB;

            user.setPhone(phone);

            SecureRandom random = new SecureRandom();
            String randomCode = Integer.toString(1000+random.nextInt(9999-1000));
            user.setVerificationCode(randomCode);
            user.setEnabled(false);
            userRepository.save(user);

            log.info("confirmation code: {}", randomCode);
            return true;
        }
        else {
            log.info("Uncorrected phone number: {}", phone);
        }
        return false;
    }

    public JwtResponse confirmSignIn(PhoneCodeDto dto) {
        User userFromDb = userRepository.findByPhone(dto.getPhone());
        User user = new User(userFromDb.getPhone(),
                userFromDb.getVerificationCode());

        String jwt = jwtUtils.generateJwtToken(dto.getPhone(), dto.getCode());

        log.info("Result: {}", jwt);
        JwtResponse token = new JwtResponse(jwt, user.getId());
        log.info("ResultToken: {}", token);
        return token;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public long getUserId(PhoneCodeDto dto) {
        String code = dto.getCode();
        String phone = dto.getPhone();
        User user = userRepository.findByPhone(dto.getPhone());

        if (user != null && Objects.equals(code, user.getVerificationCode()) && Objects.equals(phone, user.getPhone())) {
            return user.getId();
        }
        return 0;
    }

    public User saveUser(User user) {
        User userFromDB = userRepository.findByPhone(user.getPhone());

        if (userFromDB == null) {
            return userRepository.save(user);
        }
//        userRepository.save(user);
        return user;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


//    public UserDetails loadUserByPhone(String login) throws UsernameNotFoundException {
//        User u = userRepository.findByPhone(login);
//        if (Objects.isNull(u)) {
//            throw new UsernameNotFoundException(String.format("User %s is not found", login));
//        }
//        return new org.springframework.security.core.userdetails.User(u.getPhone(), u.getVerificationCode(), true, true, true, true, new HashSet<>());
//    }
}