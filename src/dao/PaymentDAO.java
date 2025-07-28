package dao;

import model.Payment;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public void addPayment(Payment payment) {
        String sql = "INSERT INTO Payment (payment_id, policy_id, payment_date, amount, payment_method, payment_status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, payment.getPaymentId());
            stmt.setLong(2, payment.getPolicyId());
            stmt.setDate(3, payment.getPaymentDate());
            stmt.setBigDecimal(4, payment.getAmount());
            stmt.setString(5, payment.getPaymentMethod());
            stmt.setString(6, payment.getPaymentStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Payment getPaymentById(long id) {
        String sql = "SELECT * FROM Payment WHERE payment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        String sql = "SELECT * FROM Payment";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
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
}
