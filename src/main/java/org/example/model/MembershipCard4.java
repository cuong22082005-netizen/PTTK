package org.example.model;

import java.util.Date;

public class MembershipCard4 {
    private int id;
    private String cardCode;
    private Date issueDate;
    private int totalPoints;
    private MembershipTier4 membershipTier;

    public MembershipCard4() {
    }

    public MembershipCard4(int id, String cardCode, Date issueDate, int totalPoints,
                           MembershipTier4 membershipTier) {
        this.id = id;
        this.cardCode = cardCode;
        this.issueDate = issueDate;
        this.totalPoints = totalPoints;
        this.membershipTier = membershipTier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public MembershipTier4 getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(MembershipTier4 membershipTier) {
        this.membershipTier = membershipTier;
    }
}
