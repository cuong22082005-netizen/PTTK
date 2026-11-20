package org.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.example.model.ScreeningRoom4;
import org.example.model.ShowTime4;

public class ShowTimeDAO4 extends DAO4 {
    public ShowTimeDAO4() {
        super();
    }

    // Danh sách suất chiếu của phim đã chọn
    public ArrayList<ShowTime4> getShowTimeListByMovie(int idMovie) {
        ArrayList<ShowTime4> list = new ArrayList<>();
        String sql = "SELECT st.id, st.showDate, st.startTime, st.endTime, st.basePrice, st.bonusPoints, " +
                "sr.id AS roomId, sr.roomName, sr.capacity " +
                "FROM tblShowTime4 st " +
                "JOIN tblScreeningRoom4 sr ON st.tblScreeningRoom4id = sr.id " +
                "WHERE st.tblMovie4id = ? " +
                "ORDER BY st.showDate, st.startTime";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMovie);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ShowTime4 st = new ShowTime4();
                st.setId(rs.getInt("id"));
                st.setShowDate(rs.getDate("showDate"));
                st.setStartTime(rs.getString("startTime").substring(0, 5));
                st.setEndTime(rs.getString("endTime").substring(0, 5));
                st.setBasePrice(rs.getFloat("basePrice"));
                st.setBonusPoints(rs.getInt("bonusPoints"));

                ScreeningRoom4 sr = new ScreeningRoom4();
                sr.setId(rs.getInt("roomId"));
                sr.setRoomName(rs.getString("roomName"));
                sr.setCapacity(rs.getInt("capacity"));
                st.setScreeningRoom(sr);

                list.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
