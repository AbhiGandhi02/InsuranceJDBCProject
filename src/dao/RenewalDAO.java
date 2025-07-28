package dao;

import model.Renewal;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RenewalDAO {

    public void addRenewal(Renewal renewal) {
        String sql = "INSERT INTO Renewal (renewal_id, policy_id, renewal_date, new_end_date, renewal_premium) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, renewal.getRenewalId());
            stmt.setLong(2, renewal.getPolicyId());
            stmt.setDate(3, renewal.getRenewalDate());
            stmt.setDate(4, renewal.getNewEndDate());
            stmt.setBigDecimal(5, renewal.getRenewalPremium());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Renewal getRenewalById(long id) {
        String sql = "SELECT * FROM Renewal WHERE renewal_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        String sql = "SELECT * FROM Renewal";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
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
}
