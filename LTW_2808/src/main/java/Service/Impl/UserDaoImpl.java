package Service.Impl;

import Service.UserDao;
import Model.User;
import Dao.DBConnection; // ✅ đổi lại cho đúng, không phải DBConnection

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM users WHERE username = ?"; // ✅ đúng tên bảng
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));   // ✅ khớp với model
                user.setFullName(rs.getString("fullname"));
                user.setPassword(rs.getString("password"));   // ✅ khớp với model
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(rs.getInt("role_id"));         // ✅ khớp với model + DB
                user.setPhone(rs.getString("phone"));
                user.setCreatedDate(rs.getTimestamp("created_date")); // ✅ đúng tên cột
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
