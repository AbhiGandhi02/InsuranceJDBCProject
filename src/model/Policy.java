package model;

public class Policy {
    private long policyId;
    private long customerId;
    private long policyTypeId;
    private java.sql.Date issueDate;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private String status;

    public Policy(long policyId, long customerId, long policyTypeId, java.sql.Date issueDate, java.sql.Date startDate, java.sql.Date endDate, String status) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.policyTypeId = policyTypeId;
        this.issueDate = issueDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public long getPolicyId() { return policyId; }
    public long getCustomerId() { return customerId; }
    public long getPolicyTypeId() { return policyTypeId; }
    public java.sql.Date getIssueDate() { return issueDate; }
    public java.sql.Date getStartDate() { return startDate; }
    public java.sql.Date getEndDate() { return endDate; }
    public String getStatus() { return status; }
}
