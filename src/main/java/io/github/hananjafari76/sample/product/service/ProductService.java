package io.github.hananjafari76.sample.product.service;

import io.github.hananjafari76.sample.product.io.ProductRequest;
import io.github.hananjafari76.sample.product.io.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {

    ProductResponse add(@RequestBody ProductRequest request);

    List<ProductResponse> read();

    ProductResponse update(Long id, @Valid ProductRequest request);

    void delete(Long id);
}
