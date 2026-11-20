<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*, java.util.*, java.text.SimpleDateFormat" %>
<%
    Customer4 kh = (Customer4) session.getAttribute("customer");
    if (kh == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }

    Invoice4 invoice = (Invoice4) session.getAttribute("completedInvoice");
    Movie4 movie = (Movie4) session.getAttribute("selectedMovie");
    ShowTime4 showtime = (ShowTime4) session.getAttribute("selectedShowtime");
    if (invoice == null || movie == null || showtime == null) {
        response.sendRedirect("MainCustomerUI4.jsp");
        return;
    }
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ticket and Invoice UI</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
<div class="card">
    <div class="top"><b>CINEMAN</b><button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button></div>
    <div class="ok">THANH TOÁN THÀNH CÔNG - VÉ & HÓA ĐƠN ĐIỆN TỬ</div>

    <h3>Hóa đơn #HD<%= invoice.getId() %></h3>
    <table>
        <tr>
            <td><b>Khách hàng</b></td>
            <td><%= kh.getFullName() %></td>
        </tr>
        <tr>
            <td><b>Ngày lập</b></td>
            <td><%= invoice.getCreateAt() != null ? dtf.format(invoice.getCreateAt()) : "" %></td>
        </tr>
        <tr>
            <td><b>Kênh đặt vé</b></td>
            <td><%= invoice.getChannel() %></td>
        </tr>
        <tr>
            <td><b>Phương thức thanh toán</b></td>
            <td><%= invoice.getPaymentMethod() %></td>
        </tr>
        <tr>
            <td><b>Tổng tiền vé</b></td>
            <td><%= String.format("%,.0f", invoice.getTotalAmount()) %> VNĐ</td>
        </tr>
        <tr>
            <td><b>Giảm giá</b></td>
            <td><%= String.format("%,.0f", invoice.getDiscountAmount()) %> VNĐ</td>
        </tr>
        <tr>
            <td><b>Thành tiền đã thanh toán</b></td>
            <td><b><%= String.format("%,.0f", invoice.getFinalAmount()) %> VNĐ</b></td>
        </tr>
        <tr>
            <td><b>Số điểm tích lũy nhận thêm</b></td>
            <td><%= invoice.getEarnedPoints() %></td>
        </tr>
    </table>

    <h3>Vé xem phim</h3>
    <p>Phim: <b><%= movie.getMovieName() %></b> - Suất <%= showtime.getStartTime() %>, ngày <%= df.format(showtime.getShowDate()) %>
       - Phòng <%= showtime.getScreeningRoom().getRoomName() %></p>
    <table>
        <thead>
            <tr>
                <th>Mã vé</th>
                <th>Ghế</th>
                <th>Loại ghế</th>
                <th>Giá vé</th>
            </tr>
        </thead>
        <tbody>
            <% for (Ticket4 t : invoice.getListTicket()) { %>
            <tr>
                <td align="center">#VE<%= t.getId() %></td>
                <td align="center"><b><%= (t.getSeat().getSeatRow() + String.format("%02d", t.getSeat().getSeatNumber())) %></b></td>
                <td align="center"><%= t.getSeat().getSeatType().getNameSeatType() %></td>
                <td align="right"><%= String.format("%,.0f", t.getTicketPrice()) %> VNĐ</td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <br/>
    <button class="primary" onclick="window.print()">In / Tải PDF vé & hóa đơn</button>
    <button onclick="location.href='MainCustomerUI4.jsp'">Về trang chủ</button>
</div>
</body>
</html>
