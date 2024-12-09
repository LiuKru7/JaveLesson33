package classWork.service;

import classWork.dto.ProductDTO;
import classWork.dto.ProductReviewsDTO;
import classWork.dto.ProductWithReviewDTO;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    public List<ProductDTO> getAllProductsByCategory(List<ProductDTO> products, String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }
    public List<ProductDTO> getProductsBelowPrice(List<ProductDTO> products, double price) {
        return products.stream()
                .filter(p->p.getPrice()<price)
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
                .mapToDouble(product->product.getPrice()* product.getQuantityInStock())
                .sum();
    }

//    public List<ProductWithReviewDTO> s (List<ProductDTO> products, List<ProductReviewsDTO> reviews) {
//        List<ProductWithReviewDTO> productsWithReviews = new ArrayList<>();
//        for (ProductDTO product : products) {
//            ProductWithReviewDTO productWithReview = new ProductWithReviewDTO(product);
//            List<ProductReviewsDTO> matchingReviews = new ArrayList<>();
//            for (ProductReviewsDTO review : reviews) {
//                if (product.getProductId().equals(review.getProductId())) {
//                    matchingReviews.add(review);
//                }
//            }
//            productWithReview.setReviews(matchingReviews);
//            productsWithReviews.add(productWithReview);
//        }
//        return productsWithReviews;
//    }
//    public List<ProductWithReviewDTO> s(List<ProductDTO> products, List<ProductReviewsDTO> reviews) {
//        return products.stream()
//                .map(product -> {
//                    ProductWithReviewDTO productWithReview = new ProductWithReviewDTO(product);
//                    List<ProductReviewsDTO> matchingReviews = reviews.stream()
//                            .filter(review -> product.getProductId().equals(review.getProductId()))
//                            .collect(Collectors.toList());
//                    productWithReview.setReviews(matchingReviews);
//                    return productWithReview;
//                })
//                .collect(Collectors.toList());
//    }

    public List<ProductWithReviewDTO> s(List<ProductDTO> products, List<ProductReviewsDTO> reviews) {
        Map<Integer, List<ProductReviewsDTO>> reviewsByProductId = reviews.stream()
                .collect(Collectors.groupingBy(ProductReviewsDTO::getProductId));

        return products.stream()
                .map(product -> {
                    ProductWithReviewDTO productWithReview = new ProductWithReviewDTO(product);
                    productWithReview.setReviews(
                            reviewsByProductId.getOrDefault(product.getProductId(), new ArrayList<>())
                    );
                    return productWithReview;
                })
                .collect(Collectors.toList());
    }



    //NEW
//
//    public List<ProductDTO> getProductsWithPositiveReviews
//            (List<ProductDTO> products, Map<Integer, List<ProductReviewsDTO>> reviews) {
//
//        List<ProductDTO> productsList = new ArrayList<>();
//
//        return productsList;
//
//    }
//
//    public List<ProductDTO> getTopRatedProducts(List<ProductDTO> products, Map<Integer, List<ProductReviewsDTO>> reviews, int topN) {
//
//        List<ProductDTO> productsList = new ArrayList<>();
//
//        return productsList;
//    }
//
//    public List<ProductReviewsDTO> getAllReviewTexts(List<ProductDTO> products, Map<Integer, List<ProductReviewsDTO>> reviews) {
//
//        List<ProductReviewsDTO> reviewsList = new ArrayList<>();
//
//        return reviewsList;
//    }
//
//    public Map<String, Double> calculateAverageRatingPerProduct(List<ProductDTO> products, Map<Integer, List<ProductReviewsDTO>>) {
//        Map<String, Double> ratingList = new HashMap<>();
//
//        return ratingList;
//    }
}