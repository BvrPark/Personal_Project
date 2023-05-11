import Util.DBUtil;

import java.sql.*;
import java.util.Scanner;

public class BoardApplication {
    private static Connection conn;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DBUtil.getConnection();

            while (true) {
                System.out.println("=======================");
                System.out.println("1. 회원 가입");
                System.out.println("2. 로그인");
                System.out.println("3. 로그아웃");
                System.out.println("4. 게시판 글쓰기");
                System.out.println("5. 게시판 글삭제");
                System.out.println("6. 게시판 조회");
                System.out.println("7. 종료");
                System.out.println("=======================");

                System.out.print("메뉴 선택: ");
                int menu = scanner.nextInt();
                scanner.nextLine();

                switch (menu) {
                    case 1:
                        join();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        logout();
                        break;
                    case 4:
                        write();
                        break;
                    case 5:
                        delete();
                        break;
                    case 6:
                        read();
                        break;
                    case 7:
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    default:
                        System.out.println("잘못된 메뉴 선택입니다.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
    }

    private static void join() throws SQLException {
        System.out.print("아이디: ");
        String id = scanner.nextLine();

        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        pstmt = conn.prepareStatement("INSERT INTO user (user_name, user_password) VALUES (?, ?)");
        pstmt.setString(1, id);
        pstmt.setString(2, password);
        int result = pstmt.executeUpdate();

        if (result == 1) {
            System.out.println("회원 가입에 성공했습니다.");
        } else {
            System.out.println("회원 가입에 실패했습니다.");
        }
    }

    private static void login() throws SQLException {
        System.out.print("아이디: ");
        String id = scanner.nextLine();

        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        pstmt = conn.prepareStatement("SELECT * FROM user WHERE user_name = ? AND user_password = ?");
        pstmt.setString(1, id);
        pstmt.setString(2, password);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            System.out.println("로그인에 성공했습니다.");
        } else {
            System.out.println("로그인에 실패했습니다.");
        }
    }

    private static void logout() {
        System.out.println("로그아웃 되었습니다.");
    }

    private static void write() throws SQLException {
        System.out.print("제목: ");
        String title = scanner.nextLine();

        System.out.print("내용: ");
        String content = scanner.nextLine();

        pstmt = conn.prepareStatement("INSERT INTO post (post_title, post_content) VALUES (?, ?)");
        pstmt.setString(1, title);
        pstmt.setString(2, content);
        int result = pstmt.executeUpdate();

        if (result == 1) {
            System.out.println("글쓰기에 실패했습니다.");
        } else {
            System.out.println("글쓰기에 성공했습니다.");
        }

    }

    private static void delete() throws SQLException {
        System.out.print("삭제할 게시글 번호: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        pstmt = conn.prepareStatement("DELETE FROM post WHERE post_id = ?");
        pstmt.setInt(1, id);
        int result = pstmt.executeUpdate();

        if (result == 1) {
            System.out.println("게시글이 삭제되었습니다.");
        } else {
            System.out.println("삭제할 게시글이 없습니다.");
        }
    }

    private static void read() throws SQLException {
        pstmt = conn.prepareStatement("SELECT * FROM post ORDER BY post_id DESC");
        rs = pstmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String content = rs.getString("content");

            System.out.println("=======================");
            System.out.println("번호: " + id);
            System.out.println("제목: " + title);
            System.out.println("내용: " + content);
        }
        System.out.println("=======================");
    }
}

