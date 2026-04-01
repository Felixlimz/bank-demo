package demo;

import java.sql.*;
import java.util.Scanner;

public class Hello {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        getUserByUsername(username);
    }

        public static void getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?"; // gunakan placeholder ?

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://postgres:5432/sonarqube",
                "sonar",
                "sonar123");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // set parameter
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("User: " + rs.getString("username"));
                    // Ambil field lain sesuai kebutuhan
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
