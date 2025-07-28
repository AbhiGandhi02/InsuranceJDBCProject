package dao;

import model.Claim;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAO {

    public void addClaim(Claim claim) {
        String sql = "INSERT INTO Claim (claim_id, policy_id, incident_date, submission_date, approval_date, payout_date, amount_requested, amount_approved, status, incident_type) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, claim.getClaimId());
            stmt.setLong(2, claim.getPolicyId());
            stmt.setDate(3, claim.getIncidentDate());
            stmt.setDate(4, claim.getSubmissionDate());
            stmt.setDate(5, claim.getApprovalDate());
            stmt.setDate(6, claim.getPayoutDate());
            stmt.setBigDecimal(7, claim.getAmountRequested());
            stmt.setBigDecimal(8, claim.getAmountApproved());
            stmt.setString(9, claim.getStatus());
            stmt.setString(10, claim.getIncidentType());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Claim getClaimById(long id) {
        String sql = "SELECT * FROM Claim WHERE claim_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Claim(
                    rs.getLong("claim_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("incident_date"),
                    rs.getDate("submission_date"),
                    rs.getDate("approval_date"),
                    rs.getDate("payout_date"),
                    rs.getBigDecimal("amount_requested"),
                    rs.getBigDecimal("amount_approved"),
                    rs.getString("status"),
                    rs.getString("incident_type")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Claim> getAllClaims() {
        List<Claim> list = new ArrayList<>();
        String sql = "SELECT * FROM Claim";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Claim(
                    rs.getLong("claim_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("incident_date"),
                    rs.getDate("submission_date"),
                    rs.getDate("approval_date"),
                    rs.getDate("payout_date"),
                    rs.getBigDecimal("amount_requested"),
                    rs.getBigDecimal("amount_approved"),
                    rs.getString("status"),
                    rs.getString("incident_type")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
