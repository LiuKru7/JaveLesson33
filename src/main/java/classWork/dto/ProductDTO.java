package classWork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    private Integer product_id;
    private String product_name;
    private String description;
    private Double price;
    private Integer quantity_in_stock;
    private String category;
    private String created_at;

    public ProductDTO(String product_name, String description, Double price, Integer quantity_in_stock, String category, String created_at) {
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.quantity_in_stock = quantity_in_stock;
        this.category = category;
        this.created_at = created_at;
    }
}
