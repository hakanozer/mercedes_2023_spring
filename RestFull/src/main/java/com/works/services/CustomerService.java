package com.works.services;

import com.works.entities.Customer;
import com.works.entities.dto.CustomerDto;
import com.works.entities.dto.CustomerLoginDto;
import com.works.projections.ICustomer;
import com.works.repositories.CustomerRepository;
import com.works.utils.Message;
import com.works.utils.Util;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final ModelMapper modelMapper = new ModelMapper();
    final CacheManager cacheManager;

    public ResponseEntity register(Customer customer ) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customer.getEmail());
        if ( optionalCustomer.isPresent() ) {
            return Util.responseFalse( Message.uniqueEmail, HttpStatus.BAD_REQUEST );
        }else {
            customerRepository.save(customer);
            cacheManager.getCache("user").clear();
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


    @Cacheable(value = "user")
    public ResponseEntity allCustomer() {
        List<Customer> allCustomers = customerRepository.findAll();
        // List<ICustomer> allCustomers = customerRepository.allCustomer();
        CustomerDto[] arr = modelMapper.map(allCustomers, CustomerDto[].class);
        return Util.responseTrue(arr);
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


    // Delete
    public ResponseEntity delete( Long cid ) {
        try {
            customerRepository.deleteById(cid);
            return Util.responseTrue(cid);
        }catch (Exception ex) {
            return Util.responseFalse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update
    public ResponseEntity update( Customer customer ) {
        if ( customer.getCid() != null ) {
           Optional<Customer> optionalCustomer = customerRepository.findById( customer.getCid() );
           if (optionalCustomer.isPresent() ) {
               customerRepository.saveAndFlush(customer);
               return Util.responseTrue(customer);
           }
        }
        return Util.responseFalse("Not Found Customer ID :" + customer.getCid(), HttpStatus.BAD_REQUEST);
    }

    @Scheduled(fixedDelay = 5000, timeUnit = TimeUnit.MILLISECONDS)
    public void clearCache() {
        System.out.println("clearCache Call");
        cacheManager.getCache("user").clear();
    }



}
