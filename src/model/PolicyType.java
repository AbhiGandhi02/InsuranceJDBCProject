package model;

public class PolicyType {
    private long policyTypeId;
    private String name;
    private String description;
    private java.math.BigDecimal basePremium;

    public PolicyType(long policyTypeId, String name, String description, java.math.BigDecimal basePremium) {
        this.policyTypeId = policyTypeId;
        this.name = name;
        this.description = description;
        this.basePremium = basePremium;
    }

    public long getPolicyTypeId() { return policyTypeId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public java.math.BigDecimal getBasePremium() { return basePremium; }
}
