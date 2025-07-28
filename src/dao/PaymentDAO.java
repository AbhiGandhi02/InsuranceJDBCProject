package dao;

import model.Payment;
// import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private Connection conn;

    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    public Payment getPaymentById(long id) {
        String sql = "SELECT * FROM payment WHERE payment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Payment(
                    rs.getLong("payment_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("payment_date"),
                    rs.getBigDecimal("amount"),
                    rs.getString("payment_method"),
                    rs.getString("payment_status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM payment";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Payment(
                    rs.getLong("payment_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("payment_date"),
                    rs.getBigDecimal("amount"),
                    rs.getString("payment_method"),
                    rs.getString("payment_status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Add this method to handle interactive payment creation
    public void makePaymentInteractive(java.util.Scanner sc) {
        System.out.println("Enter Policy ID for payment:");
        int policyId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Payment Amount:");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter Payment Date (YYYY-MM-DD):");
        String paymentDate = sc.nextLine();

        // You can add logic to insert payment into DB here
        // For example:
        String sql = "INSERT INTO payment (policy_id, amount, payment_date) VALUES (?, ?, ?)";
        try (java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, policyId);
            stmt.setDouble(2, amount);
            stmt.setString(3, paymentDate);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Payment recorded successfully.");
            } else {
                System.out.println("Failed to record payment.");
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
