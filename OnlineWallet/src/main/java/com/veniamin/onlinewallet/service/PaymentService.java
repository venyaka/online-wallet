package com.veniamin.onlinewallet.service;

import com.veniamin.onlinewallet.config.EpayParams;
import com.veniamin.onlinewallet.dto.PhoneDto;
import com.veniamin.onlinewallet.dto.TokenDto;
import com.veniamin.onlinewallet.entity.Order;
import com.veniamin.onlinewallet.entity.User;
import com.veniamin.onlinewallet.model.GetAuthModel;
import com.veniamin.onlinewallet.model.InvoiceModel;
import com.veniamin.onlinewallet.repository.PaymentRepository;
import com.veniamin.onlinewallet.repository.UserRepository;
import com.veniamin.onlinewallet.security.jwt.JwtUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.Callback;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final UserRepository userRepository;
    private final EpayClient client;
    private final JwtUtils jwtUtils;
    private final PaymentRepository paymentRepository;

    public void getAuthToken() {
        var getAuthModel = new GetAuthModel(
                EpayParams.grant_type,
                EpayParams.scope,
                EpayParams.client_id,
                EpayParams.client_secret,
                "",
                "",
                EpayParams.currency,
                EpayParams.terminal,
                EpayParams.postLink,
                EpayParams.failurePostLink
        );

        //handle response after you get it as a call back
        client.getAuthToken(getAuthModel, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //in case of failure
                System.out.println("failure " + e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //in case of success
                System.out.println("Success " + response);
            }
        });
    }
    public long getBalance(TokenDto tokenDto) {
        String phone = jwtUtils.decodeToken(tokenDto.getToken());
        log.info("phone: {}", phone);

        ArrayList<User> expenses = paymentRepository.findAllBySender(userRepository.findByPhone(phone));

        log.info("expenses: {}", expenses);
        return 0;
    }
}