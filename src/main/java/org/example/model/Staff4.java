package org.example.model;

public class Staff4 extends Member4 {
    private String position;

    public Staff4() {
        super();
    }

    public Staff4(int id, String userName, String password, String fullName, Address4 address,
                  String email, String phone, String role, String note, String position) {
        super(id, userName, password, fullName, address, email, phone, role, note);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
