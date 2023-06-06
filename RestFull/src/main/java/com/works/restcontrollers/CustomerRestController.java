package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import com.works.utils.Util;
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

    @GetMapping("/get/{stCid}")
    public ResponseEntity get( @PathVariable String stCid ) {
        return customerService.get(stCid);
    }

    @GetMapping("/search")
    public ResponseEntity search(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "0") String page
    ) {
        return customerService.search(q, page);
    }



}
