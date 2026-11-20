<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*" %>
<%
    Member4 ql = (Member4) session.getAttribute("manager");
    if (ql == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }
    String err = request.getParameter("err");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Movie UI</title>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
<div class="card">
    <div class="top"><b>CINEMAN - QUẢN LÝ</b><button onclick="location.href='../LoginUI4.jsp'">Đăng xuất</button></div>
    <h2>THÊM THÔNG TIN PHIM</h2>
    <% if ("empty".equalsIgnoreCase(err)) { %>
        <p style="color:red;">Vui lòng nhập đầy đủ các trường thông tin bắt buộc!</p>
    <% } else if ("format".equalsIgnoreCase(err)) { %>
        <p style="color:red;">Thời lượng phim phải là số nguyên dương!</p>
    <% } else if ("duplicate".equalsIgnoreCase(err)) { %>
        <p style="color:red;">Tên phim đã tồn tại trong hệ thống, vui lòng kiểm tra lại!</p>
    <% } else if ("fail".equalsIgnoreCase(err)) { %>
        <p style="color:red;">Lỗi hệ thống khi lưu thông tin phim!</p>
    <% } %>

    <form action="doAddMovie4.jsp" method="post">
        <table class="form">
            <tr>
                <td>Tên phim (*):</td>
                <td><input type="text" name="inMovieTitle4" required /></td>
            </tr>
            <tr>
                <td>Thể loại (*):</td>
                <td><input type="text" name="inGenre4" required /></td>
            </tr>
            <tr>
                <td>Thời lượng (phút) (*):</td>
                <td><input type="text" name="inDuration4" required /></td>
            </tr>
            <tr>
                <td>Đạo diễn (*):</td>
                <td><input type="text" name="inDirector4" required /></td>
            </tr>
            <tr>
                <td>Ngôn ngữ (*):</td>
                <td><input type="text" name="inLanguage4" required /></td>
            </tr>
            <tr>
                <td>Mô tả:</td>
                <td><textarea name="inDescription4" rows="3" cols="30"></textarea></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Thêm" />
                    <button type="button" onclick="location.href='ManageMovieUI4.jsp'">Hủy</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>