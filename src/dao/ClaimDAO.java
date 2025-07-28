package dao;

import model.Claim;
// import util.DBConnection;

import java.sql.*;
public class ClaimDAO {
    private Connection conn;

    public ClaimDAO(Connection conn) {
        this.conn = conn;
    }

    public Claim getClaimById(long id) {
        String sql = "SELECT * FROM claim WHERE claim_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    // Add this method to handle interactive claim filing
    public void fileClaimInteractive(java.util.Scanner sc) {
        System.out.println("Enter Customer ID:");
        int customerId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Policy ID:");
        int policyId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Claim Amount:");
        double claimAmount = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter Claim Description:");
        String description = sc.nextLine();

        // You can add logic here to insert the claim into the database
        // For now, just print the values
        System.out.println("Claim filed for Customer ID: " + customerId +
                           ", Policy ID: " + policyId +
                           ", Amount: " + claimAmount +
                           ", Description: " + description);
    }

    public void viewAllClaims() {
        String query = "SELECT * FROM claim";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("All Claims:");
            while (rs.next()) {
                int claimId = rs.getInt("claim_id");
                int policyId = rs.getInt("policy_id");
                String claimType = rs.getString("claim_type");
                double amount = rs.getDouble("amount");
                String status = rs.getString("status");
                System.out.printf("Claim ID: %d, Policy ID: %d, Type: %s, Amount: %.2f, Status: %s%n",
                        claimId, policyId, claimType, amount, status);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving claims: " + e.getMessage());
        }
    }
}
