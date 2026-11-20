package org.example.model;

import java.util.Date;

public class Customer4 extends Member4 {
    private Date registerDate;
    private MembershipCard4 membershipCard; // quan hệ gắn chặt 1-1

    public Customer4() {
        super();
    }

    public Customer4(int id, String userName, String password, String fullName, Address4 address,
                     String email, String phone, String role, String note,
                     Date registerDate, MembershipCard4 membershipCard) {
        super(id, userName, password, fullName, address, email, phone, role, note);
        this.registerDate = registerDate;
        this.membershipCard = membershipCard;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public MembershipCard4 getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard4 membershipCard) {
        this.membershipCard = membershipCard;
    }
}
