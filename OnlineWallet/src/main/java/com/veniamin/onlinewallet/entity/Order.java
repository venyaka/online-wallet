package com.veniamin.onlinewallet.entity;

import com.veniamin.onlinewallet.entity.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private Long amount;

    @NotNull
    private Date createdDate;

    @NotNull
    private String currency;

    private String data;

    private String description;

    @NotNull
    private String invoiceID;

    @NotNull
    private String language;

    @NotNull
    private String phone;

    @NotNull
    private String reason;

    @NotNull
    private String reasonCode;

    @NotNull
    private String statusName;

    @NotNull
    @OneToOne
    private User sender;

    @NotNull
    @OneToOne
    private User receiver;
}