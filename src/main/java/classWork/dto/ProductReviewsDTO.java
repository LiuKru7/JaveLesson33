package classWork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductReviewsDTO {
    private Integer reviewId;
    private Integer productId;
    private String reviewText;
    private Integer rating;
    private LocalDate created_at;
}
