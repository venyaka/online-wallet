package com.veniamin.onlinewallet.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class InvoiceModel {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String invoiceID;

    @NotNull
    @Size(min = 3, message = "Client name should be at least 3 characters long")
    private String client_name;

    @NotNull
    @Email(message = "Please provide a valid email")
    private String client_email;

    @NotNull
    @DecimalMin(value = "75.0", message = "amount should be at least 75")
    private BigDecimal amount;

    @NotNull
    @Min(value = 0, message = "Discount percentage should be at least 0%")
    @Max(value = 100, message = "Discount percentage should not be more than 99.99%")
    private Double discount;

    @URL
    @NotNull
    private String back_url;

    @URL
    @NotNull
    private String webhook_url;

    @NotNull
    private String comment;
}