package com.example.springbootjdbc.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootjdbc.dao.entity.Customer;
import com.example.springbootjdbc.dao.repository.CustomerRepository;
import com.example.springbootjdbc.model.customer.RequestDeleteCustomer;
import com.example.springbootjdbc.model.customer.RequestSaveCustomer;
import com.example.springbootjdbc.model.customer.ResponseGenericForCustomer;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository repository;
    
    @Override
    public ResponseGenericForCustomer saveCustomer(RequestSaveCustomer request) {
        ResponseGenericForCustomer response = new ResponseGenericForCustomer();
        if(null == request){
            response.setSuccess(false);
            response.setMessage("The request must not be null!");
            return response;
        }
        if(null == request.getIdentityNo() || null == request.getName() || null == request.getSurname() || 
            null == request.getPhoneNumber() || null == request.getEmail()) {
                response.setMessage("Request must contain those: name, surname, identityNo, phoneNumber, and email, please make sure all is valid!");
                response.setSuccess(false);
                return response;
        }
        Customer customer = repository.findByIdentityNo(request.getIdentityNo());

        if(null != customer){
            response.setMessage("Customer already exists with the given identity number! " +
                                " Any change on name, surname, phone number, email, and password has been made successfully ");
        } else {
            customer = new Customer();
            customer.setIdentityNo(request.getIdentityNo());
            response.setMessage("The customer has been created successfully ");
        }
        
        customer.setName(request.getName());
        customer.setSurname(request.getSurname());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        customer = repository.save(customer);
        response.setSuccess(true);
        response.setMessage(response.getMessage() + "with the customer id: " + customer.getId());
        return response;
    }
    
    @Override
    public ResponseGenericForCustomer deleteCustomer(RequestDeleteCustomer request) {
        ResponseGenericForCustomer response = new ResponseGenericForCustomer();
        if(null == request){
            response.setSuccess(false);
            response.setMessage("The request must not be null!");
            return response;
        }
        if(null == request.getCustomerId() && null == request.getIdentityNo()){
            response.setMessage("Request must contain at least one of them: customerId or identityNo!");
            response.setSuccess(false);
            return response;
        }
        Customer customer = null;
        if(null != request.getCustomerId()){
            customer = repository.findById(request.getCustomerId()).orElse(null);
            //in case no customer found & the message will be overriden if it exists
            response.setMessage("No such customer found with customerId: " + request.getCustomerId());
        }
        if(null != request.getIdentityNo()){
            customer = repository.findByIdentityNo(request.getIdentityNo());
            //in case no customer found & the message will be overriden if it exists
            response.setMessage("No such customer found with identityNo: " + request.getIdentityNo());
        }
        if(null == customer){
            response.setSuccess(false);
        } else {
            repository.deleteById(customer.getId());
            response.setSuccess(true);
            response.setMessage("The customer is successfully deleted!");
        }
        return response;
    }
    
    @Override
    public Customer findById(Long customerId) {
        return repository.findById(customerId).orElse(null);
    }
    
    @Override
    public Customer findByIdentityNo(Long identityNo) {
        return repository.findByIdentityNo(identityNo);
    }
}