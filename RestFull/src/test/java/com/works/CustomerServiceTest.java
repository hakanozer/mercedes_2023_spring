package com.works;

import com.works.entities.Customer;
import com.works.entities.dto.CustomerLoginDto;
import com.works.services.CustomerService;
import com.works.utils.REnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void customerSave() {
        Customer customer = new Customer();
        customer.setName("Erkan Bilsin");
        customer.setEmail(UUID.randomUUID().toString()+"@mail.com");
        customer.setPassword("12345");
        ResponseEntity cus = customerService.register(customer);
        if ( cus.getStatusCodeValue() == 200 ) {
            Map map = (LinkedHashMap<REnum, Object>) cus.getBody();
            Customer cs = (Customer) map.get(REnum.result);
            System.out.println( cs );
            Assertions.assertTrue( cs.getCid() > 0, "Customer Save Fail" );
        }else {
            Assertions.assertTrue(false, cus.getBody().toString());
        }

    }



    @Test
    public void customerLogin() {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto();
        customerLoginDto.setEmail("emircan@salih.com");
        customerLoginDto.setPassword("12345");
        ResponseEntity response = customerService.login(customerLoginDto);
        if ( response.getStatusCodeValue() == 200 ) {
            Map map = (LinkedHashMap<REnum, Object>) response.getBody();
            Customer customer = (Customer) map.get(REnum.result);
            System.out.println( customer );
        }else {
            Assertions.assertTrue(false, response.getBody().toString());
        }
    }
}
