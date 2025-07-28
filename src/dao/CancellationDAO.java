package dao;

import model.Cancellation;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CancellationDAO {

    public void addCancellation(Cancellation cancellation) {
        String sql = "INSERT INTO Cancellation (cancellation_id, policy_id, cancellation_date, reason) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, cancellation.getCancellationId());
            stmt.setLong(2, cancellation.getPolicyId());
            stmt.setDate(3, cancellation.getCancellationDate());
            stmt.setString(4, cancellation.getReason());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cancellation getCancellationById(long id) {
        String sql = "SELECT * FROM Cancellation WHERE cancellation_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cancellation(
                    rs.getLong("cancellation_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("cancellation_date"),
                    rs.getString("reason")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cancellation> getAllCancellations() {
        List<Cancellation> list = new ArrayList<>();
        String sql = "SELECT * FROM Cancellation";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Cancellation(
                    rs.getLong("cancellation_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("cancellation_date"),
                    rs.getString("reason")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
