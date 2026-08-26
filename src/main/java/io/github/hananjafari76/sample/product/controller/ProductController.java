package io.github.hananjafari76.sample.product.controller;


import io.github.hananjafari76.sample.product.io.ProductRequest;
import io.github.hananjafari76.sample.product.io.ProductResponse;
import io.github.hananjafari76.sample.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService  productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse add(@RequestBody @Valid ProductRequest request) {
        return productService.add(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> fetchProducts(){
        return productService.read();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse update(@PathVariable Long id, @RequestBody @Valid ProductRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        try {
            productService.delete(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
