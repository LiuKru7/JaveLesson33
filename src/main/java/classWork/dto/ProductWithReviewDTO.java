package classWork.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithReviewDTO extends ProductDTO {
    private List<ProductReviewsDTO> reviews;

    public ProductWithReviewDTO(ProductDTO product) {
        super(product.getProductId(), product.getProductName(), product.getDescription(),
                product.getPrice(), product.getQuantityInStock(),
                product.getCategory(), product.getCreatedAt());
        this.reviews = new ArrayList<>();
    }
}
