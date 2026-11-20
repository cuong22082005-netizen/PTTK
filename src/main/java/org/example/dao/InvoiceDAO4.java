package org.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import org.example.model.Invoice4;
import org.example.model.MembershipCard4;
import org.example.model.Ticket4;

public class InvoiceDAO4 extends DAO4 {
    public InvoiceDAO4() {
        super();
    }

    public Invoice4 calculateInvoice(String cardCode, ArrayList<Ticket4> listTicket) {
        Invoice4 invoice = new Invoice4();
        float total = 0;
        for (Ticket4 t : listTicket) {
            total += t.getTicketPrice();
        }
        float discount = 0;
        int earned = 0;
        if (cardCode != null) {
            String sql = "SELECT t.discountRate FROM tblMembershipCard4 c " +
                    "JOIN tblMembershipTier4 t ON c.tblMembershipTier4id = t.id WHERE c.cardCode = ?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, cardCode);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    discount = total * rs.getFloat("discountRate");
                    earned = listTicket.get(0).getShowTime().getBonusPoints();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        invoice.setChannel("Online");
        invoice.setListTicket(listTicket);
        invoice.setTotalAmount(total);
        invoice.setDiscountAmount(discount);
        invoice.setFinalAmount(total - discount);
        invoice.setEarnedPoints(earned);
        return invoice;
    }

    public boolean createInvoice(Invoice4 invoice) {
        if (invoice == null || invoice.getListTicket() == null || invoice.getListTicket().isEmpty()
                || invoice.getCustomer() == null) {
            throw new IllegalArgumentException("Hóa đơn không hợp lệ");
        }
        String checkSeatSql = "SELECT id FROM tblTicket4 WHERE tblShowTime4id = ? AND tblSeat4id = ? " +
                "AND status = 'SOLD' FOR UPDATE";
        String insertInvoiceSql = "INSERT INTO tblInvoice4 (paymentMethod, createAt, channel, earnedPoints, " +
                "discountAmount, totalAmount, finalAmount, tblCustomer4id) VALUES (?, NOW(), ?, ?, ?, ?, ?, ?)";
        String insertTicketSql = "INSERT INTO tblTicket4 (ticketPrice, status, tblSeat4id, tblShowTime4id, tblInvoice4id) " +
                "VALUES (?, 'SOLD', ?, ?, ?)";
        String updateCardSql = "UPDATE tblMembershipCard4 SET totalPoints = ?, tblMembershipTier4id = " +
                "(SELECT id FROM (SELECT id FROM tblMembershipTier4 WHERE minTotalPoints <= ? " +
                "ORDER BY minTotalPoints DESC LIMIT 1) AS best) WHERE id = ?";
        try {
            con.setAutoCommit(false);

            PreparedStatement psCheck = con.prepareStatement(checkSeatSql);
            for (Ticket4 t : invoice.getListTicket()) {
                psCheck.setInt(1, t.getShowTime().getId());
                psCheck.setInt(2, t.getSeat().getId());
                if (psCheck.executeQuery().next()) {
                    con.rollback();
                    return false;
                }
            }

            PreparedStatement psInv = con.prepareStatement(insertInvoiceSql, Statement.RETURN_GENERATED_KEYS);
            psInv.setString(1, invoice.getPaymentMethod());
            psInv.setString(2, invoice.getChannel() != null ? invoice.getChannel() : "Online");
            psInv.setInt(3, invoice.getEarnedPoints());
            psInv.setFloat(4, invoice.getDiscountAmount());
            psInv.setFloat(5, invoice.getTotalAmount());
            psInv.setFloat(6, invoice.getFinalAmount());
            psInv.setInt(7, invoice.getCustomer().getId());
            psInv.executeUpdate();

            ResultSet rsKey = psInv.getGeneratedKeys();
            if (!rsKey.next()) {
                throw new IllegalStateException("Không lấy được mã hóa đơn");
            }
            int invoiceId = rsKey.getInt(1);

            PreparedStatement psTicket = con.prepareStatement(insertTicketSql, Statement.RETURN_GENERATED_KEYS);
            for (Ticket4 t : invoice.getListTicket()) {
                psTicket.setFloat(1, t.getTicketPrice());
                psTicket.setInt(2, t.getSeat().getId());
                psTicket.setInt(3, t.getShowTime().getId());
                psTicket.setInt(4, invoiceId);
                psTicket.executeUpdate();
                ResultSet rsTicketKey = psTicket.getGeneratedKeys();
                if (rsTicketKey.next()) {
                    t.setId(rsTicketKey.getInt(1));
                }
                t.setStatus("SOLD");
            }

            MembershipCard4 card = invoice.getCustomer().getMembershipCard();
            int newPoints = 0;
            if (card != null && invoice.getEarnedPoints() > 0) {
                newPoints = card.getTotalPoints() + invoice.getEarnedPoints();
                PreparedStatement psCard = con.prepareStatement(updateCardSql);
                psCard.setInt(1, newPoints);
                psCard.setInt(2, newPoints);
                psCard.setInt(3, card.getId());
                psCard.executeUpdate();
            }

            con.commit();
            invoice.setId(invoiceId);
            invoice.setCreateAt(new Date());
            if (card != null && invoice.getEarnedPoints() > 0) {
                card.setTotalPoints(newPoints);
            }
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            try {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
