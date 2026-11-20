package org.example.model;

import java.util.ArrayList;
import java.util.Date;

public class Invoice4 {
    private int id;
    private String paymentMethod;
    private Date createAt;
    private String channel;       // "Online" | "Counter"
    private int earnedPoints;
    private float discountAmount;
    private float totalAmount;    // tổng tiền vé (trước giảm giá)
    private float finalAmount;    // thành tiền phải thanh toán (sau giảm giá)
    private Customer4 customer;
    private SalesStaff4 salesStaff;
    private ArrayList<Ticket4> listTicket;

    public Invoice4() {
        this.listTicket = new ArrayList<>();
    }

    public Invoice4(int id, String paymentMethod, Date createAt, String channel, int earnedPoints,
                    float discountAmount, float totalAmount, float finalAmount,
                    Customer4 customer, SalesStaff4 salesStaff, ArrayList<Ticket4> listTicket) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.createAt = createAt;
        this.channel = channel;
        this.earnedPoints = earnedPoints;
        this.discountAmount = discountAmount;
        this.totalAmount = totalAmount;
        this.finalAmount = finalAmount;
        this.customer = customer;
        this.salesStaff = salesStaff;
        this.listTicket = listTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getEarnedPoints() {
        return earnedPoints;
    }

    public void setEarnedPoints(int earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(float finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Customer4 getCustomer() {
        return customer;
    }

    public void setCustomer(Customer4 customer) {
        this.customer = customer;
    }

    public SalesStaff4 getSalesStaff() {
        return salesStaff;
    }

    public void setSalesStaff(SalesStaff4 salesStaff) {
        this.salesStaff = salesStaff;
    }

    public ArrayList<Ticket4> getListTicket() {
        return listTicket;
    }

    public void setListTicket(ArrayList<Ticket4> listTicket) {
        this.listTicket = listTicket;
    }
}
