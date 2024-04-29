package com.veniamin.onlinewallet.service;

import com.google.gson.Gson;
import com.veniamin.onlinewallet.config.EpayClientConfig;
import com.veniamin.onlinewallet.config.EpayParams;
import com.veniamin.onlinewallet.model.GetAuthModel;
import com.veniamin.onlinewallet.model.InvoiceModel;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Repository
@Validated
@RequiredArgsConstructor
public class EpayClient {
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final MediaType APPLICATION_JSON = MediaType.parse("application/json");
    public void getAuthToken(@Valid GetAuthModel getAuthModel, okhttp3.Callback responseCallback) {
        Gson gson = new Gson();
        String invoiceAsJson = gson.toJson(getAuthModel);
        RequestBody body = RequestBody.create(invoiceAsJson, APPLICATION_JSON);
        Request request = new Request.Builder()
                .url(EpayParams.TEST_URL)
//                .addHeader("X-Authorization", EpayParams.API_KEY)
                .post(body)
                .build();

        okHttpClient.newCall(request).enqueue(responseCallback);
    }

//    public void makePayment(@Valid InvoiceModel invoiceModel, okhttp3.Callback responseCallback) {
//        Gson gson = new Gson();
//        String invoiceAsJson = gson.toJson(invoiceModel);
//        RequestBody body = RequestBody.create(invoiceAsJson, APPLICATION_JSON);
//        Request request = new Request.Builder()
//                .url(epayClientConfig.getProperty(EpayParams.BASE_URL) + "/api/invoice")
//                .addHeader("X-Authorization", epayClientConfig.getProperty(EpayParams.API_KEY))
//                .post(body)
//                .build();
//
//        okHttpClient.newCall(request).enqueue(responseCallback);
//    }
//
//    public Boolean isSignatureValid(String signature, String responseData) {
//        String secretKey = epayClientConfig.getProperty(EpayParams.SECRET);
//        String responseHash = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, secretKey).hmacHex(responseData);
//        return signature.equals(responseHash);
//    }
}