package dao;

import model.Policy;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyDAO {

    public void addPolicy(Policy policy) {
        String sql = "INSERT INTO Policy (policy_id, customer_id, policy_type_id, issue_date, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, policy.getPolicyId());
            stmt.setLong(2, policy.getCustomerId());
            stmt.setLong(3, policy.getPolicyTypeId());
            stmt.setDate(4, policy.getIssueDate());
            stmt.setDate(5, policy.getStartDate());
            stmt.setDate(6, policy.getEndDate());
            stmt.setString(7, policy.getStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Policy getPolicyById(long id) {
        String sql = "SELECT * FROM Policy WHERE policy_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Policy(
                    rs.getLong("policy_id"),
                    rs.getLong("customer_id"),
                    rs.getLong("policy_type_id"),
                    rs.getDate("issue_date"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Policy> getAllPolicies() {
        List<Policy> list = new ArrayList<>();
        String sql = "SELECT * FROM Policy";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Policy(
                    rs.getLong("policy_id"),
                    rs.getLong("customer_id"),
                    rs.getLong("policy_type_id"),
                    rs.getDate("issue_date"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
