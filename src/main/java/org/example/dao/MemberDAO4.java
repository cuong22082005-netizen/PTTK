package org.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.example.model.Customer4;
import org.example.model.Manager4;
import org.example.model.Member4;
import org.example.model.SalesStaff4;

public class MemberDAO4 extends DAO4 {
    public MemberDAO4() {
        super();
    }

    public Member4 checkLogin(String userName, String password) {
        String sql = "SELECT m.*, s.position, c.registerDate " +
                "FROM tblMember4 m " +
                "LEFT JOIN tblStaff4 s ON m.id = s.tblMember4id " +
                "LEFT JOIN tblCustomer4 c ON m.id = c.tblMember4id " +
                "WHERE m.username = ? AND m.password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                Member4 member;
                if ("customer".equalsIgnoreCase(role)) {
                    Customer4 c = new Customer4();
                    c.setRegisterDate(rs.getDate("registerDate"));
                    member = c;
                } else if ("manager".equalsIgnoreCase(role)) {
                    Manager4 m = new Manager4();
                    m.setPosition(rs.getString("position"));
                    member = m;
                } else if ("salesstaff".equalsIgnoreCase(role)) {
                    SalesStaff4 s = new SalesStaff4();
                    s.setPosition(rs.getString("position"));
                    member = s;
                } else {
                    member = new Member4();
                }
                member.setId(rs.getInt("id"));
                member.setUserName(rs.getString("username"));
                member.setFullName(rs.getString("fullName"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setRole(role);
                member.setNote(rs.getString("note"));
                return member;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
