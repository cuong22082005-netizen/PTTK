<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*, java.util.*" %>
<%
    Customer4 kh = (Customer4) session.getAttribute("customer");
    if (kh == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }
    // Bắt đầu một lượt mua vé mới: xóa dữ liệu của lượt trước
    for (String k : new String[]{"selectedMovie", "listShowtime", "selectedShowtime", "seatMap",
            "selectedTickets", "pendingInvoice", "completedInvoice"}) {
        session.removeAttribute(k);
    }
    ArrayList<Movie4> listMovie = (new MovieDAO4()).getMovieList();
    session.setAttribute("listMovie", listMovie);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Select Movie UI</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
<div class="card">
    <div class="top"><b>CINEMAN</b><button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button></div>
    <h2>CHỌN PHIM ĐANG CHIẾU</h2>
    <table>
        <thead>
            <tr>
                <th>TT</th>
                <th>Mã phim</th>
                <th>Tên phim</th>
                <th>Thể loại</th>
                <th>Thời lượng</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <% for (int i = 0; i < listMovie.size(); i++) {
                Movie4 m = listMovie.get(i);
            %>
            <tr>
                <td align="center"><%= i + 1 %></td>
                <td align="center"><%= m.getId() %></td>
                <td><%= m.getMovieName() %></td>
                <td><%= m.getGenre() %></td>
                <td align="center"><%= m.getDuration() %> phút</td>
                <td align="center"><a class="primary" href="SelectShowtimeUI4.jsp?movieId=<%= m.getId() %>">Chọn</a></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <br/>
    <button onclick="location.href='MainCustomerUI4.jsp'">Quay lại</button>
</div>
</body>
</html>
