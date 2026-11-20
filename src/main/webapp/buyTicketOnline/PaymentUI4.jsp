<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*, java.util.*, java.text.SimpleDateFormat" %>
<%
    request.setCharacterEncoding("UTF-8");
    Customer4 kh = (Customer4) session.getAttribute("customer");
    if (kh == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }

    Movie4 movie = (Movie4) session.getAttribute("selectedMovie");
    ShowTime4 showtime = (ShowTime4) session.getAttribute("selectedShowtime");
    if (movie == null || showtime == null) {
        response.sendRedirect("SelectMovieUI4.jsp");
        return;
    }

    // Từ sơ đồ ghế sang: tạo vé cho các ghế vừa chọn
    if (request.getParameter("fromSeat") != null) {
        String[] seatIds = request.getParameterValues("seatIds");
        if (seatIds == null || seatIds.length == 0) {
            response.sendRedirect("SelectSeatUI4.jsp?showtimeId=" + showtime.getId());
            return;
        }
        TicketDAO4 ticketDAO = new TicketDAO4();
        HashSet<Integer> soldSeatIds = new HashSet<>();
        for (Ticket4 t : ticketDAO.getTicketSold(showtime.getId())) {
            soldSeatIds.add(t.getSeat().getId());
        }
        ArrayList<Ticket4> chosen = new ArrayList<>();
        for (String idStr : seatIds) {
            int seatId;
            try {
                seatId = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                continue;
            }
            if (soldSeatIds.contains(seatId)) {
                continue;
            }
            Ticket4 ticket = ticketDAO.createTicket(showtime.getId(), seatId);
            if (ticket != null) {
                chosen.add(ticket);
            }
        }
        if (chosen.isEmpty()) {
            response.sendRedirect("SelectSeatUI4.jsp?showtimeId=" + showtime.getId() + "&err=taken");
            return;
        }
        session.setAttribute("selectedTickets", chosen);
    }

    ArrayList<Ticket4> selectedTickets = (ArrayList<Ticket4>) session.getAttribute("selectedTickets");
    if (selectedTickets == null || selectedTickets.isEmpty()) {
        response.sendRedirect("SelectSeatUI4.jsp?showtimeId=" + showtime.getId());
        return;
    }

    // Thẻ thành viên của khách đang đăng nhập được lấy tự động (null nếu khách chưa có thẻ)
    MembershipCard4 card = (new MembershipCardDAO4()).getMembershipCard(kh.getId());
    kh.setMembershipCard(card);

    Invoice4 invoice = (new InvoiceDAO4()).calculateInvoice(card != null ? card.getCardCode() : null, selectedTickets);
    invoice.setCustomer(kh);
    session.setAttribute("pendingInvoice", invoice);

    StringBuilder seatNames = new StringBuilder();
    for (int i = 0; i < selectedTickets.size(); i++) {
        if (i > 0) seatNames.append(", ");
        Seat4 seat = selectedTickets.get(i).getSeat();
        seatNames.append(seat.getSeatRow()).append(String.format("%02d", seat.getSeatNumber()));
    }
    session.setAttribute("seatNames", seatNames.toString());

    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment UI</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
<div class="card">
    <div class="top"><b>CINEMAN</b><button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button></div>
    <h2>XÁC NHẬN MUA VÉ VÀ THANH TOÁN</h2>

    <table>
        <tr>
            <td><b>Tên phim</b></td>
            <td><%= movie.getMovieName() %></td>
        </tr>
        <tr>
            <td><b>Suất chiếu</b></td>
            <td><%= showtime.getStartTime() %> (<%= df.format(showtime.getShowDate()) %>)</td>
        </tr>
        <tr>
            <td><b>Phòng chiếu</b></td>
            <td><%= showtime.getScreeningRoom().getRoomName() %></td>
        </tr>
        <tr>
            <td><b>Vị trí ghế</b></td>
            <td><%= seatNames.toString() %></td>
        </tr>
        <tr>
            <td><b>Tổng tiền vé</b></td>
            <td><%= String.format("%,.0f", invoice.getTotalAmount()) %> VNĐ</td>
        </tr>
        <tr>
            <td><b>Mã thẻ khách hàng thân thiết (nếu có)</b></td>
            <td>
                <% if (card != null) { %>
                    <b><%= card.getCardCode() %></b>
                    - Hạng <%= card.getMembershipTier().getNameMembershipTier() %>
                    (giảm <%= String.format("%.0f", card.getMembershipTier().getDiscountRate() * 100) %>%)
                    - Điểm hiện có: <%= card.getTotalPoints() %>
                <% } else { %>
                    <i>Chưa có thẻ thành viên</i>
                <% } %>
            </td>
        </tr>
        <tr>
            <td><b>Số điểm tích lũy được</b></td>
            <td><%= invoice.getEarnedPoints() %></td>
        </tr>
        <tr>
            <td><b>Tổng tiền</b></td>
            <td><%= String.format("%,.0f", invoice.getTotalAmount()) %> VNĐ</td>
        </tr>
        <tr>
            <td><b>Giảm giá</b></td>
            <td><%= String.format("%,.0f", invoice.getDiscountAmount()) %> VNĐ</td>
        </tr>
        <tr>
            <td><b>Thành tiền</b></td>
            <td><b><%= String.format("%,.0f", invoice.getFinalAmount()) %> VNĐ</b></td>
        </tr>
    </table>

    <br/>
    <form action="doMakePayment4.jsp" method="post">
        <label><b>Hình thức thanh toán:</b></label>
        <select name="inPaymentMethod4">
            <option value="VNPAY">Cổng thanh toán VNPAY</option>
            <option value="MOMO">Ví điện tử MoMo</option>
            <option value="BANK">Thẻ ngân hàng nội địa</option>
        </select>
        <br/><br/>
        <input type="submit" value="Thanh toán" />
        <button type="button" onclick="location.href='MainCustomerUI4.jsp'">Hủy thanh toán</button>
    </form>
</div>
</body>
</html>
