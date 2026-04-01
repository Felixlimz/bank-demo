package demo;

import java.sql.*;
import java.util.Scanner;

public class Hello {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();

        String password = "123456"; // issue

        String query = "SELECT * FROM users WHERE username = '" + username + "'"; // SQL injection

        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://postgres:5432/sonarqube",
            "sonar",
            "sonar123"
        );

        Statement stmt = conn.createStatement();
        stmt.executeQuery(query);

        System.out.println(password);
    }
}
