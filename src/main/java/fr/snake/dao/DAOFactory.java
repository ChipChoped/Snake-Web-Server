package fr.snake.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private String url;
    private String username;
    private String password;

    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {

        }

        return new DAOFactory(
                "jdbc:mysql://localhost:3306/snake", "admin", "admin");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Récupération du Dao
    public UserDAO getUserDao() {
        return new UserDAODB(this);
    }

    public GameDAO getGameDao() {
        return new GameDAODB(this);
    }
}