package io.github.hananjafari76.sample.product.service.impl;

import io.github.hananjafari76.sample.category.repository.CategoryRepository;
import io.github.hananjafari76.sample.model.Category;
import io.github.hananjafari76.sample.model.Product;
import io.github.hananjafari76.sample.product.io.ProductRequest;
import io.github.hananjafari76.sample.product.io.ProductResponse;
import io.github.hananjafari76.sample.product.repository.ProductRepository;
import io.github.hananjafari76.sample.product.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductResponse add(ProductRequest request) {
        Product newProduct = convertToEntity(request);
        newProduct = productRepository.save(newProduct);
        return convertToResponse(newProduct);
    }

    @Override
    @Transactional
    public List<ProductResponse> read() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product existingProduct  = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found" + id));
        existingProduct.setProductName(request.getProductName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setStock(request.getStock());

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("category not found"));
        existingProduct.setCategory(category);

        Product updatedProduct = productRepository.save(existingProduct);
        return convertToResponse(updatedProduct);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product existingProduct  = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found" + id));
        productRepository.delete(existingProduct);
    }

    private Product convertToEntity(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("category not found"));

        return Product.builder()
                .productName(request.getProductName())
                .price(request.getPrice())
                .description(request.getDescription())
                .stock(request.getStock())
                .category(category)
                .build();
    }

    private ProductResponse convertToResponse(Product newProduct) {
        return ProductResponse.builder()
                .id(newProduct.getId())
                .productName(newProduct.getProductName())
                .price(newProduct.getPrice())
                .description(newProduct.getDescription())
                .stock(newProduct.getStock())
                .categoryName(newProduct.getCategory().getName())
                .createdAt(newProduct.getCreatedAt())
                .build();
    }
}
