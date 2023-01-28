package fr.snake.db;

import fr.snake.beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDB {
    private Connection connexion;

    private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/snake", "admin", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        Statement statement = null;
        ResultSet result = null;

        loadDatabase();

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            result = statement.executeQuery("SELECT username, first_name, last_name," +
                    "email, password, sex, birth_date, inscription_date FROM users;");

            // Récupération des données
            while (result.next()) {
                User user = new User();

                user.setUsername(result.getString("username"));
                user.setFirstName(result.getString("first_name"));
                user.setLastName(result.getString("last_name"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setSex(result.getString("sex"));
                user.setBirthDate(result.getString("birth_date"));
                user.setInscriptionDate(result.getString("inscription_date"));

                users.add(user);
            }
        } catch (SQLException ignored) {
        } finally {
            // Fermeture de la connexion
            try {
                if (result != null)
                    result.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignored) {
            }
        }

        return users;
    }

    public void addUser(User user) {
        loadDatabase();

        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getSex());
            preparedStatement.setString(7, user.getBirthDate());
            preparedStatement.setString(8, user.getInscriptionDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUsernameTaken(String username) {
        Statement statement = null;
        ResultSet result = null;
        int count = 0;

        loadDatabase();

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            /*PreparedStatement preparedStatement = connexion.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?;");
            preparedStatement.setString(1, username);
            result = preparedStatement.executeQuery(String.valueOf(preparedStatement));*/
            result = statement.executeQuery("SELECT COUNT(*) FROM users WHERE username = '" + username + "';");

            // Récupération des données
            while (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException ignored) {
        } finally {
            // Fermeture de la connexion
            try {
                if (result != null)
                    result.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignored) {
            }
        }

        return count >= 1;
    }

    public boolean isEmailTaken(String email) {
        Statement statement = null;
        ResultSet result = null;
        int count = 0;

        loadDatabase();

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            result = statement.executeQuery("SELECT COUNT(*) FROM users WHERE email = '" + email + "';");

            // Récupération des données
            while (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException ignored) {
        } finally {
            // Fermeture de la connexion
            try {
                if (result != null)
                    result.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignored) {
            }
        }

        return count >= 1;
    }
}
