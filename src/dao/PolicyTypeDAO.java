package dao;

import model.PolicyType;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyTypeDAO {

    public void addPolicyType(PolicyType policyType) {
        String sql = "INSERT INTO PolicyType (policy_type_id, name, description, base_premium) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, policyType.getPolicyTypeId());
            stmt.setString(2, policyType.getName());
            stmt.setString(3, policyType.getDescription());
            stmt.setBigDecimal(4, policyType.getBasePremium());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PolicyType getPolicyTypeById(long id) {
        String sql = "SELECT * FROM PolicyType WHERE policy_type_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new PolicyType(
                    rs.getLong("policy_type_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBigDecimal("base_premium")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PolicyType> getAllPolicyTypes() {
        List<PolicyType> list = new ArrayList<>();
        String sql = "SELECT * FROM PolicyType";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new PolicyType(
                    rs.getLong("policy_type_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBigDecimal("base_premium")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
