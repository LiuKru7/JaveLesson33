package classWork;

import classWork.dto.ProductDTO;
import classWork.dto.ProductReviewDTO;
import classWork.repository.ProductRepository;
import classWork.service.ProductService;
import classWork.util.ProductDataInitializer;

import java.util.ArrayList;
import java.util.List;


public class ProductMain {

    public static void main(String[] args) {
        List<ProductDTO> products = new ArrayList<>();
        List<ProductReviewDTO> reviews = new ArrayList<>();

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService();
        ProductDataInitializer productDataInitializer = new ProductDataInitializer();

        productRepository.dropReviewsTable();
        productRepository.dropTable();

        productRepository.createTable();
        productRepository.createReviewsTable();

        productDataInitializer.generateDate(productRepository);

        productRepository.deleteProductById(5);
        System.out.println("Displaying reviews for the product with ID 5:");
        productRepository.getReviewsByProductId(5).forEach(System.out::println);

        products = productRepository.getAllProducts();
        reviews = productRepository.getAllReviews();
        products = productRepository.getAllProductsWithReviewsSql();

        //service
        System.out.println("\nProducts belonging to the 'berries' category:");
        productService.getAllProductsByCategory(products, "berries").forEach(System.out::println);

        System.out.println("\nProducts priced below 0.9:");
        productService.getProductsBelowPrice(products, 0.9).forEach(System.out::println);

        System.out.println("\nProducts sorted by price (ascending order):");
        productService.sortProductsByPrice(products, true).forEach(System.out::println);

        System.out.println("\nThe most expensive product:");
        System.out.println(productService.getMostExpensiveProduct(products));

        System.out.println("\nThe total value of all products in stock:");
        System.out.println(productService.calculateTotalStockValue(products));

        System.out.println("\nProducts with reviews rated 4 or higher:");
        productService.getProductsWithPositiveReviews(products).forEach(System.out::println);

        System.out.println("\nTop 3 highest-rated products:");
        productService.getTopRatedProducts(products, 3).forEach(System.out::println);

        System.out.println("\nReview texts for all products:");
        productService.getAllReviewTexts(products).forEach(System.out::println);

        System.out.println("\nAverage rating for each product:");
        System.out.println(productService.calculateAverageRatingPerProduct(products));
    }
}

