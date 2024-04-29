package com.veniamin.onlinewallet.controllers;

import com.veniamin.onlinewallet.dto.TokenDto;
import com.veniamin.onlinewallet.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/pay/failure-transaction")
    public void deposit(@RequestBody ResponseBody response) {
        log.info("result: {}", response);
    }

    @PostMapping("pay/deposit")
    public void fail(@RequestBody ResponseBody response) {
        log.info("resultpays: {}", response);
    }

    @GetMapping("/getBalance")
    @PreAuthorize("#token == authorization")
    public ResponseEntity<?> getBalance(String token) {
        log.info("response: {}", token);

//        paymentService.getBalance(new TokenDto(token));
        return ResponseEntity.ok(token);
    }
}
