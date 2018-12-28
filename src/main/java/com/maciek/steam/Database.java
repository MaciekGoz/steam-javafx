package com.maciek.steam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://77.55.192.67/mgozdalik";

    static final String DB_USERNAME = "mgozdalik";
    static final String DB_PASSWORD = "mgozdalik";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    private void close() {
        try {
            if (con != null) con.setAutoCommit(true);
            if (ps != null) ps.close();
            if (rs != null) rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean register(String username, String password, String email) {
        if (username.length() <= 0 || password.length() <= 0 || email.length() <= 0) {
            return false;
        }

        sql = "INSERT INTO `users` (`ID_USER`, `USERNAME`, `PASSWORD`, `EMAIL`) VALUES (NULL, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            return rs.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            close();
        }
    }

    public boolean login(String username, String password) {
        if (username.length() <= 0 || password.length() <= 0) {
            return false;
        }

        sql = "SELECT ID_USER FROM users WHERE `USERNAME` = ? AND `PASSWORD` = ?";

        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            close();
        }
    }

    public List<String> getUserGames(String username) {
        sql = "SELECT TITLE FROM games, user_games WHERE ID_USER=(SELECT ID_USER FROM users WHERE username=?) AND games.ID_GAME = user_games.ID_GAME";

        List<String> userGames = new ArrayList<>();

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);

            rs = ps.executeQuery();

            while (rs.next()) {
                userGames.add(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userGames;
    }

}
