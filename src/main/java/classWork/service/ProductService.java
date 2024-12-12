package classWork.service;

import classWork.dto.ProductDTO;
import classWork.dto.ProductReviewDTO;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ProductService {
    public List<ProductDTO> getAllProductsByCategory(List<ProductDTO> products, String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public List<ProductDTO> getProductsBelowPrice(List<ProductDTO> products, double price) {
        return products.stream()
                .filter(p -> p.getPrice() < price)
                .toList();
    }

    public List<ProductDTO> sortProductsByPrice(List<ProductDTO> products, boolean ascending) {
        return products.stream()
                .sorted(ascending
                        ? Comparator.comparingDouble(ProductDTO::getPrice)
                        : Comparator.comparingDouble(ProductDTO::getPrice).reversed())
                .toList();
    }

    public ProductDTO getMostExpensiveProduct(List<ProductDTO> products) {
        return products.stream()
                .max(Comparator.comparingDouble(ProductDTO::getPrice))
                .orElse(null);
    }

    public double calculateTotalStockValue(List<ProductDTO> products) {
        return products.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantityInStock())
                .sum();
    }

    public List<ProductDTO> getAllProductsWithReviews (List<ProductDTO> products, List<ProductReviewDTO> reviews) {
        return products.stream()
                .map(product -> {
                    ProductDTO productWithReview = new ProductDTO(product);
                    List<ProductReviewDTO> matchingReviews = reviews.stream()
                            .filter(review -> product.getProductId().equals(review.getProductId()))
                            .collect(Collectors.toList());
                    productWithReview.setReviews(matchingReviews);
                    return productWithReview;
                })
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsWithPositiveReviews(List<ProductDTO> products) {
        return products.stream()
                .filter(p->p.getReviews().stream().
                        anyMatch(r->r.getRating() >= 4))
                .toList();
    }

    public List<ProductDTO> getProductsWithPositiveReviews(
            List<ProductDTO> products,
            Map<Integer, List<ProductReviewDTO>> reviews
    ) {
        return products.stream()
                .filter(product -> {
                    List<ProductReviewDTO> productReviews = reviews.get(product.getProductId());
                    return productReviews != null && productReviews.stream()
                            .anyMatch(review -> review.getRating() >= 4);
                })
                .collect(Collectors.toList());
    }
    public List<ProductDTO> getTopRatedProducts(List<ProductDTO> products, int topN) {
        return products.stream()
                .sorted((p1, p2) -> {
                    double avg1 = p1.getReviews().stream()
                            .mapToInt(ProductReviewDTO::getRating)
                            .average()
                            .orElse(0.0);

                    double avg2 = p2.getReviews().stream()
                            .mapToInt(ProductReviewDTO::getRating)
                            .average()
                            .orElse(0.0);

                    return Double.compare(avg2, avg1);
                })
                .limit(topN)
                .toList();
    }



    public List<ProductDTO> getTopRatedProducts(
            List<ProductDTO> products, Map<Integer, List<ProductReviewDTO>> reviews, int topN) {
        return products.stream()
                .sorted((p1, p2) -> {
                    double avgRating1 = calculateAverageRating(p1.getProductId(), reviews);
                    double avgRating2 = calculateAverageRating(p2.getProductId(), reviews);
                    return Double.compare(avgRating2, avgRating1); // Sort in descending order
                })
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<String> getAllReviewTexts(List<ProductDTO> products){
        return products.stream()
                .flatMap(p->p.getReviews().stream()
                        .map(ProductReviewDTO::getReviewText))
                .toList();
    }

    public List<String> getAllReviewTexts(
            List<ProductDTO> products, Map<Integer, List<ProductReviewDTO>> reviews) {
        return products.stream()
                .flatMap(product -> {
                    List<ProductReviewDTO> productReviews = reviews.get(product.getProductId());
                    return productReviews != null ? productReviews.stream()
                            .map(ProductReviewDTO::getReviewText) : Stream.empty();
                })
                .collect(Collectors.toList());
    }

    public Map<Integer, Double> calculateAverageRatingPerProduct(List<ProductDTO> products) {
        return products.stream()
                .collect(Collectors.toMap(
                        ProductDTO::getProductId, // ProductDTO nes kazkodel netinka paveldejusi klase.
                        product -> product.getReviews().stream()
                                .mapToInt(ProductReviewDTO::getRating)
                                .average()
                                .orElse(0.0)
                ));
    }

    public Map<Integer, Double> calculateAverageRatingPerProduct(
            List<ProductDTO> products, Map<Integer, List<ProductReviewDTO>> reviews) {
        return products.stream()
                .collect(Collectors.toMap(
                        ProductDTO::getProductId,
                        product -> calculateAverageRating(product.getProductId(), reviews)
                ));
    }

    private double calculateAverageRating(int productId, Map<Integer, List<ProductReviewDTO>> reviews) {
        List<ProductReviewDTO> productReviews = reviews.get(productId);
        if (productReviews == null || productReviews.isEmpty()) {
            return 0.0;
        }
        return productReviews.stream()
                .mapToInt(ProductReviewDTO::getRating)
                .average()
                .orElse(0.0);
    }
}

