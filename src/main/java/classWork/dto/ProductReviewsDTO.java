package classWork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductReviewsDTO {
    private Integer reviewId;
    private Integer productId;
    private String reviewText;
    private Integer rating;
    private Date created_at;

    public ProductReviewsDTO(Integer productId, String reviewText, Integer rating) {
        this.productId = productId;
        this.reviewText = reviewText;
        this.rating = rating;
    }
}
