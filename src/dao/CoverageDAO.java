package dao;

import model.Coverage;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoverageDAO {

    public void addCoverage(Coverage coverage) {
        String sql = "INSERT INTO Coverage (coverage_id, policy_id, coverage_type, coverage_limit, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, coverage.getCoverageId());
            stmt.setLong(2, coverage.getPolicyId());
            stmt.setString(3, coverage.getCoverageType());
            stmt.setBigDecimal(4, coverage.getCoverageLimit());
            stmt.setString(5, coverage.getDescription());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Coverage getCoverageById(long id) {
        String sql = "SELECT * FROM Coverage WHERE coverage_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
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
}
