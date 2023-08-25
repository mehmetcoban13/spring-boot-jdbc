package com.example.springbootjdbc.service.customer;

import com.example.springbootjdbc.dao.entity.Customer;
import com.example.springbootjdbc.model.customer.RequestDeleteCustomer;
import com.example.springbootjdbc.model.customer.RequestSaveCustomer;
import com.example.springbootjdbc.model.customer.ResponseGenericForCustomer;

public interface CustomerService {
    ResponseGenericForCustomer saveCustomer(RequestSaveCustomer request);
    ResponseGenericForCustomer deleteCustomer(RequestDeleteCustomer request);
    Customer findById(Long customerId);
    Customer findByIdentityNo(Long identityNo);
}