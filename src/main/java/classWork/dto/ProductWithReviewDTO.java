package classWork.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithReviewDTO extends ProductDTO {
    private List<ProductReviewDTO> reviews;

    public ProductWithReviewDTO(ProductDTO product) {
        super(product.getProductId(), product.getProductName(), product.getDescription(),
                product.getPrice(), product.getQuantityInStock(),
                product.getCategory(), product.getCreatedAt());
        this.reviews = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "ProductWithReviewDTO{" +
                "\n\tproductId=" + getProductId() +
                ", \n\tproductName='" + getProductName() + '\'' +
                ", \n\tdescription='" + getDescription() + '\'' +
                ", \n\tprice=" + getPrice() +
                ", \n\tquantityInStock=" + getQuantityInStock() +
                ", \n\tcategory='" + getCategory() + '\'' +
                ", \n\tcreatedAt=" + getCreatedAt() +
                ", \n\treviews=" + reviews +
                "\n}";
    }
}
