package dao;

import model.Cancellation;
// import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CancellationDAO {
    private Connection conn;

    public CancellationDAO(Connection conn) {
        this.conn = conn;
    }

    public Cancellation getCancellationById(long id) {
        String sql = "SELECT * FROM cancellation WHERE cancellation_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cancellation(
                    rs.getLong("cancellation_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("cancellation_date"),
                    rs.getString("reason")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cancellation> getAllCancellations() {
        List<Cancellation> list = new ArrayList<>();
        String sql = "SELECT * FROM cancellation";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Cancellation(
                    rs.getLong("cancellation_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("cancellation_date"),
                    rs.getString("reason")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cancelPolicyInteractive(Scanner sc) {
        System.out.print("Enter Policy ID to cancel: ");
        int policyId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter cancellation reason: ");
        String reason = sc.nextLine();
        // Implement cancellation logic here, e.g., insert into cancellation table
        System.out.println("Policy " + policyId + " cancelled for reason: " + reason);
    }
}
