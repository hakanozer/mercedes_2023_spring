package com.works;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JPATest {

    @Autowired
    CustomerRepository customerRepository;

    @Order(1)
    @Test
    public void customerSave() {
        Customer customer = new Customer();
        customer.setName("Erkan Bilsin");
        customer.setEmail("erkan@mail.com");
        customer.setPassword("12345");
        customerRepository.save(customer);
        Assertions.assertEquals(customer.getCid(), 1, "customer save fail");
    }

    @Order(2)
    @Test
    @Sql(scripts = "classpath:customer.sql")
    public void customerEmailControl() {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase("ali@mail.com");
        Assertions.assertTrue( optionalCustomer.isPresent(), "ali@mail.com not found" );
    }

    @Order(3)
    @Test
    @Sql(scripts = "classpath:customer.sql")
    public void customerAllTest() {
        List<Customer> ls = customerRepository.findAll();
        Assertions.assertTrue(ls.size() > 2, "Size Fail");
    }

    @Order(4)
    @Sql(scripts = "classpath:customer.sql")
    @ParameterizedTest
    @ValueSource(strings = {"ali@mail.com","veli@mail.com","mehmet@mail.com",""})
    public void paramsTest( String item ) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(item);
        Assertions.assertTrue(optionalCustomer.isPresent(), item +" Not Found");
    }

    @Order(5)
    @ParameterizedTest
    @Sql(scripts = "classpath:customer.sql")
    @CsvSource(value = { "ali@mail.com,12345", "veli@mail.com,12345", "ahmet@mail.com,12345" })
    public void paramsCsv( String email, String password ) {
       Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(email, password);
       Assertions.assertTrue( optionalCustomer.isPresent(), email + " User email or password fail" );
    }

    @Order(6)
    @RepeatedTest(10)
    @Sql(scripts = "classpath:customer.sql")
    public void repeatTest() {
        List<Customer> ls = customerRepository.findAll();
        Assertions.assertTrue(ls.size() > 2, "Size Fail");
    }

}
