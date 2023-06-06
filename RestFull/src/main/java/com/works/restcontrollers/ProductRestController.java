package com.works.restcontrollers;

import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {

    final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity list() {
        return productService.proCatJoin();
    }

    @GetMapping("/list/{cid}")
    public ResponseEntity listCatID(@PathVariable Long cid) {
        return productService.proCatJoinID(cid);
    }

}
