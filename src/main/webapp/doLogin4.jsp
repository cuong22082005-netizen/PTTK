<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*" %>
<%
    request.setCharacterEncoding("UTF-8");
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    MemberDAO4 dao = new MemberDAO4();
    Member4 member = dao.checkLogin(username, password);

    if (member != null && "manager".equalsIgnoreCase(member.getRole())) {
        session.setAttribute("manager", member);
        response.sendRedirect("addMovie/MainManagerUI4.jsp");
        return;
    } else if (member != null && "customer".equalsIgnoreCase(member.getRole())) {
        session.setAttribute("customer", member);
        response.sendRedirect("buyTicketOnline/MainCustomerUI4.jsp");
        return;
    } else {
        response.sendRedirect("LoginUI4.jsp?err=fail");
        return;
    }
%>