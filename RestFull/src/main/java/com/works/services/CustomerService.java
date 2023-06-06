package com.works.services;

import com.works.entities.Customer;
import com.works.entities.dto.CustomerLoginDto;
import com.works.repositories.CustomerRepository;
import com.works.utils.Message;
import com.works.utils.Util;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity register(Customer customer ) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customer.getEmail());
        if ( optionalCustomer.isPresent() ) {
            return Util.responseFalse( Message.uniqueEmail, HttpStatus.BAD_REQUEST );
        }else {
            customerRepository.save(customer);
            return Util.responseTrue(customer);
        }
    }


    public ResponseEntity login( CustomerLoginDto customerLoginDto ) {
        Customer customer = modelMapper.map(customerLoginDto, Customer.class);
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

    public ResponseEntity get( String stCid ) {
        try {
            Long cid = Long.parseLong(stCid);
            Optional<Customer> optionalCustomer = customerRepository.findById(cid);
            if (optionalCustomer.isPresent()) {
                return Util.responseTrue(optionalCustomer.get());
            }
            return Util.responseFalse(Message.getCid, HttpStatus.BAD_REQUEST);
        }catch (Exception ex) {
            return Util.responseFalse(ex.getStackTrace()[0].getClassName() + " - " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    public ResponseEntity search( String q, String page ) {
        List<Customer> list = customerRepository.findByNameContainsOrEmailContainsAllIgnoreCase(q,q);
        return Util.responseTrue(list);
    }



}
