package dao;

import model.Renewal;
// import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RenewalDAO {
    private Connection conn;

    public RenewalDAO(Connection conn) {
        this.conn = conn;
    }

    public Renewal getRenewalById(long id) {
        String sql = "SELECT * FROM renewal WHERE renewal_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Renewal(
                    rs.getLong("renewal_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("renewal_date"),
                    rs.getDate("new_end_date"),
                    rs.getBigDecimal("renewal_premium")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Renewal> getAllRenewals() {
        List<Renewal> list = new ArrayList<>();
        String sql = "SELECT * FROM renewal";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Renewal(
                    rs.getLong("renewal_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("renewal_date"),
                    rs.getDate("new_end_date"),
                    rs.getBigDecimal("renewal_premium")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Add this method to handle interactive policy renewal
    public void renewPolicyInteractive(java.util.Scanner sc) {
        System.out.print("Enter Policy ID to renew: ");
        int policyId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new renewal date (YYYY-MM-DD): ");
        String renewalDate = sc.nextLine();

        // Example implementation, adjust as needed
        try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO renewals (policy_id, renewal_date) VALUES (?, ?)")) {
            stmt.setInt(1, policyId);
            stmt.setString(2, renewalDate);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Policy renewed successfully.");
            } else {
                System.out.println("Failed to renew policy.");
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error renewing policy: " + e.getMessage());
        }
    }
}
