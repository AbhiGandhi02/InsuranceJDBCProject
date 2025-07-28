package model;

public class Endorsement {
    private long endorsementId;
    private long policyId;
    private java.sql.Date endorsementDate;
    private String changesMade;

    public Endorsement(long endorsementId, long policyId, java.sql.Date endorsementDate, String changesMade) {
        this.endorsementId = endorsementId;
        this.policyId = policyId;
        this.endorsementDate = endorsementDate;
        this.changesMade = changesMade;
    }

    public long getEndorsementId() { return endorsementId; }
    public long getPolicyId() { return policyId; }
    public java.sql.Date getEndorsementDate() { return endorsementDate; }
    public String getChangesMade() { return changesMade; }
}
