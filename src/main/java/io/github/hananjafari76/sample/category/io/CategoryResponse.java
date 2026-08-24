package io.github.hananjafari76.sample.category.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {

    private int id;
    private String name;
    private String description;
    private List<String> productName;
}
