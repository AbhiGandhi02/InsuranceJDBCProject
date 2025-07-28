package model;

public class Claim {
    private long claimId;
    private long policyId;
    private java.sql.Date incidentDate;
    private java.sql.Date submissionDate;
    private java.sql.Date approvalDate;
    private java.sql.Date payoutDate;
    private java.math.BigDecimal amountRequested;
    private java.math.BigDecimal amountApproved;
    private String status;
    private String incidentType;

    public Claim(long claimId, long policyId, java.sql.Date incidentDate, java.sql.Date submissionDate,
                 java.sql.Date approvalDate, java.sql.Date payoutDate, java.math.BigDecimal amountRequested,
                 java.math.BigDecimal amountApproved, String status, String incidentType) {
        this.claimId = claimId;
        this.policyId = policyId;
        this.incidentDate = incidentDate;
        this.submissionDate = submissionDate;
        this.approvalDate = approvalDate;
        this.payoutDate = payoutDate;
        this.amountRequested = amountRequested;
        this.amountApproved = amountApproved;
        this.status = status;
        this.incidentType = incidentType;
    }

    public long getClaimId() { return claimId; }
    public long getPolicyId() { return policyId; }
    public java.sql.Date getIncidentDate() { return incidentDate; }
    public java.sql.Date getSubmissionDate() { return submissionDate; }
    public java.sql.Date getApprovalDate() { return approvalDate; }
    public java.sql.Date getPayoutDate() { return payoutDate; }
    public java.math.BigDecimal getAmountRequested() { return amountRequested; }
    public java.math.BigDecimal getAmountApproved() { return amountApproved; }
    public String getStatus() { return status; }
    public String getIncidentType() { return incidentType; }
}
