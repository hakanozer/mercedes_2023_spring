package com.works;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.models.Product;
import com.works.restcontrollers.DummyRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
public class CustomerRestMockTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void customerRegister() throws Exception {
        String url = "/user/register";
        Product product = new Product();
        product.setTitle("TV");
        String sendObj = objectMapper.writeValueAsString(product);
        System.out.println( sendObj );
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sendObj)
        ).andReturn();
        Assertions.assertTrue( mvcResult.getResponse().getStatus() == 200 );
    }

}
