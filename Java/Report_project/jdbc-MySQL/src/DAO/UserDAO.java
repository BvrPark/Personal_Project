package DAO;

import DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertUser(UserDTO userDTO) throws SQLException {
        String sql = "INSERT INTO user (user_id, user_password, user_name) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userDTO.getUserId());
            pstmt.setString(2, userDTO.getPassword());
            pstmt.setString(3, userDTO.getName());

            pstmt.executeUpdate();
        }
    }

    public UserDTO selectUser(String userId) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        UserDTO userDTO = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String password = rs.getString("password");
                    String name = rs.getString("name");
                    userDTO = new UserDTO(userId, password, name);
                }
            }
        }

        return userDTO;
    }
}
