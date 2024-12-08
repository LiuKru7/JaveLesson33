package classWork.service;

import classWork.dto.ProductDTO;

import java.util.Comparator;
import java.util.List;

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
                .mapToDouble(product->product.getPrice()* product.getQuantity_in_stock())
                .sum();
    }
}