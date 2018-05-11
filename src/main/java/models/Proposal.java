package models;

import java.util.List;

public class Proposal {
    private int version;
    private String prdType, name, identifier, productType, baseRequirements, benefit, department;
    private List<String> interestedParties;
    private boolean isExpedited, isPrototype;

    // Need to provide level of estimates (LOE)
    public Proposal(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrdType(String type) {
        this.prdType = type;
    }

    public String getPrdType() {
        return prdType;
    }

    public boolean isExpedited() {
        return isExpedited;
    }

    public int getVersion() {
        return version;
    }

    public void setExpedited(boolean expedited) {
        isExpedited = expedited;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<String> getInterestedParties() {
        return interestedParties;
    }

    public void setInterestedParties(List<String> interestedParties) {
        this.interestedParties = interestedParties;
    }

    public String getBaseRequirements() {
        return baseRequirements;
    }

    public void setBaseRequirements(String baseRequirements) {
        this.baseRequirements = baseRequirements;
    }

    public boolean isPrototype() {
        return isPrototype;
    }

    public void setPrototype(boolean prototype) {
        isPrototype = prototype;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
