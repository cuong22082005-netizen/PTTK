package org.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.example.model.Seat4;
import org.example.model.SeatType4;
import org.example.model.ShowTime4;
import org.example.model.Ticket4;

public class TicketDAO4 extends DAO4 {
    public TicketDAO4() {
        super();
    }

    // Danh sách vé đã bán của suất chiếu (để biết ghế nào đã có người đặt)
    public ArrayList<Ticket4> getTicketSold(int idShowTime) {
        ArrayList<Ticket4> list = new ArrayList<>();
        String sql = "SELECT id, ticketPrice, status, tblSeat4id FROM tblTicket4 " +
                "WHERE tblShowTime4id = ? AND status = 'SOLD'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idShowTime);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ticket4 ticket = new Ticket4();
                ticket.setId(rs.getInt("id"));
                ticket.setTicketPrice(rs.getFloat("ticketPrice"));
                ticket.setStatus(rs.getString("status"));
                Seat4 seat = new Seat4();
                seat.setId(rs.getInt("tblSeat4id"));
                ticket.setSeat(seat);
                ShowTime4 showTime = new ShowTime4();
                showTime.setId(idShowTime);
                ticket.setShowTime(showTime);
                list.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Tạo vé (chưa lưu CSDL) cho ghế đã chọn: giá vé = giá cơ bản của suất x hệ số của loại ghế.
    // Vé chỉ được lưu khi tạo hóa đơn (createInvoice). Trả về null nếu ghế không thuộc phòng của suất chiếu.
    public Ticket4 createTicket(int idShowTime, int idSeat) {
        String sql = "SELECT st.basePrice, st.bonusPoints, s.seatRow, s.seatNumber, " +
                "ty.id AS typeId, ty.nameSeatType, ty.priceRate, ty.note " +
                "FROM tblShowTime4 st " +
                "JOIN tblSeat4 s ON s.tblScreeningRoom4id = st.tblScreeningRoom4id " +
                "JOIN tblSeatType4 ty ON s.tblSeatType4id = ty.id " +
                "WHERE st.id = ? AND s.id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idShowTime);
            ps.setInt(2, idSeat);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SeatType4 type = new SeatType4(rs.getInt("typeId"), rs.getString("nameSeatType"),
                        rs.getFloat("priceRate"), rs.getString("note"));
                Seat4 seat = new Seat4();
                seat.setId(idSeat);
                seat.setSeatRow(rs.getString("seatRow"));
                seat.setSeatNumber(rs.getInt("seatNumber"));
                seat.setSeatType(type);

                ShowTime4 showTime = new ShowTime4();
                showTime.setId(idShowTime);
                showTime.setBasePrice(rs.getFloat("basePrice"));
                showTime.setBonusPoints(rs.getInt("bonusPoints"));

                Ticket4 ticket = new Ticket4();
                ticket.setTicketPrice(showTime.getBasePrice() * type.getPriceRate());
                ticket.setStatus("AVAILABLE");
                ticket.setShowTime(showTime);
                ticket.setSeat(seat);
                return ticket;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
