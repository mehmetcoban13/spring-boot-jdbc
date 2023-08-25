package com.example.springbootjdbc.model.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseGenericForCustomer {
    private boolean isSuccess;
    private String message;
}