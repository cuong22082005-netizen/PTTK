<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - CineMan</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="card" style="width:500px">
    <h2>ĐĂNG NHẬP HỆ THỐNG CINEMAN</h2>
    <%
        String err = request.getParameter("err");
        if ("timeout".equalsIgnoreCase(err)) {
    %>
        <p style="color:red;">Hết phiên làm việc. Vui lòng đăng nhập lại!</p>
    <%
        } else if ("fail".equalsIgnoreCase(err)) {
    %>
        <p style="color:red;">Sai tên đăng nhập hoặc mật khẩu!</p>
    <%
        }
    %>
    <form action="doLogin4.jsp" method="post">
        <table class="form">
            <tr>
                <td>Tên đăng nhập:</td>
                <td><input type="text" name="username" required /></td>
            </tr>
            <tr>
                <td>Mật khẩu:</td>
                <td><input type="password" name="password" required /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Đăng nhập" /></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>