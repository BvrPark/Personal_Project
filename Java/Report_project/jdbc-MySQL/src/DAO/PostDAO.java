package DAO;

import DTO.PostDto;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    private final String url;
    private final String user;
    private final String password;

    public PostDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // 게시물 등록 메소드
    public void createPost(PostDto postDto) {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO post(user_id, title, content) VALUES (?, ?, ?)")) {
            pstmt.setInt(1, postDto.getUserId());
            pstmt.setString(2, postDto.getTitle());
            pstmt.setString(3, postDto.getContent());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 게시물 목록 조회 메소드
    public List<PostDto> selectAllPosts() {
        List<PostDto> postList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM post ORDER BY created DESC");
            while (rs.next()) {
                int postId = rs.getInt("post_id");
                int userId = rs.getInt("user_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                LocalDateTime created = rs.getObject("created", LocalDateTime.class);
                LocalDateTime updated = rs.getObject("updated", LocalDateTime.class);
                PostDto postDto = new PostDto(postId, userId, title, content, created, updated);
                postList.add(postDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    // 게시물 상세 조회 메소드
    public PostDto selectPost(int postId) {
        PostDto postDto = null;
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM post WHERE post_id = ?")) {
            pstmt.setInt(1, postId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                LocalDateTime created = rs.getObject("created", LocalDateTime.class);
                LocalDateTime updated = rs.getObject("updated", LocalDateTime.class);
                postDto = new PostDto(postId, userId, title, content, created, updated);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postDto;
    }

    // 게시물 수정 메소드
    public void updatePost(PostDto postDto) {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE post SET post_title = ?, post_content = ?, updated = ? WHERE post_id = ?")) {
            pstmt.setString(1, postDto.getTitle());
            pstmt.setString(2, postDto.getContent());
            pstmt.setObject(3, LocalDateTime.now());
            pstmt.setInt(4, postDto.getPostId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 게시물 삭제 메소드
    public void deletePost(int postId) {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement("DELETE FROM post WHERE post_id = ?")) {

            ps.setInt(1, postId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

