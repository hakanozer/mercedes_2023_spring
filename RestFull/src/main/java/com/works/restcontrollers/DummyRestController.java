package com.works.restcontrollers;

import com.works.models.Product;
import com.works.services.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dummy")
@RequiredArgsConstructor
public class DummyRestController {

    final DummyService dummyService;

    @GetMapping("/products")
    public ResponseEntity products() {
       return dummyService.allProduct();
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product product) {
        return dummyService.save(product);
    }

}
