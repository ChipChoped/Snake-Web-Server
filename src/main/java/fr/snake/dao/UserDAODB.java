package fr.snake.dao;

import fr.snake.beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAODB implements UserDAO {
    private final DAOFactory daoFactory;

    UserDAODB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(User user) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
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
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible de communiquer avec la base de données");
            }
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<User>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT * FROM users;");

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
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible de communiquer avec la base de données");
            }
        }

        return users;
    }

    @Override
    public User getUserLoggedIn(String username, String password) throws DAOException {
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        User user = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            result = statement.executeQuery(
                    "SELECT * FROM users WHERE username = '" + username + "' and password = '" + password + "';");

            while (result.next()) {
                user = new User();
                user.setUsername(result.getString("username"));
                user.setFirstName(result.getString("first_name"));
                user.setLastName(result.getString("last_name"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setSex(result.getString("sex"));
                user.setBirthDate(result.getString("birth_date"));
                user.setInscriptionDate(result.getString("inscription_date"));
            }
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible de communiquer avec la base de données");
        }
            finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible de communiquer avec la base de données");
            }
        }

        return user;
    }

    @Override
    public boolean isUsernameTaken(String username) throws DAOException {
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        int count = 0;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT COUNT(*) FROM users WHERE username = '" + username + "';");

            // Récupération des données
            while (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible de communiquer avec la base de données");
            }
        }

        return count >= 1;
    }

    @Override
    public boolean isEmailTaken(String email) throws DAOException {
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        int count = 0;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();

            // Exécution de la requête
            result = statement.executeQuery("SELECT COUNT(*) FROM users WHERE email = '" + email + "';");

            // Récupération des données
            while (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible de communiquer avec la base de données");
            }
        }

        return count >= 1;
    }
}