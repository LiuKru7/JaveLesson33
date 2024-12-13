package classWork.repository;

import classWork.dto.ProductDTO;
import classWork.dto.ProductReviewDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
            System.out.println("Table PRODUCT deleted successfully.");
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
        String sql = """
                DELETE FROM product_review WHERE product_id = ?;
                DELETE FROM product WHERE product_id = ?;
                """;
        try (Connection connection = DatabaseRepository.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, productId);
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
        String sql = """
                CREATE TABLE IF NOT EXISTS product_review (
                review_id SERIAL PRIMARY KEY,
                product_id int,
                review_text VARCHAR(255) NOT NULL,
                rating int CHECK (rating >= 0 AND rating <= 10),
                created_at DATE DEFAULT CURRENT_DATE NOT NULL,
                FOREIGN KEY (product_id) REFERENCES product(product_id));
                """;
        try (Connection connection = DatabaseRepository.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table PRODUCT_REVIEW was created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropReviewsTable() {
        String sql = "DROP TABLE IF EXISTS product_review;";
        try (Connection connection = DatabaseRepository.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table PRODUCT_REVIEW was deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertReview(ProductReviewDTO review) {
        String sql = "INSERT INTO product_review (product_id, review_text, rating) VALUES (?,?,?);";
        try (Connection connection = DatabaseRepository.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, review.getProductId());
            ps.setString(2, review.getReviewText());
            ps.setInt(3, review.getRating());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductReviewDTO> getReviewsByProductId(int productId) {
        String sql = "SELECT * FROM product_review WHERE product_id = ?;";
        ResultSet rs;
        List<ProductReviewDTO> reviews = new ArrayList<>();

        try (Connection connection = DatabaseRepository.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next())
                reviews.add(new ProductReviewDTO(
                        rs.getInt("review_id"),
                        rs.getInt("product_id"),
                        rs.getString("review_text"),
                        rs.getInt("rating"),
                        rs.getDate("created_at")
                ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }


    public List<ProductReviewDTO> getAllReviews() {
        List<ProductReviewDTO> reviews = new ArrayList<>();
        String sql = "SELECT * FROM product_review;";
        ResultSet rs;
        try (Connection connection = DatabaseRepository.getConnection()) {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next())
                reviews.add(new ProductReviewDTO(
                        rs.getInt("review_id"),
                        rs.getInt("product_id"),
                        rs.getString("review_text"),
                        rs.getInt("rating"),
                        rs.getDate("created_at")
                ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    public List<ProductDTO> getAllProductsWithReviewsSql() {
        List<ProductDTO> products = new ArrayList<>();
        String productSql = "SELECT * FROM PRODUCT;";
        String reviewSql = "SELECT * FROM product_review WHERE product_id = ?;";

        try (Connection connection = DatabaseRepository.getConnection()) {
            Statement productStatement = connection.createStatement();
            ResultSet productResultSet = productStatement.executeQuery(productSql);

            while (productResultSet.next()) {
                ProductDTO product = getProductDTO(productResultSet);
                products.add(product);
            }

            PreparedStatement reviewStatement = connection.prepareStatement(reviewSql);
            for (ProductDTO product : products) {
                reviewStatement.setInt(1, product.getProductId());
                ResultSet reviewResultSet = reviewStatement.executeQuery();
                while (reviewResultSet.next()) {
                    ProductReviewDTO review = getProductReviewDTO(reviewResultSet);
                    product.getReviews().add(review);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    private static ProductReviewDTO getProductReviewDTO(ResultSet reviewResultSet) throws SQLException {
        ProductReviewDTO review = new ProductReviewDTO(
                reviewResultSet.getInt("review_id"),
                reviewResultSet.getInt("product_id"),
                reviewResultSet.getString("review_text"),
                reviewResultSet.getInt("rating"),
                reviewResultSet.getDate("created_at")
        );
        return review;
    }

    private static ProductDTO getProductDTO(ResultSet productResultSet) throws SQLException {
        ProductDTO product = new ProductDTO(
                productResultSet.getInt("product_id"),
                productResultSet.getString("product_name"),
                productResultSet.getString("description"),
                productResultSet.getDouble("price"),
                productResultSet.getInt("quantity_in_stock"),
                productResultSet.getString("category"),
                productResultSet.getString("created_at")
        );
        return product;
    }
}
