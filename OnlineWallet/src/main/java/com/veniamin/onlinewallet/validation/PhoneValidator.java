package com.veniamin.onlinewallet.validation;

public class PhoneValidator {
    public boolean isValidPhoneNumber(String phoneNumber) {
        boolean isValid = phoneNumber.matches("\\d{10}");
        System.out.println(phoneNumber+" : "+isValid);
        return isValid;
    }
}
