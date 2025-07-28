package dao;

import model.Endorsement;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EndorsementDAO {

    public void addEndorsement(Endorsement endorsement) {
        String sql = "INSERT INTO Endorsement (endorsement_id, policy_id, endorsement_date, changes_made) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, endorsement.getEndorsementId());
            stmt.setLong(2, endorsement.getPolicyId());
            stmt.setDate(3, endorsement.getEndorsementDate());
            stmt.setString(4, endorsement.getChangesMade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Endorsement getEndorsementById(long id) {
        String sql = "SELECT * FROM Endorsement WHERE endorsement_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Endorsement(
                    rs.getLong("endorsement_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("endorsement_date"),
                    rs.getString("changes_made")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Endorsement> getAllEndorsements() {
        List<Endorsement> list = new ArrayList<>();
        String sql = "SELECT * FROM Endorsement";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Endorsement(
                    rs.getLong("endorsement_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("endorsement_date"),
                    rs.getString("changes_made")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
