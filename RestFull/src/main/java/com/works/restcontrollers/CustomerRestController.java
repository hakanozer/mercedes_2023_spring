package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CustomerRestController {

    final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register( @RequestBody Customer customer ) {
        return customerService.register(customer);
    }

    @PostMapping("/login")
    public ResponseEntity login( @RequestBody Customer customer ) {
        return customerService.login(customer);
    }

    @GetMapping("/all")
    public ResponseEntity all() {
        return customerService.allCustomer();
    }

}
