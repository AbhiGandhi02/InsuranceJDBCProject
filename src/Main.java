import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
import util.DBConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connected to DB");

            // Sample customer details
            String name = "Abhi Gandhi";
            String email = "ab@example.com";
            String rawPassword = "mySecurePassword";
            String phone = "1234567890";
            String address = "Mumbai, India";
            LocalDate dob = LocalDate.parse("1999-07-15");
            LocalDate regDate = LocalDate.now();

            // Hash password using SHA-256 (or use bcrypt in a real-world app)
            String hashedPassword = hashPassword(rawPassword);

            String sql = "INSERT INTO Customer (name, email, password, phone, address, date_of_birth, registration_date) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, hashedPassword);
                stmt.setString(4, phone);
                stmt.setString(5, address);
                stmt.setDate(6, Date.valueOf(dob));
                stmt.setDate(7, Date.valueOf(regDate));

                stmt.executeUpdate();
                System.out.println("Customer inserted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
