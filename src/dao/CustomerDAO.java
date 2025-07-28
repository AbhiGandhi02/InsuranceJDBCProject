package dao;

import model.Customer;
// import util.DBConnection;

import java.sql.*;

public class CustomerDAO {
    private Connection conn;

    public CustomerDAO(Connection conn) {
        this.conn = conn;
    }

    public Customer getCustomerById(long id) {
        String sql = "SELECT * FROM Customer WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                    rs.getLong("customer_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getDate("date_of_birth"),
                    rs.getDate("registration_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCustomer(long id) {
        String sql = "DELETE FROM Customer WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add this method to support interactive customer registration
    public void registerCustomerInteractive(java.util.Scanner sc) {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();
        System.out.print("Enter customer email: ");
        String email = sc.nextLine();
        System.out.print("Enter customer password: ");
        String password = sc.nextLine();
        System.out.print("Enter customer phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter customer address: ");
        String address = sc.nextLine();
        System.out.print("Enter date of birth (yyyy-mm-dd): ");
        String dobStr = sc.nextLine();
        System.out.print("Enter registration date (yyyy-mm-dd): ");
        String regStr = sc.nextLine();

        java.sql.Date dateOfBirth = java.sql.Date.valueOf(dobStr);
        java.sql.Date registrationDate = java.sql.Date.valueOf(regStr);

        Customer customer = new Customer(0, name, email, password, phone, address, dateOfBirth, registrationDate);

        try {
            String sql = "INSERT INTO Customer (name, email, password, phone, address, date_of_birth, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getEmail());
                stmt.setString(3, customer.getPassword());
                stmt.setString(4, customer.getPhone());
                stmt.setString(5, customer.getAddress());
                stmt.setDate(6, customer.getDateOfBirth());
                stmt.setDate(7, customer.getRegistrationDate());
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Customer registered successfully.");
                } else {
                    System.out.println("Failed to register customer.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error registering customer: " + e.getMessage());
        }
    }

    public void viewAllCustomers() {
        String query = "SELECT * FROM Customer";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Customer List:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getLong("customer_id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Email: " + rs.getString("email") +
                                   ", Phone Number: " + rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching customers: " + e.getMessage());
        }
    }
}
