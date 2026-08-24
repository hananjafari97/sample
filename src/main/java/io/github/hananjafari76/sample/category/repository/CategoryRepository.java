package io.github.hananjafari76.sample.category.repository;

import io.github.hananjafari76.sample.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findById(int id);
}
