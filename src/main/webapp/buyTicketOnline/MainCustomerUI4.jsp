<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*" %>
<%
    Customer4 kh = (Customer4) session.getAttribute("customer");
    if (kh == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Customer UI</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
<div class="card">
    <div class="top"><b>CINEMAN</b><button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button></div>
    <h2>TRANG CHỦ KHÁCH HÀNG - CINEMAN</h2>
    <p>Xin chào: <b><%= kh.getFullName() %></b></p>
    <button class="primary" onclick="location.href='SelectMovieUI4.jsp'">Mua vé trực tuyến</button>
    <button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button>
</div>
</body>
</html>