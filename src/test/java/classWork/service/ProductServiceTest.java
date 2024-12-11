package classWork.service;

import classWork.dto.ProductDTO;
import classWork.dto.ProductReviewDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {

    private ProductService productService;
    private List<ProductDTO> products;

    @BeforeEach
    public void setUp() {
        List<ProductReviewDTO> laptopReviews = Arrays.asList(
                new ProductReviewDTO(1, 1, "Excellent performance!", 5, new Date()),
                new ProductReviewDTO(2, 1, "Good value for the price.", 4, new Date())
        );

        List<ProductReviewDTO> smartphoneReviews = Arrays.asList(
                new ProductReviewDTO(3, 2, "Great smartphone, worth the price!", 5, new Date()),
                new ProductReviewDTO(4, 2, "Battery life could be better.", 3, new Date())
        );

        products = Arrays.asList(
                new ProductDTO(1, "Laptop", "High-performance laptop", 1200.00, 10, "Electronics", null, laptopReviews),
                new ProductDTO(2, "Smartphone", "Latest model smartphone", 800.00, 15, "Electronics", null, smartphoneReviews)
        );

        productService = new ProductService();
    }


    @Test
    public void testGetAllProductsByCategory() {
        List<ProductDTO> electronics = productService.getAllProductsByCategory(products, "Electronics");
        assertEquals(2, electronics.size());
    }

    @Test
    public void testGetProductsBelowPrice() {
        List<ProductDTO> productList = productService.getProductsBelowPrice(products, 1000);
        assertEquals(1, productList.size());
        assertEquals("Smartphone", productList.get(0).getProductName());
    }

    @Test
    public void testSortProductsByPriceAsc() {
        List<ProductDTO> productList = productService.sortProductsByPrice(products, false);
        assertEquals("Smartphone", productList.get(1).getProductName());
        assertEquals("Laptop", productList.get(0).getProductName());
    }

    @Test
    public void testSortProductsByPriceDes() {
        List<ProductDTO> productList = productService.sortProductsByPrice(products, true);
        assertEquals("Laptop", productList.get(1).getProductName());
        assertEquals("Smartphone", productList.get(0).getProductName());
    }

    @Test
    public void testGetMostExpensiveProduct() {
        ProductDTO mostExpensive = productService.getMostExpensiveProduct(products);
        assertEquals("Laptop", mostExpensive.getProductName());
    }

    @Test
    public void testCalculateTotalStockValue() {
        Double sum = productService.calculateTotalStockValue(products);
        assertEquals(24000, sum);
    }
}