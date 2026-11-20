<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*, java.util.*" %>
<%
    request.setCharacterEncoding("UTF-8");
    Customer4 kh = (Customer4) session.getAttribute("customer");
    if (kh == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }

    ShowTime4 showtime = (ShowTime4) session.getAttribute("selectedShowtime");
    Invoice4 invoice = (Invoice4) session.getAttribute("pendingInvoice");
    if (showtime == null || invoice == null) {
        response.sendRedirect("SelectMovieUI4.jsp");
        return;
    }

    String paymentMethod = request.getParameter("inPaymentMethod4");
    invoice.setPaymentMethod(paymentMethod != null ? paymentMethod : "VNPAY");
    invoice.setChannel("Online");

    boolean paid = (new InvoiceDAO4()).createInvoice(invoice);
    if (paid) {
        session.setAttribute("completedInvoice", invoice);
        session.removeAttribute("pendingInvoice");
        response.sendRedirect("TicketInvoiceUI4.jsp");
    } else {
        // Ngoại lệ 6a: Ghế đã có người đặt trước
        response.sendRedirect("SelectSeatUI4.jsp?showtimeId=" + showtime.getId() + "&err=taken");
    }
%>
