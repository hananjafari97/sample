package io.github.hananjafari76.sample.category.service.impl;

import io.github.hananjafari76.sample.category.io.CategoryRequest;
import io.github.hananjafari76.sample.category.io.CategoryResponse;
import io.github.hananjafari76.sample.category.repository.CategoryRepository;
import io.github.hananjafari76.sample.category.service.CategoryService;
import io.github.hananjafari76.sample.model.Category;
import io.github.hananjafari76.sample.model.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    @Transactional
    public List<CategoryResponse> read() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponse add(CategoryRequest request) {
        Category newCategory = convertToEntity(request);
        newCategory = categoryRepository.save(newCategory);
        return convertToResponse(newCategory);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("category not found : " + id));
        categoryRepository.delete(existingCategory);
    }

    @Override
    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("category not found : " + id));
        existingCategory.setName(request.getName());
        existingCategory.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(existingCategory);

        return convertToResponse(updatedCategory);
    }

    private CategoryResponse convertToResponse(Category newCategory) {
        return CategoryResponse.builder()
                .id(newCategory.getId())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .build();
    }

    private Category convertToEntity(CategoryRequest request) {
        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
}
