package org.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.example.model.Movie4;

public class MovieDAO4 extends DAO4 {
    public MovieDAO4() {
        super();
    }

    // Danh sách phim trong hệ thống (Manager và Customer)
    public ArrayList<Movie4> getMovieList() {
        ArrayList<Movie4> list = new ArrayList<>();
        String sql = "SELECT * FROM tblMovie4 ORDER BY id";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(readMovie(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Kiểm tra trùng tên phim (Ngoại lệ 5b)
    public boolean checkMovieExist(String movieName) {
        String sql = "SELECT id FROM tblMovie4 WHERE LOWER(movieTitle) = LOWER(?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, movieName.trim());
            return ps.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // true = thêm thành công
    public boolean addMovie(Movie4 movie) {
        String sql = "INSERT INTO tblMovie4 (movieTitle, genre, duration, director, language, note) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, movie.getMovieName().trim());
            ps.setString(2, movie.getGenre());
            ps.setInt(3, movie.getDuration());
            ps.setString(4, movie.getDirector());
            ps.setString(5, movie.getLanguage());
            ps.setString(6, movie.getNote());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Movie4 readMovie(ResultSet rs) throws Exception {
        Movie4 m = new Movie4();
        m.setId(rs.getInt("id"));
        m.setMovieName(rs.getString("movieTitle"));
        m.setGenre(rs.getString("genre"));
        m.setDuration(rs.getInt("duration"));
        m.setDirector(rs.getString("director"));
        m.setLanguage(rs.getString("language"));
        m.setNote(rs.getString("note"));
        return m;
    }
}
