package dao;

import model.RiskAssessment;
// import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RiskAssessmentDAO {
    private Connection conn;

    public RiskAssessmentDAO(Connection conn) {
        this.conn = conn;
    }

    public RiskAssessment getRiskAssessmentById(long id) {
        String sql = "SELECT * FROM risk_assessment WHERE assessment_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        String sql = "SELECT * FROM risk_assessment";

        try (Statement stmt = conn.createStatement();
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

    public void addRiskAssessmentInteractive(Scanner sc) {
        System.out.println("Enter Customer ID for Risk Assessment:");
        int customerId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Risk Score:");
        double riskScore = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter Assessment Date (YYYY-MM-DD):");
        String assessmentDate = sc.nextLine();

        // Insert logic to add risk assessment to DB
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO risk_assessment (customer_id, risk_score, assessment_date) VALUES (?, ?, ?)")) {
            stmt.setInt(1, customerId);
            stmt.setDouble(2, riskScore);
            stmt.setString(3, assessmentDate);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Risk Assessment added successfully.");
            } else {
                System.out.println("Failed to add Risk Assessment.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
