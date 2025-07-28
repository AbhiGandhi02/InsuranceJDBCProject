package dao;

import model.Coverage;
// import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoverageDAO {
    private Connection conn;

    public CoverageDAO(Connection conn) {
        this.conn = conn;
    }

    public Coverage getCoverageById(long id) {
        String sql = "SELECT * FROM Coverage WHERE coverage_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Coverage(
                    rs.getLong("coverage_id"),
                    rs.getLong("policy_id"),
                    rs.getString("coverage_type"),
                    rs.getBigDecimal("coverage_limit"),
                    rs.getString("description")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Coverage> getAllCoverages() {
        List<Coverage> list = new ArrayList<>();
        String sql = "SELECT * FROM Coverage";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Coverage(
                    rs.getLong("coverage_id"),
                    rs.getLong("policy_id"),
                    rs.getString("coverage_type"),
                    rs.getBigDecimal("coverage_limit"),
                    rs.getString("description")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Interactive method to add coverage
    public void addCoverageInteractive(Scanner sc) {
        System.out.println("Enter Policy ID for Coverage:");
        int policyId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Coverage Type:");
        String coverageType = sc.nextLine();
        System.out.println("Enter Coverage Amount:");
        double coverageAmount = sc.nextDouble();
        sc.nextLine();

        // You may need to adjust this according to your Coverage model and DB schema
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO coverage (policy_id, coverage_type, coverage_amount) VALUES (?, ?, ?)")) {
            stmt.setInt(1, policyId);
            stmt.setString(2, coverageType);
            stmt.setDouble(3, coverageAmount);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Coverage added successfully.");
            } else {
                System.out.println("Failed to add coverage.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding coverage: " + e.getMessage());
        }
    }
}
