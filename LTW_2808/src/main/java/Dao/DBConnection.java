package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Thông tin kết nối MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/ltw_bt?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";     // user MySQL của bạn
    private static final String PASSWORD = "123456"; // password MySQL của bạn

    // Hàm lấy connection
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tạo kết nối
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Kết nối MySQL thành công!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Không tìm thấy Driver MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Lỗi kết nối CSDL: " + e.getMessage());
        }
        return conn;
    }

    // Đóng kết nối
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("❌ Lỗi đóng kết nối: " + e.getMessage());
            }
        }
    }
}
