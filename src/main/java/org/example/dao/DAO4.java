package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO4 {
    public static Connection con;

    public DAO4() {
        try {
            if (con != null && con.isClosed()) {
                con = null;
            }
        } catch (Exception e) {
            con = null;
        }
        if (con == null) {
            String dbUrl = "jdbc:mysql://localhost:3306/cineman?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Ho_Chi_Minh";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "root", "123456789"); // Sửa user/pass theo máy bạn
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
