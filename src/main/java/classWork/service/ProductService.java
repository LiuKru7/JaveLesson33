package classWork.service;

import classWork.dto.ProductDTO;
import classWork.dto.ProductReviewsDTO;

import java.util.*;

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

    //NEW

    public List<ProductDTO> getProductsWithPositiveReviews
            (List<ProductDTO> products, Map<Integer, List<ProductReviewsDTO>> reviews) {

        List<ProductDTO> productsList = new ArrayList<>();

        return productsList;

    }

    public List<ProductDTO> getTopRatedProducts(List<ProductDTO> products, Map<Integer, List<ProductReviewsDTO>> reviews, int topN) {

        List<ProductDTO> productsList = new ArrayList<>();

        return productsList;
    }

    public List<ProductReviewsDTO> getAllReviewTexts(List<ProductDTO> products, Map<Integer, List<ProductReviewsDTO>> reviews) {

        List<ProductReviewsDTO> reviewsList = new ArrayList<>();

        return reviewsList;
    }

    public Map<String, Double> calculateAverageRatingPerProduct(List<ProductDTO> products, Map<Integer, List<ProductReviewsDTO>>) {
        Map<String, Double> ratingList = new HashMap<>();

        return ratingList;
    }











}