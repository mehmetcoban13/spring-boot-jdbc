package com.example.springbootjdbc.model.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSaveCustomer {
    private String name;
    private String surname;
    private Long identityNo;
    private String password;
    private String email;
    private String phoneNumber;
}