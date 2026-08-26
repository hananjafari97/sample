package io.github.hananjafari76.sample.product.io;

import io.github.hananjafari76.sample.model.Category;
import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {

    private Long id;
    private String productName;
    private String description;
    private Long price;
    private Long stock;
    private String categoryName;
    private Timestamp createdAt;
}
