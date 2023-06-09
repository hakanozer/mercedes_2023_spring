package com.works.services;

import com.works.models.Product;
import com.works.models.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    final RestTemplate restTemplate;

    public List<Product> products() {
        String url = "https://dummyjson.com/products";
        Products products = restTemplate.getForObject(url, Products.class );
        return products.getProducts();
    }

}
