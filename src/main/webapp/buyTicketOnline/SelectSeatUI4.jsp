<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*, java.util.*, java.text.SimpleDateFormat" %>
<%
    Customer4 kh = (Customer4) session.getAttribute("customer");
    if (kh == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }

    Movie4 selectedMovie = (Movie4) session.getAttribute("selectedMovie");
    ArrayList<ShowTime4> listShowtime = (ArrayList<ShowTime4>) session.getAttribute("listShowtime");
    int showtimeId;
    try {
        showtimeId = Integer.parseInt(request.getParameter("showtimeId"));
    } catch (Exception e) {
        response.sendRedirect("SelectMovieUI4.jsp");
        return;
    }
    ShowTime4 selectedShowtime = null;
    if (listShowtime != null) {
        for (ShowTime4 st : listShowtime) {
            if (st.getId() == showtimeId) {
                selectedShowtime = st;
                break;
            }
        }
    }
    if (selectedMovie == null || selectedShowtime == null) {
        response.sendRedirect("SelectMovieUI4.jsp");
        return;
    }
    session.setAttribute("selectedShowtime", selectedShowtime);
    session.removeAttribute("selectedTickets");
        session.removeAttribute("pendingInvoice");

    ArrayList<Seat4> seatMap = (new SeatDAO4()).getSeatByScreeningRoom(selectedShowtime.getScreeningRoom().getId());
    // Ghế đã bán = ghế có trong danh sách vé đã bán của suất chiếu
    HashSet<Integer> soldSeatIds = new HashSet<>();
    for (Ticket4 t : (new TicketDAO4()).getTicketSold(selectedShowtime.getId())) {
        soldSeatIds.add(t.getSeat().getId());
    }

    String err = request.getParameter("err");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Select Seat UI</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
<div class="card">
    <div class="top"><b>CINEMAN</b><button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button></div>
    <h2>SƠ ĐỒ CHỌN GHẾ - PHÒNG <%= selectedShowtime.getScreeningRoom().getRoomName() %></h2>
    <p>Phim: <b><%= selectedMovie.getMovieName() %></b> - Suất <%= selectedShowtime.getStartTime() %> (<%= new SimpleDateFormat("dd/MM/yyyy").format(selectedShowtime.getShowDate()) %>)</p>
    <% if ("taken".equalsIgnoreCase(err)) { %>
        <p style="color:red;"><b>Ghế bạn chọn đã có người đặt, vui lòng chọn lại!</b></p>
    <% } %>

    <form action="PaymentUI4.jsp" method="post">
        <input type="hidden" name="fromSeat" value="1" />
        <div class="screen">MÀN HÌNH</div>
        <table>
            <tr>
            <%
                String currentRow = null;
                for (Seat4 s : seatMap) {
                    if (!s.getSeatRow().equals(currentRow)) {
                        if (currentRow != null) { out.print("</tr><tr>"); }
                        currentRow = s.getSeatRow();
                        out.print("<td><b>Hàng " + currentRow + "</b></td>");
                    }
            %>
                <td align="center">
                    <% if (soldSeatIds.contains(s.getId())) { %>
                        <span style="color:gray;">[Đã bán]</span>
                    <% } else { %>
                        <label>
                            <input type="checkbox" name="seatIds" value="<%= s.getId() %>" />
                            [<%= (s.getSeatRow() + String.format("%02d", s.getSeatNumber())) %>]<br/>
                            <small><%= s.getSeatType().getNameSeatType() %> - <%= String.format("%,.0f", selectedShowtime.getBasePrice() * s.getSeatType().getPriceRate()) %>đ</small>
                        </label>
                    <% } %>
                </td>
            <% } %>
            </tr>
        </table>
        <br/>
        <input type="submit" value="Tiếp tục" />
        <button type="button" onclick="location.href='SelectShowtimeUI4.jsp?movieId=<%= selectedMovie.getId() %>'">Quay lại</button>
    </form>
</div>
</body>
</html>
