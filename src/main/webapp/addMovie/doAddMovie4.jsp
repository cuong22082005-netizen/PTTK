<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.example.dao.*, org.example.model.*" %>
<%
    request.setCharacterEncoding("UTF-8");
    Member4 ql = (Member4) session.getAttribute("manager");
    if (ql == null) {
        response.sendRedirect("../LoginUI4.jsp?err=timeout");
        return;
    }

    String movieTitle = request.getParameter("inMovieTitle4");
    String genre = request.getParameter("inGenre4");
    String durationStr = request.getParameter("inDuration4");
    String director = request.getParameter("inDirector4");
    String language = request.getParameter("inLanguage4");
    String description = request.getParameter("inDescription4");

    // Ngoại lệ 5a: Thiếu thông tin bắt buộc
    if (movieTitle == null || movieTitle.trim().isEmpty() ||
        genre == null || genre.trim().isEmpty() ||
        durationStr == null || durationStr.trim().isEmpty() ||
        director == null || director.trim().isEmpty() ||
        language == null || language.trim().isEmpty()) {
        response.sendRedirect("AddMovieUI4.jsp?err=empty");
        return;
    }

    // Ngoại lệ 5a: Thời lượng không phải số nguyên dương
    int duration;
    try {
        duration = Integer.parseInt(durationStr.trim());
    } catch (NumberFormatException e) {
        response.sendRedirect("AddMovieUI4.jsp?err=format");
        return;
    }
    if (duration <= 0) {
        response.sendRedirect("AddMovieUI4.jsp?err=format");
        return;
    }

    MovieDAO4 movieDAO = new MovieDAO4();

    // Ngoại lệ 5b: Trùng tên phim
    if (movieDAO.checkMovieExist(movieTitle)) {
        response.sendRedirect("AddMovieUI4.jsp?err=duplicate");
        return;
    }

    Movie4 movie = new Movie4();
    movie.setMovieName(movieTitle.trim());
    movie.setGenre(genre.trim());
    movie.setDuration(duration);
    movie.setDirector(director.trim());
    movie.setLanguage(language.trim());
    movie.setNote(description != null ? description.trim() : "");

    if (movieDAO.addMovie(movie)) {
        response.sendRedirect("ManageMovieUI4.jsp?msg=success");
    } else {
        response.sendRedirect("AddMovieUI4.jsp?err=fail");
    }
%>
