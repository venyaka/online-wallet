package com.veniamin.onlinewallet.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/*
TEST URL POST https://testoauth.homebank.kz/epay2/oauth2/token
PROD URL POST https://epay-oauth.homebank.kz/oauth2/token
Body: form-data

grant_type: 	"client_credentials"
scope: 			" webapi usermanagement email_send verification statement statistics payment"
client_id: 		"test"
client_secret: 	"yF587AV9Ms94qN2QShFzVR3vFnWkhjbAK3sG"
invoiceID: 		"000000001"
amount: 		100
currency: 		"KZT"
terminal: 		"67e34d63-102f-4bd1-898e-370781d0074d"
postLink:       ""
failurePostLink: ""
*/
@Data
@AllArgsConstructor
public class GetAuthModel {

    @NotNull
    private String grant_type;

    @NotNull
    private String scope;

    @NotNull
    private String client_id;

    @NotNull
    private String client_secret;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String invoiceID;

    @NotNull
    private String amount;

    @NotNull
    private String currency;

    @NotNull
    private String terminal;

    @NotNull
    private String postLink;

    @NotNull
    private String failurePostLink;
}
