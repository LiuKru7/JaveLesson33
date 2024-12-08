package classWork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    private Integer productId;
    private String productName;
    private String description;
    private Double price;
    private Integer quantityInStock;
    private String category;
    private String createdAt;

    public ProductDTO(String product_name, String description, Double price, Integer quantity_in_stock, String category, String created_at) {
        this.productName = product_name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantity_in_stock;
        this.category = category;
        this.createdAt = created_at;
    }
}
