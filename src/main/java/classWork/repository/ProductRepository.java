package classWork.repository;

import classWork.dto.ProductDTO;
import classWork.dto.ProductReviewsDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS product(
                product_id SERIAL PRIMARY KEY,
                product_name VARCHAR(50),
                description VARCHAR(255),
                price DECIMAL (10,2),
                quantity_in_stock INT,
                category VARCHAR(50),
                created_at VARCHAR(50)
                );
                """;
        try (Connection connection = DatabaseRepository.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table PRODUCT was created successfully ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTable() {
        String sql = "DROP TABLE product;";
        try (Connection connection = DatabaseRepository.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table delete successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertProduct(ProductDTO product) {
        String sql = "INSERT INTO product (product_name, description, price, quantity_in_stock, category, created_at) " +
                "VALUES (?,?,?,?,?,?);";
        try (Connection connection = DatabaseRepository.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantityInStock());
            ps.setString(5, product.getCategory());
            ps.setString(6, product.getCreatedAt());
            ps.executeUpdate();
            System.out.println("Product inserted successfully.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProductById(int productId) {
        String sql = "DELETE FROM PRODUCT WHERE product_id = ?;";
        try (Connection connection = DatabaseRepository.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.executeUpdate();
            System.out.println("Product deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT;";
        ResultSet resultSet;
        try (Connection connection = DatabaseRepository.getConnection()) {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                products.add(new ProductDTO(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity_in_stock"),
                        resultSet.getString("category"),
                        resultSet.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void createReviewsTable() {

    }

    public void dropReviewsTable() {

    }

    public void insertReview (int productId,String reviewText, int rating) {

    }
    public void getReviewsByProductId (int productId){

    }
    public List<ProductReviewsDTO> getAllProductsWithReviews() {
        List<ProductReviewsDTO> reviews = new ArrayList<>();


        return  reviews;
    }

}
