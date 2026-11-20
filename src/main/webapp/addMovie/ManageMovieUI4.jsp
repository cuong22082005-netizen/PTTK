<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*, java.util.*" %>
<%
    Member4 ql = (Member4) session.getAttribute("manager");
    if (ql == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }
    ArrayList<Movie4> listMovie = (new MovieDAO4()).getMovieList();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Movie UI</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
<div class="card">
    <div class="top"><b>CINEMAN - QUẢN LÝ</b><button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button></div>
    <h2>QUẢN LÝ THÔNG TIN PHIM</h2>
    <% if ("success".equalsIgnoreCase(request.getParameter("msg"))) { %>
        <div class="ok">Thêm thông tin phim mới thành công!</div>
    <% } %>
    <table>
        <thead>
            <tr>
                <th>Mã phim</th>
                <th>Tên phim</th>
                <th>Thể loại</th>
                <th>Thời lượng (phút)</th>
                <th>Đạo diễn</th>
                <th>Ngôn ngữ</th>
                <th>Mô tả</th>
            </tr>
        </thead>
        <tbody>
            <% for (Movie4 m : listMovie) { %>
            <tr>
                <td align="center"><%= m.getId() %></td>
                <td><%= m.getMovieName() %></td>
                <td><%= m.getGenre() %></td>
                <td align="center"><%= m.getDuration() %></td>
                <td><%= m.getDirector() %></td>
                <td><%= m.getLanguage() %></td>
                <td><%= m.getNote() != null ? m.getNote() : "" %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <br/>
    <button class="primary" onclick="location.href='AddMovieUI4.jsp'">Thêm mới</button>
    <button onclick="location.href='MainManagerUI4.jsp'">Quay lại</button>
</div>
</body>
</html>