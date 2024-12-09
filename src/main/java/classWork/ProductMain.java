package classWork;

import classWork.dto.ProductDTO;
import classWork.dto.ProductReviewsDTO;
import classWork.repository.ProductRepository;
import classWork.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductMain {

    public static void main(String[] args) {
        List<ProductDTO> products = new ArrayList<>();

        ProductRepository productRepository = new ProductRepository();

        ProductService productService = new ProductService();

        productRepository.dropReviewsTable();
        productRepository.dropTable();
        productRepository.createTable();
        productRepository.insertProduct(new ProductDTO
                ("Apple", "red", 0.99, 5, "Fruits", "USA"));
        productRepository.insertProduct(new ProductDTO
                ("Banana", "yellow", 1.49, 10, "Fruits", "Ecuador"));
        productRepository.insertProduct(new ProductDTO
                ("Cucumber", "green", 0.79, 7, "Vegetables", "Spain"));
        productRepository.insertProduct(new ProductDTO
                ("Orange", "orange", 1.29, 6, "Fruits", "Morocco"));
        productRepository.insertProduct(new ProductDTO
                ("Potato", "brown", 0.59, 20, "Vegetables", "Poland"));
        productRepository.insertProduct(new ProductDTO
                ("Tomato", "red", 1.99, 3, "Vegetables", "Italy"));
        productRepository.insertProduct(new ProductDTO
                ("Onion", "white", 0.49, 15, "Vegetables", "Netherlands"));
        productRepository.insertProduct(new ProductDTO
                ("Blueberry", "blue", 3.99, 2, "Berries", "Canada"));
        productRepository.insertProduct(new ProductDTO
                ("Carrot", "orange", 0.89, 12, "Vegetables", "Lithuania"));
        productRepository.insertProduct(new ProductDTO
                ("Strawberry", "red", 2.99, 8, "Berries", "Belgium"));

//        productRepository.deleteProductById(5);

        products = productRepository.getAllProducts();

        System.out.println("Products by category");
        productService.getAllProductsByCategory(products, "berries").forEach(System.out::println);

        System.out.println("Product with below price");
        productService.getProductsBelowPrice(products, 0.9).forEach(System.out::println);

        System.out.println("PRODUCTS SORTED BY PRICE");
        productService.sortProductsByPrice(products, true).forEach(System.out::println);

        System.out.println("Most expensive product: " +productService.getMostExpensiveProduct(products) );
        System.out.println(productService.calculateTotalStockValue(products));

        productRepository.createReviewsTable();

        productRepository.insertReview(new ProductReviewsDTO(1, "Amazing quality!", 5));
        productRepository.insertReview(new ProductReviewsDTO(1, "Good but could be cheaper.", 4));
        productRepository.insertReview(new ProductReviewsDTO(2, "Very fresh and tasty.", 5));
        productRepository.insertReview(new ProductReviewsDTO(2, "Not what I expected.", 3));
        productRepository.insertReview(new ProductReviewsDTO(3, "Very good", 4));
        productRepository.insertReview(new ProductReviewsDTO(3, "Crisp and delicious.", 5));
        productRepository.insertReview(new ProductReviewsDTO(4, "Excellent taste!", 5));
        productRepository.insertReview(new ProductReviewsDTO(4, "A bit too sour for my liking.", 3));
        productRepository.insertReview(new ProductReviewsDTO(5, "Great value for money.", 4));
        productRepository.insertReview(new ProductReviewsDTO(5, "Not bad, but not great either.", 3));
        productRepository.insertReview(new ProductReviewsDTO(6, "Juicy and fresh.", 5));
        productRepository.insertReview(new ProductReviewsDTO(6, "Overpriced for the quality.", 2));
        productRepository.insertReview(new ProductReviewsDTO(7, "Perfectly ripe!", 5));
        productRepository.insertReview(new ProductReviewsDTO(7, "Tastes great in recipes.", 4));
        productRepository.insertReview(new ProductReviewsDTO(8, "Exceptional product.", 5));
        productRepository.insertReview(new ProductReviewsDTO(8, "Would buy again.", 4));
        productRepository.insertReview(new ProductReviewsDTO(9, "Very fresh and crunchy.", 5));
        productRepository.insertReview(new ProductReviewsDTO(9, "Best I’ve had in years.", 5));
        productRepository.insertReview(new ProductReviewsDTO(10, "Sweet and juicy.", 5));
        productRepository.insertReview(new ProductReviewsDTO(10, "Highly recommend.", 5));

        productRepository.getReviewsByProductId(5).forEach(System.out::println);
        productRepository.getAllReviews().forEach(System.out::println);


    }

}
