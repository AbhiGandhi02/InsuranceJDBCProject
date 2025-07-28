package dao;

import model.Endorsement;
// import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EndorsementDAO {
    private Connection conn;

    public EndorsementDAO(Connection conn) {
        this.conn = conn;
    }

    public Endorsement getEndorsementById(long id) {
        String sql = "SELECT * FROM endorsement WHERE endorsement_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Endorsement(
                    rs.getLong("endorsement_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("endorsement_date"),
                    rs.getString("changes_made")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Endorsement> getAllEndorsements() {
        List<Endorsement> list = new ArrayList<>();
        String sql = "SELECT * FROM endorsement";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Endorsement(
                    rs.getLong("endorsement_id"),
                    rs.getLong("policy_id"),
                    rs.getDate("endorsement_date"),
                    rs.getString("changes_made")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addEndorsementInteractive(java.util.Scanner sc) {
        System.out.println("Enter Policy ID for endorsement:");
        int policyId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter endorsement details:");
        String details = sc.nextLine();
        System.out.println("Endorsement added for Policy ID " + policyId + " with details: " + details);
    }
}
