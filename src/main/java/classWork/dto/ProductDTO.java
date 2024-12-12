package classWork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private List<ProductReviewDTO> reviews = new ArrayList<>();


    public ProductDTO(String product_name, String description, Double price, Integer quantity_in_stock, String category, String created_at) {
        this.productName = product_name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantity_in_stock;
        this.category = category;
        this.createdAt = created_at;
    }

    public ProductDTO(Integer productId, String productName, String description, Double price, Integer quantityInStock, String category, String createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.category = category;
        this.createdAt = createdAt;
    }

    public ProductDTO(ProductDTO product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantityInStock = product.getQuantityInStock();
        this.category = product.getCategory();
        this.createdAt = product.getCreatedAt();
        this.reviews = new ArrayList<>(product.getReviews());
    }

}
