package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.utils.Message;
import com.works.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;

    public ResponseEntity register(Customer customer ) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customer.getEmail());
        if ( optionalCustomer.isPresent() ) {
            return Util.responseFalse( Message.uniqueEmail, HttpStatus.BAD_REQUEST );
        }else {
            customerRepository.save(customer);
            return Util.responseTrue(customer);
        }
    }


    public ResponseEntity login( Customer customer ) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(customer.getEmail(), customer.getPassword());
        if ( optionalCustomer.isPresent() ) {
            return Util.responseTrue( optionalCustomer.get() );
        }else {
            return Util.responseFalse(Message.emailOrPassword, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity allCustomer() {
        List<Customer> allCustomers = customerRepository.findAll();
        return Util.responseTrue(allCustomers);
    }



}
