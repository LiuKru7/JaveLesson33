package classWork;

import classWork.dto.ProductDTO;
import classWork.dto.ProductReviewDTO;
import classWork.repository.ProductRepository;
import classWork.service.ProductService;
import classWork.util.ProductDataInitializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductMain {

    public static void main(String[] args) {
        List<ProductDTO> products = new ArrayList<>();
        List<ProductReviewDTO> reviews = new ArrayList<>();
        Map<Integer, List<ProductReviewDTO>> reviewsMap = new HashMap<>();
        List<ProductDTO> productsWithReviews = new ArrayList<>();

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService();
        ProductDataInitializer productDataInitializer = new ProductDataInitializer();


        productRepository.dropReviewsTable();
        productRepository.dropTable();

        productRepository.createTable();
        productRepository.createReviewsTable();


        productDataInitializer.generateDate(productRepository);

        productRepository.deleteProductById(5);

        products = productRepository.getAllProducts();
        reviews = productRepository.getAllReviews();
        reviewsMap = productRepository.getAllProductsWithReviews();
        productsWithReviews = productService.getAllProductsWithReviews(products, reviews);

        productRepository.getReviewsByProductId(5).forEach(System.out::println);


        System.out.println("Products by category");
        productService.getAllProductsByCategory(products, "berries").forEach(System.out::println);

        System.out.println("Product with below price");
        productService.getProductsBelowPrice(products, 0.9).forEach(System.out::println);

        System.out.println("PRODUCTS SORTED BY PRICE");
        productService.sortProductsByPrice(products, true).forEach(System.out::println);

        System.out.println("Most expensive product: " + productService.getMostExpensiveProduct(products));


        System.out.println(productService.calculateTotalStockValue(products));

        System.out.println("All Reviews with rating more than 4 or equal.");
        productService.getProductsWithPositiveReviews(productsWithReviews).forEach(System.out::println);
        productService.getProductsWithPositiveReviews(products, reviewsMap).forEach(System.out::println);

        System.out.println("TOP 5 products ");
        productService.getTopRatedProducts(productsWithReviews, reviewsMap, 1).forEach(System.out::println);

        System.out.println("Get all products reviews texts:");
        productService.getAllReviewTexts(productsWithReviews).forEach(System.out::println);
        productService.getAllReviewTexts(products, reviewsMap).forEach(System.out::println);

        System.out.println("Get all products rating average by product_id ");
        System.out.println(productService.calculateAverageRatingPerProduct(productsWithReviews));
        System.out.println(productService.calculateAverageRatingPerProduct(products, reviewsMap));

    }
}
