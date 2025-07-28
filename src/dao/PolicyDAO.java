package dao;

import model.Policy;
// import util.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class PolicyDAO {
    private Connection conn;

    public PolicyDAO(Connection conn) {
        this.conn = conn;
    }

    public void addPolicy(Policy policy) {
        String sql = "INSERT INTO policy (policy_id, customer_id, policy_type_id, issue_date, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        String sql = "SELECT * FROM policy WHERE policy_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    // Add this method to handle interactive policy creation
    public void createPolicyInteractive(Scanner sc) {
        System.out.println("Enter customer ID:");
        long customerId = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter policy type ID:");
        long policyTypeId = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter issue date (YYYY-MM-DD):");
        String issueDateStr = sc.nextLine();
        System.out.println("Enter policy start date (YYYY-MM-DD):");
        String startDateStr = sc.nextLine();
        System.out.println("Enter policy end date (YYYY-MM-DD):");
        String endDateStr = sc.nextLine();
        System.out.println("Enter policy status:");
        String status = sc.nextLine();

        java.sql.Date issueDate = java.sql.Date.valueOf(issueDateStr);
        java.sql.Date startDate = java.sql.Date.valueOf(startDateStr);
        java.sql.Date endDate = java.sql.Date.valueOf(endDateStr);

        // You can generate policyId automatically if your DB uses AUTO_INCREMENT
        Policy policy = new Policy(0, customerId, policyTypeId, issueDate, startDate, endDate, status);

        addPolicy(policy);

        System.out.println("Policy created and saved to database.");
    }

    public void viewAllPolicies() {
        // Example implementation: fetch and print all policies
        String query = "SELECT * FROM policy";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("All Policies:");
            while (rs.next()) {
                System.out.println("Policy ID: " + rs.getInt("policy_id") +
                                   ", Customer ID: " + rs.getInt("customer_id") +
                                   ", Policy Type: " + rs.getString("policy_type") +
                                   ", Start Date: " + rs.getDate("start_date") +
                                   ", End Date: " + rs.getDate("end_date"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching policies: " + e.getMessage());
        }
    }
}
