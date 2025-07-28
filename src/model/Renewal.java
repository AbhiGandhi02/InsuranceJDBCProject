package model;

public class Renewal {
    private long renewalId;
    private long policyId;
    private java.sql.Date renewalDate;
    private java.sql.Date newEndDate;
    private java.math.BigDecimal renewalPremium;

    public Renewal(long renewalId, long policyId, java.sql.Date renewalDate, java.sql.Date newEndDate, java.math.BigDecimal renewalPremium) {
        this.renewalId = renewalId;
        this.policyId = policyId;
        this.renewalDate = renewalDate;
        this.newEndDate = newEndDate;
        this.renewalPremium = renewalPremium;
    }

    public long getRenewalId() { return renewalId; }
    public long getPolicyId() { return policyId; }
    public java.sql.Date getRenewalDate() { return renewalDate; }
    public java.sql.Date getNewEndDate() { return newEndDate; }
    public java.math.BigDecimal getRenewalPremium() { return renewalPremium; }
}
