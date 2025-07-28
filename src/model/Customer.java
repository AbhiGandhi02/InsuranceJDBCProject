package model;

public class Customer {
    private long customerId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private java.sql.Date dateOfBirth;
    private java.sql.Date registrationDate;

    public Customer(long customerId, String name, String email, String password, String phone, String address, java.sql.Date dateOfBirth, java.sql.Date registrationDate) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
    }

    public long getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public java.sql.Date getDateOfBirth() { return dateOfBirth; }
    public java.sql.Date getRegistrationDate() { return registrationDate; }
}
