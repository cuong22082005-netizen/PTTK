<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*, java.util.*, java.text.SimpleDateFormat" %>
<%
    Customer4 kh = (Customer4) session.getAttribute("customer");
    if (kh == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }

    int movieId;
    try {
        movieId = Integer.parseInt(request.getParameter("movieId"));
    } catch (Exception e) {
        response.sendRedirect("SelectMovieUI4.jsp");
        return;
    }
    ArrayList<Movie4> listMovie = (ArrayList<Movie4>) session.getAttribute("listMovie");
    if (listMovie == null) {
        listMovie = (new MovieDAO4()).getMovieList();
        session.setAttribute("listMovie", listMovie);
    }
    Movie4 selectedMovie = null;
    for (Movie4 m : listMovie) {
        if (m.getId() == movieId) {
            selectedMovie = m;
            break;
        }
    }
    if (selectedMovie == null) {
        response.sendRedirect("SelectMovieUI4.jsp");
        return;
    }
    session.setAttribute("selectedMovie", selectedMovie);

    ArrayList<ShowTime4> listShowtime = (new ShowTimeDAO4()).getShowTimeListByMovie(selectedMovie.getId());
    session.setAttribute("listShowtime", listShowtime);
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SeatDAO4 seatDAO = new SeatDAO4();
    TicketDAO4 ticketDAO = new TicketDAO4();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Select Showtime UI</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
<div class="card">
    <div class="top"><b>CINEMAN</b><button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button></div>
    <h2>CHỌN SUẤT CHIẾU - PHIM: <%= selectedMovie.getMovieName() %></h2>
    <%
        // Ngoại lệ 2a: Chưa có lịch chiếu
        if (listShowtime.isEmpty()) {
    %>
        <p style="color:red;"><b>Hiện chưa có suất chiếu</b></p>
    <%
        } else {
    %>
    <table>
        <thead>
            <tr>
                <th>TT</th>
                <th>Ngày chiếu</th>
                <th>Giờ chiếu</th>
                <th>Phòng chiếu</th>
                <th>Ghế trống</th>
                <th>Giá vé cơ bản</th>
                <th>Điểm</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <% for (int i = 0; i < listShowtime.size(); i++) {
                ShowTime4 st = listShowtime.get(i);
                // Ghế trống = tổng số ghế của phòng - số vé đã bán của suất chiếu
                int available = seatDAO.getSeatByScreeningRoom(st.getScreeningRoom().getId()).size()
                        - ticketDAO.getTicketSold(st.getId()).size();
            %>
            <tr>
                <td align="center"><%= i + 1 %></td>
                <td align="center"><%= df.format(st.getShowDate()) %></td>
                <td align="center"><%= st.getStartTime() %></td>
                <td align="center"><%= st.getScreeningRoom().getRoomName() %></td>
                <td align="center"><%= available %>/<%= st.getScreeningRoom().getCapacity() %></td>
                <td align="right"><%= String.format("%,.0f", st.getBasePrice()) %> VNĐ</td>
                <td align="center"><%= st.getBonusPoints() %></td>
                <td align="center">
                    <% if (available > 0) { %>
                        <a class="primary" href="SelectSeatUI4.jsp?showtimeId=<%= st.getId() %>">Chọn</a>
                    <% } else { %>
                        <span style="color:gray;">Hết vé</span>
                    <% } %>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } %>
    <br/>
    <button onclick="location.href='SelectMovieUI4.jsp'">Quay lại</button>
</div>
</body>
</html>
