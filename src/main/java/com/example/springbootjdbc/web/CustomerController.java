package com.example.springbootjdbc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootjdbc.dao.entity.Customer;
import com.example.springbootjdbc.model.customer.RequestDeleteCustomer;
import com.example.springbootjdbc.model.customer.RequestSaveCustomer;
import com.example.springbootjdbc.model.customer.ResponseGenericForCustomer;
import com.example.springbootjdbc.service.customer.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseGenericForCustomer saveCustomer(@RequestBody RequestSaveCustomer request) {
        return customerService.saveCustomer(request);
    }

    @PostMapping("/delete")
    public ResponseGenericForCustomer deleteCustomer(@RequestBody RequestDeleteCustomer request) {
        return customerService.deleteCustomer(request);
    }

    @GetMapping("/{customerId}")
    public Customer findById(@PathVariable Long customerId) {
        return customerService.findById(customerId);
    }

    @GetMapping("/identityNo/{identityNo}")
    public Customer findByIdentityNo(@PathVariable Long identityNo) {
        return customerService.findByIdentityNo(identityNo);
    }
}
