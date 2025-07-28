// Your import statements remain unchanged
import dao.CustomerDAO;
import dao.PolicyDAO;
import dao.PolicyTypeDAO;
import dao.ClaimDAO;
import dao.PaymentDAO;
import dao.CancellationDAO;
import dao.CoverageDAO;
import dao.EndorsementDAO;
import dao.RenewalDAO;
import dao.RiskAssessmentDAO;

import model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/InsurancePolicy";
        String user = "root";
        String password = "Abhi@123";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the Insurance Database");

            Scanner sc = new Scanner(System.in);
            CustomerDAO customerDAO = new CustomerDAO(conn);
            PolicyDAO policyDAO = new PolicyDAO(conn);
            PolicyTypeDAO policyTypeDAO = new PolicyTypeDAO(conn);
            ClaimDAO claimDAO = new ClaimDAO(conn);
            PaymentDAO paymentDAO = new PaymentDAO(conn);
            CancellationDAO cancellationDAO = new CancellationDAO(conn);
            CoverageDAO coverageDAO = new CoverageDAO(conn);
            EndorsementDAO endorsementDAO = new EndorsementDAO(conn);
            RenewalDAO renewalDAO = new RenewalDAO(conn);
            RiskAssessmentDAO riskAssessmentDAO = new RiskAssessmentDAO(conn);

            while (true) {
                System.out.println("\n------ Insurance Policy Management ------");
                System.out.println("1. Register New Customer");
                System.out.println("2. View All Customers");
                System.out.println("3. View Customer by ID");
                System.out.println("4. Delete Customer by ID");
                System.out.println("5. Add New Policy Type");
                System.out.println("6. View All Policy Types");
                System.out.println("7. View Policy Type by ID");
                System.out.println("8. Create Policy for Customer");
                System.out.println("9. View All Policies");
                System.out.println("10. View Policy by ID");
                System.out.println("11. File a Claim");
                System.out.println("12. View All Claims");
                System.out.println("13. View Claim by ID"); 
                System.out.println("14. Make a Payment");
                System.out.println("15. View All Payments");
                System.out.println("16. View Payment by ID"); 
                System.out.println("17. Cancel a Policy");
                System.out.println("18. View All Cancellations");
                System.out.println("19. View Cancellation by ID"); 
                System.out.println("20. Add Coverage");
                System.out.println("21. View All Coverage");
                System.out.println("22. View Coverage by ID"); 
                System.out.println("23. Add Endorsement");
                System.out.println("24. View All Endorsements");
                System.out.println("25. View Endorsement by ID"); 
                System.out.println("26. Renew Policy");
                System.out.println("27. View All Renewals");
                System.out.println("28. View Renewal by ID"); 
                System.out.println("29. Add Risk Assessment");
                System.out.println("30. View All Risk Assessments");
                System.out.println("31. View Risk Assessment by ID"); 
                System.out.println("32. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {
                        customerDAO.registerCustomerInteractive(sc);
                        break;
                    }
                    case 2 -> {
                        customerDAO.viewAllCustomers();
                        break;
                    }
                    case 3 -> {
                        System.out.print("Enter Customer ID to view: ");
                        long customerId = sc.nextLong(); sc.nextLine();
                        Customer customer = customerDAO.getCustomerById(customerId);
                        if (customer != null) {
                            System.out.println("ID: " + customer.getCustomerId() +
                                               ", Name: " + customer.getName() +
                                               ", Email: " + customer.getEmail() +
                                               ", Phone: " + customer.getPhone() +
                                               ", Address: " + customer.getAddress());
                        } else {
                            System.out.println("Customer not found.");
                        }
                        break;
                    }
                    case 4 -> {
                        System.out.print("Enter Customer ID to delete: ");
                        long customerId = sc.nextLong(); sc.nextLine();
                        customerDAO.deleteCustomer(customerId);
                        System.out.println("Customer deleted (if existed).");
                        break;
                    }
                    case 5 -> {
                        policyTypeDAO.addPolicyTypeInteractive(sc);
                        break;
                    }
                    case 6 -> {
                        policyTypeDAO.viewAllPolicyTypes();
                        break;
                    }
                    case 7 -> {
                        System.out.print("Enter Policy Type ID to view: ");
                        long policyTypeId = sc.nextLong(); sc.nextLine();
                        var policyType = policyTypeDAO.getPolicyTypeById(policyTypeId);
                        if (policyType != null) {
                            System.out.println("ID: " + policyType.getPolicyTypeId() +
                                               ", Name: " + policyType.getName() +
                                               ", Description: " + policyType.getDescription());
                        } else {
                            System.out.println("Policy Type not found.");
                        }
                        break;
                    }
                    case 8 -> {
                        policyDAO.createPolicyInteractive(sc);
                        break;
                    }
                    case 9 -> {
                        policyDAO.viewAllPolicies();
                        break;
                    }
                    case 10 -> {
                        System.out.print("Enter Policy ID to view: ");
                        long policyId = sc.nextLong(); sc.nextLine();
                        var policy = policyDAO.getPolicyById(policyId);
                        if (policy != null) {
                            System.out.println("Policy ID: " + policy.getPolicyId() +
                                               ", Customer ID: " + policy.getCustomerId() +
                                               ", Policy Type ID: " + policy.getPolicyTypeId() +
                                               ", Issue Date: " + policy.getIssueDate() +
                                               ", Start Date: " + policy.getStartDate() +
                                               ", End Date: " + policy.getEndDate() +
                                               ", Status: " + policy.getStatus());
                        } else {
                            System.out.println("Policy not found.");
                        }
                        break;
                    }
                    case 11 -> {
                        claimDAO.fileClaimInteractive(sc);
                        break;
                    }
                    case 12 -> {
                        claimDAO.viewAllClaims();
                        break;
                    }
                    case 13 -> {
                        System.out.print("Enter Claim ID to view: ");
                        long claimId = sc.nextLong(); sc.nextLine();
                        var claim = claimDAO.getClaimById(claimId);
                        if (claim != null) {
                            System.out.println("Claim ID: " + claim.getClaimId() +
                                               ", Policy ID: " + claim.getPolicyId() +
                                               ", Incident Type: " + claim.getIncidentType() +
                                               ", Amount: " + claim.getAmountApproved() +
                                               ", Status: " + claim.getStatus());
                        } else {
                            System.out.println("Claim not found.");
                        }
                        break;
                    }
                    case 14 -> {
                        paymentDAO.makePaymentInteractive(sc);
                        break;
                    }
                    case 15 -> {
                        paymentDAO.getAllPayments();
                        break;
                    }
                    case 16 -> {
                        System.out.print("Enter Payment ID to view: ");
                        long paymentId = sc.nextLong(); sc.nextLine();
                        var payment = paymentDAO.getPaymentById(paymentId);
                        if (payment != null) {
                            System.out.println("Payment ID: " + payment.getPaymentId() +
                                               ", Policy ID: " + payment.getPolicyId() +
                                               ", Amount: " + payment.getAmount() +
                                               ", Payment Date: " + payment.getPaymentDate());
                        } else {
                            System.out.println("Payment not found.");
                        }
                        break;
                    }
                    case 17 -> {
                        cancellationDAO.cancelPolicyInteractive(sc);
                        break;
                    }
                    case 18 -> {
                        cancellationDAO.getAllCancellations();
                        break;
                    }
                    case 19 -> {
                        System.out.print("Enter Cancellation ID to view: ");
                        long cancellationId = sc.nextLong(); sc.nextLine();
                        var cancellation = cancellationDAO.getCancellationById(cancellationId);
                        if (cancellation != null) {
                            System.out.println("Cancellation ID: " + cancellation.getCancellationId() +
                                               ", Policy ID: " + cancellation.getPolicyId() +
                                               ", Reason: " + cancellation.getReason() +
                                               ", Date: " + cancellation.getCancellationDate());
                        } else {
                            System.out.println("Cancellation not found.");
                        }
                        break;
                    }
                    case 20 -> {
                        coverageDAO.addCoverageInteractive(sc);
                        break;
                    }
                    case 21 -> {
                        coverageDAO.getAllCoverages();
                        break;
                    }
                    case 22 -> {
                        System.out.print("Enter Coverage ID to view: ");
                        long coverageId = sc.nextLong(); sc.nextLine();
                        var coverage = coverageDAO.getCoverageById(coverageId);
                        if (coverage != null) {
                            System.out.println("Coverage ID: " + coverage.getCoverageId() +
                                               ", Policy ID: " + coverage.getPolicyId() +
                                               ", Type: " + coverage.getCoverageType() +
                                               ", Limit: " + coverage.getCoverageLimit() +
                                               ", Description: " + coverage.getDescription());
                        } else {
                            System.out.println("Coverage not found.");
                        }
                        break;
                    }
                    case 23 -> {
                        endorsementDAO.addEndorsementInteractive(sc);
                        break;
                    }
                    case 24 -> {
                        endorsementDAO.getAllEndorsements();
                        break;
                    }
                    case 25 -> {
                        System.out.print("Enter Endorsement ID to view: ");
                        long endorsementId = sc.nextLong(); sc.nextLine();
                        var endorsement = endorsementDAO.getEndorsementById(endorsementId);
                        if (endorsement != null) {
                            System.out.println("Endorsement ID: " + endorsement.getEndorsementId() +
                                               ", Policy ID: " + endorsement.getPolicyId() +
                                               ", Change Type: " + endorsement.getChangesMade() +
                                               ", Effective Date: " + endorsement.getEndorsementDate());
                        } else {
                            System.out.println("Endorsement not found.");
                        }
                        break;
                    }
                    case 26 -> {
                        renewalDAO.renewPolicyInteractive(sc);
                        break;
                    }
                    case 27 -> {
                        renewalDAO.getAllRenewals();
                        break;
                    }
                    case 28 -> {
                        System.out.print("Enter Renewal ID to view: ");
                        long renewalId = sc.nextLong(); sc.nextLine();
                        var renewal = renewalDAO.getRenewalById(renewalId);
                        if (renewal != null) {
                            System.out.println("Renewal ID: " + renewal.getRenewalId() +
                                               ", Policy ID: " + renewal.getPolicyId() +
                                               ", Renewal Date: " + renewal.getRenewalDate());
                        } else {
                            System.out.println("Renewal not found.");
                        }
                        break;
                    }
                    case 29 -> {
                        riskAssessmentDAO.addRiskAssessmentInteractive(sc);
                        break;
                    }
                    case 30 -> {
                        riskAssessmentDAO.getAllRiskAssessments();
                        break;
                    }
                    case 31 -> {
                        System.out.print("Enter Risk Assessment ID to view: ");
                        long riskAssessmentId = sc.nextLong(); sc.nextLine();
                        var riskAssessment = riskAssessmentDAO.getRiskAssessmentById(riskAssessmentId);
                        if (riskAssessment != null) {
                            System.out.println("Risk Assessment ID: " + riskAssessment.getAssessmentId() +
                                               ", Customer ID: " + riskAssessment.getCustomerId() +
                                               ", Risk Score: " + riskAssessment.getRiskScore() +
                                               ", Assessment Date: " + riskAssessment.getAssessmentDate());
                        } else {
                            System.out.println("Risk Assessment not found.");
                        }
                        break;
                    }
                    case 32 -> {
                        System.out.println("Exiting...");
                        break;
                    }
                    default -> {
                        System.out.println("Invalid option. Please try again.");
                        break;
                    }
                }
                if (choice == 32) break;
            }

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
