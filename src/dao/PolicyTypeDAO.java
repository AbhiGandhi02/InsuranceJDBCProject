package dao;

import model.PolicyType;
// import util.DBConnection;

import java.sql.*;
public class PolicyTypeDAO {
    private Connection conn;

    public PolicyTypeDAO(Connection conn) {
        this.conn = conn;
    }

    public PolicyType getPolicyTypeById(long id) {
        String sql = "SELECT * FROM PolicyType WHERE policy_type_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    // Add this method to allow interactive addition of a policy type
    public void addPolicyTypeInteractive(java.util.Scanner sc) {
        System.out.print("Enter Policy Type Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Policy Type Description: ");
        String description = sc.nextLine();

        try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO policy_types (name, description) VALUES (?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Policy Type added successfully.");
            } else {
                System.out.println("Failed to add Policy Type.");
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error adding Policy Type: " + e.getMessage());
        }
    }

    public void viewAllPolicyTypes() {
        String query = "SELECT * FROM policy_type";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Policy Types:");
            while (rs.next()) {
                int id = rs.getInt("policy_type_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                System.out.printf("ID: %d, Name: %s, Description: %s%n", id, name, description);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching policy types: " + e.getMessage());
        }
    }
}
