package com.works.restcontrollers;

import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {

    final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "pid") String sortSt
    ) {
        return productService.proCatJoin(page, sortSt);
    }

    @GetMapping("/list/{cid}")
    public ResponseEntity listCatID(@PathVariable Long cid) {
        return productService.proCatJoinID(cid);
    }

}
