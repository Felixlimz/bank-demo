import java.sql.*;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hello {

    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        getUserByUsername(username);
    }

    public static void getUserByUsername(String username) {
        String query = "SELECT anyfield FROM users WHERE username = ?";
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://postgres:5432/sonarqube",
                dbUser,
                dbPass);
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            // set parameter
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    log.error(rs.getString("username"));
                }
            }

        } catch (Exception e) {
            log.error("Failed to query user", e);
        }
    }
}
