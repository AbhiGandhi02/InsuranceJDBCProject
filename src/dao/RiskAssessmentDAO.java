package dao;

import model.RiskAssessment;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RiskAssessmentDAO {

    public void addRiskAssessment(RiskAssessment ra) {
        String sql = "INSERT INTO RiskAssessment (assessment_id, customer_id, risk_score, risk_category, assessment_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, ra.getAssessmentId());
            stmt.setLong(2, ra.getCustomerId());
            stmt.setInt(3, ra.getRiskScore());
            stmt.setString(4, ra.getRiskCategory());
            stmt.setDate(5, ra.getAssessmentDate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RiskAssessment getRiskAssessmentById(long id) {
        String sql = "SELECT * FROM RiskAssessment WHERE assessment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RiskAssessment(
                    rs.getLong("assessment_id"),
                    rs.getLong("customer_id"),
                    rs.getInt("risk_score"),
                    rs.getString("risk_category"),
                    rs.getDate("assessment_date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<RiskAssessment> getAllRiskAssessments() {
        List<RiskAssessment> list = new ArrayList<>();
        String sql = "SELECT * FROM RiskAssessment";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new RiskAssessment(
                    rs.getLong("assessment_id"),
                    rs.getLong("customer_id"),
                    rs.getInt("risk_score"),
                    rs.getString("risk_category"),
                    rs.getDate("assessment_date")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
