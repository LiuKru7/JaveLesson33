package classWork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductReviewDTO {
    private Integer reviewId;
    private Integer productId;
    private String reviewText;
    private Integer rating;
    private Date created_at;

    public ProductReviewDTO(Integer productId, String reviewText, Integer rating) {
        this.productId = productId;
        this.reviewText = reviewText;
        this.rating = rating;
    }
}
