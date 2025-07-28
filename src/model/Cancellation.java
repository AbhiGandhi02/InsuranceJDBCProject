package model;

public class Cancellation {
    private long cancellationId;
    private long policyId;
    private java.sql.Date cancellationDate;
    private String reason;

    public Cancellation(long cancellationId, long policyId, java.sql.Date cancellationDate, String reason) {
        this.cancellationId = cancellationId;
        this.policyId = policyId;
        this.cancellationDate = cancellationDate;
        this.reason = reason;
    }

    public long getCancellationId() { return cancellationId; }
    public long getPolicyId() { return policyId; }
    public java.sql.Date getCancellationDate() { return cancellationDate; }
    public String getReason() { return reason; }
}
