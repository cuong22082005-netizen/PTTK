package org.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.example.model.Seat4;
import org.example.model.SeatType4;

public class SeatDAO4 extends DAO4 {
    public SeatDAO4() {
        super();
    }

    // Danh sách ghế của phòng chiếu (mỗi ghế kèm loại ghế)
    public ArrayList<Seat4> getSeatByScreeningRoom(int idScreeningRoom) {
        ArrayList<Seat4> list = new ArrayList<>();
        String sql = "SELECT s.id, s.seatRow, s.seatNumber, " +
                "ty.id AS typeId, ty.nameSeatType, ty.priceRate, ty.note " +
                "FROM tblSeat4 s " +
                "JOIN tblSeatType4 ty ON s.tblSeatType4id = ty.id " +
                "WHERE s.tblScreeningRoom4id = ? " +
                "ORDER BY s.seatRow, s.seatNumber";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idScreeningRoom);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Seat4 seat = new Seat4();
                seat.setId(rs.getInt("id"));
                seat.setSeatRow(rs.getString("seatRow"));
                seat.setSeatNumber(rs.getInt("seatNumber"));
                seat.setSeatType(new SeatType4(rs.getInt("typeId"), rs.getString("nameSeatType"),
                        rs.getFloat("priceRate"), rs.getString("note")));
                list.add(seat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
