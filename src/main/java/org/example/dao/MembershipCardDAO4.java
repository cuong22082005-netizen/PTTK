package org.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.example.model.MembershipCard4;
import org.example.model.MembershipTier4;

public class MembershipCardDAO4 extends DAO4 {
    public MembershipCardDAO4() {
        super();
    }

    public MembershipCard4 getMembershipCard(int customerId) {
        String sql = "SELECT c.id, c.cardCode, c.issueDate, c.totalPoints, " +
                "t.id AS tierId, t.nameMembershipTier, t.minTotalPoints, t.discountRate " +
                "FROM tblMembershipCard4 c " +
                "JOIN tblMembershipTier4 t ON c.tblMembershipTier4id = t.id " +
                "WHERE c.tblCustomer4tblMember4id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MembershipTier4 tier = new MembershipTier4(rs.getInt("tierId"),
                        rs.getString("nameMembershipTier"),
                        rs.getInt("minTotalPoints"),
                        rs.getFloat("discountRate"));
                return new MembershipCard4(rs.getInt("id"), rs.getString("cardCode"),
                        rs.getDate("issueDate"), rs.getInt("totalPoints"), tier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
