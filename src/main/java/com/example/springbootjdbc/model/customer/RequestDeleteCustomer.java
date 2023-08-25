package com.example.springbootjdbc.model.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDeleteCustomer {
    private Long customerId;
    private Long identityNo;
}