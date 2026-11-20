<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*" %>
<%
    Member4 ql = (Member4) session.getAttribute("manager");
    if (ql == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Manager UI</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
<div class="card">
    <div class="top"><b>CINEMAN - QUẢN LÝ</b><button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button></div>
    <h2>TRANG CHỦ NHÂN VIÊN QUẢN LÝ</h2>
    <p>Xin chào: <b><%= ql.getFullName() %></b></p>
    <button class="primary" onclick="location.href='ManageMovieUI4.jsp'">Quản lý thông tin phim</button>
    <button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button>
</div>
</body>
</html>