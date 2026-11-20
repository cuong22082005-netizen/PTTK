package org.example.model;

public class MembershipTier4 {
    private int id;
    private String nameMembershipTier;
    private int minTotalPoints;
    private float discountRate; // tỷ lệ giảm giá, ví dụ 0.15 = 15%

    public MembershipTier4() {
    }

    public MembershipTier4(int id, String nameMembershipTier, int minTotalPoints, float discountRate) {
        this.id = id;
        this.nameMembershipTier = nameMembershipTier;
        this.minTotalPoints = minTotalPoints;
        this.discountRate = discountRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameMembershipTier() {
        return nameMembershipTier;
    }

    public void setNameMembershipTier(String nameMembershipTier) {
        this.nameMembershipTier = nameMembershipTier;
    }

    public int getMinTotalPoints() {
        return minTotalPoints;
    }

    public void setMinTotalPoints(int minTotalPoints) {
        this.minTotalPoints = minTotalPoints;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }
}
