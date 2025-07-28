package model;

public class Coverage {
    private long coverageId;
    private long policyId;
    private String coverageType;
    private java.math.BigDecimal coverageLimit;
    private String description;

    public Coverage(long coverageId, long policyId, String coverageType, java.math.BigDecimal coverageLimit, String description) {
        this.coverageId = coverageId;
        this.policyId = policyId;
        this.coverageType = coverageType;
        this.coverageLimit = coverageLimit;
        this.description = description;
    }

    public long getCoverageId() { return coverageId; }
    public long getPolicyId() { return policyId; }
    public String getCoverageType() { return coverageType; }
    public java.math.BigDecimal getCoverageLimit() { return coverageLimit; }
    public String getDescription() { return description; }
}
