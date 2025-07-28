package model;

public class Payment {
    private long paymentId;
    private long policyId;
    private java.sql.Date paymentDate;
    private java.math.BigDecimal amount;
    private String paymentMethod;
    private String paymentStatus;

    public Payment(long paymentId, long policyId, java.sql.Date paymentDate, java.math.BigDecimal amount, String paymentMethod, String paymentStatus) {
        this.paymentId = paymentId;
        this.policyId = policyId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public long getPaymentId() { return paymentId; }
    public long getPolicyId() { return policyId; }
    public java.sql.Date getPaymentDate() { return paymentDate; }
    public java.math.BigDecimal getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; }
}
