package model;

public class RiskAssessment {
    private long assessmentId;
    private long customerId;
    private int riskScore;
    private String riskCategory;
    private java.sql.Date assessmentDate;

    public RiskAssessment(long assessmentId, long customerId, int riskScore, String riskCategory, java.sql.Date assessmentDate) {
        this.assessmentId = assessmentId;
        this.customerId = customerId;
        this.riskScore = riskScore;
        this.riskCategory = riskCategory;
        this.assessmentDate = assessmentDate;
    }

    public long getAssessmentId() { return assessmentId; }
    public long getCustomerId() { return customerId; }
    public int getRiskScore() { return riskScore; }
    public String getRiskCategory() { return riskCategory; }
    public java.sql.Date getAssessmentDate() { return assessmentDate; }
}
