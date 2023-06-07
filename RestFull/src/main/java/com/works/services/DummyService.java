package com.works.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.models.Product;
import com.works.models.Products;
import com.works.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DummyService {

    final RestTemplate restTemplate;
    final ObjectMapper objectMapper;

    public ResponseEntity allProduct() {
        String url = "https://dummyjson.com/products";
        Products products = restTemplate.getForObject(url, Products.class);
        List<Product> ls = products.getProducts();
        for ( Product item : ls ) {
            item.setPrice( item.getPrice() * 2 );
        }
        return Util.responseTrue(ls);
    }

    public ResponseEntity save( Product product ) {
        String stObj = "";
        try {
            stObj = objectMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String url = "https://dummyjson.com/products/add";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(stObj, headers);

        ResponseEntity<Product> response = restTemplate.postForEntity(url, httpEntity, Product.class);
        return Util.responseTrue( response.getBody() );
    }



}
