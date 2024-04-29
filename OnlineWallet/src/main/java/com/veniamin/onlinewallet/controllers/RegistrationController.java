package com.veniamin.onlinewallet.controllers;

import com.veniamin.onlinewallet.dto.*;
import com.veniamin.onlinewallet.entity.User;
import com.veniamin.onlinewallet.responce.JwtResponse;
import com.veniamin.onlinewallet.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RegistrationController {
    int EXP_TIME = 7 * 24 * 60 * 60;

    private final UserService userService;

    AuthenticationManager authenticationManager;

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/sign-in")
    public ResponseDto signIn(@RequestBody @Valid PhoneDto dto) {
        return ResponseDto.builder().ok(userService.signIn(dto.getPhone())).build();
    }

    @PostMapping("/confirm-sign-in")
    public ResponseEntity<?> confirmSignIn(@RequestBody @Valid PhoneCodeDto phoneCodeDto, HttpServletResponse response) {
        JwtResponse token = userService.confirmSignIn(phoneCodeDto);
//        JwtResponse jwtResponse = new JwtResponse(token.getToken(), null);
        log.info("token: {}", token.getToken());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/paydeposit")
    public void fail(@RequestBody ResponseBody response) {
        log.info("resultpays: {}", response);
    }
}