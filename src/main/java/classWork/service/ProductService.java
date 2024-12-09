package classWork.service;

import classWork.dto.ProductDTO;
import classWork.dto.ProductReviewDTO;
import classWork.dto.ProductWithReviewDTO;
import org.w3c.dom.ls.LSOutput;

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

//    public List<ProductWithReviewDTO> getAllProductsWithReviews (List<ProductDTO> products, List<ProductReviewsDTO> reviews) {
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
//    public List<ProductWithReviewDTO> getAllProductsWithReviews (List<ProductDTO> products, List<ProductReviewsDTO> reviews) {
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

    public List<ProductWithReviewDTO> getAllProductsWithReviews(List<ProductDTO> products, List<ProductReviewDTO> reviews) {
        Map<Integer, List<ProductReviewDTO>> reviewsByProductId = reviews.stream()
                .collect(Collectors.groupingBy(ProductReviewDTO::getProductId));

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
//            (List<ProductDTO> products, Map<Integer, List<ProductReviewDTO>> reviews) {
//
//        for (List<ProductReviewDTO> value : reviews.values()) {
//
//        }
//
//        List<ProductReviewDTO> positiveReviews = reviews.values().stream()
//                .filter(p->p.getRating()>=4)
//                .toList();
//
//
//
//        List<ProductDTO> productsList = new ArrayList<>();
//        return productsList;
//    }
    public List<ProductDTO> getProductsWithPositiveReviews
    (List<ProductDTO> products, Map<Integer, List<ProductReviewDTO>> reviews) {
        return products.stream()
                .filter(product -> {
                    List<ProductReviewDTO> productReviews = reviews.get(product.getProductId());
                    if (productReviews == null) {
                        return false;
                    }
                    return productReviews.stream().anyMatch(review -> review.getRating() >= 4);
                })
                .toList();
    }




    public List<ProductDTO> getTopRatedProducts(List<ProductDTO> products, Map<Integer, List<ProductReviewDTO>> reviews, int topN) {
        List<ProductDTO> productsList = new ArrayList<>();

        return productsList;
    }

    public List<String> getAllReviewTexts(List<ProductDTO> products, Map<Integer, List<ProductReviewDTO>> reviews) {
        return reviews.values().stream()//take the map values
                .flatMap(Collection::stream)//
                .map(ProductReviewDTO::getReviewText)
                .toList();
    }

    public void calculateAverageRatingPerProduct(List<ProductDTO> products, Map<Integer, List<ProductReviewDTO>> reviews) {
        List<ProductReviewDTO> valueList = reviews.values().stream()
                .flatMap(Collection::stream)
                .toList();

//        valueList.stream()

    }
}