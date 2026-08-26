package io.github.hananjafari76.sample.product.io;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank
    private String productName;
    private String description;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Long price;
    @Positive(message = "Stock must be greater than zero")
    private Long stock;

    private Long categoryId;

}
