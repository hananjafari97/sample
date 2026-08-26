package io.github.hananjafari76.sample.category.service;

import io.github.hananjafari76.sample.category.io.CategoryRequest;
import io.github.hananjafari76.sample.category.io.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> read();

    CategoryResponse add(@RequestBody CategoryRequest request);

    void delete(Long id);

    CategoryResponse update(Long id, @Valid CategoryRequest request);
}
