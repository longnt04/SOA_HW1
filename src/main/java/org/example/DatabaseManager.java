package org.example;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USER = "root";
    private static final String PASSWORD = "123456"; // Đổi mật khẩu của bạn vào đây

    // Kết nối database
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Lấy giá sản phẩm theo ID
    public static double getProductPrice(String productId) {
        double price = -1;
        String query = "SELECT price FROM products WHERE product_id = ?";
        System.out.println(1);
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    public static void main(String[] args) {
        System.out.println("Giá sản phẩm P1003: $" + getProductPrice("P1003"));
    }
}
