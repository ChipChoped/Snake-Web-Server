package fr.snake.dao;

import fr.snake.beans.BeanException;
import fr.snake.beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAODB implements UserDAO {
    private final DAOFactory daoFactory;

    UserDAODB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static void setUser(User user, ResultSet result) throws SQLException {
        user.setId(result.getInt("id"));
        user.setUsername(result.getString("username"));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setEmail(result.getString("email"));
        user.setPassword(result.getString("password"));
        user.setSex(result.getString("sex"));
        user.setVictories(result.getInt("victories"));
        user.setBirthDate(result.getString("birth_date"));
        user.setInscriptionDate(result.getString("inscription_date"));
    }

    public static void setPreparedStatement(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setString(6, user.getSex());
        preparedStatement.setString(7, Integer.toString(user.getVictories()));
        preparedStatement.setString(8, user.getBirthDate());
        preparedStatement.setString(9, user.getInscriptionDate());

        preparedStatement.executeUpdate();
    }

    @Override
    public void addUser(User user) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO users (username, first_name, last_name, email, password, sex, victories, birth_date, inscription_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);");
            setPreparedStatement(user, preparedStatement);
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }
    }

    @Override
    public User getUser(int id) throws DAOException, BeanException {
        User user;
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT * FROM users WHERE id = '" + id + "';");

            if (result.next()) {
                user = new User();
                setUser(user, result);
            }
            else
                throw new BeanException("The user " + id + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }

        return user;
    }

    @Override
    public User getUser(String username) throws DAOException, BeanException {
        User user = null;
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "';");

            if (result.next()) {
                user = new User();
                setUser(user, result);
            }
            else
                throw new BeanException("The user " + username + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }

        return user;
    }

    @Override
    public int getID(String username) throws DAOException, BeanException {
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        int id;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT id FROM users WHERE username = '" + username + "';");

            if (result.next()) {
                id = result.getInt("id");
            }
            else
                throw new BeanException("The user " + username + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }

        return id;
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
                setUser(user, result);

                users.add(user);
            }
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }

        return users;
    }

    @Override
    public boolean doLoginsExist(String username, String password) throws DAOException {
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        boolean userExist = false;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            result = statement.executeQuery(
                    "SELECT COUNT(*) FROM users WHERE username = '" + username + "' and password = '" + password + "';");

            while (result.next()) {
                userExist = result.getInt(1) > 0;
            }
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }

        return userExist;
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
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
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
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }

        return count >= 1;
    }

    @Override
    public void updateUsername(int id, String username) throws DAOException, BeanException {
        Connection connexion = null;
        PreparedStatement preparedStatement;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE users SET username = ? WHERE id = ?;");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, String.valueOf(id));

            if (preparedStatement.executeUpdate() == 0)
                throw new BeanException("The user " + id + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }
    }

    @Override
    public void updateFirstName(int id, String firstName) throws DAOException, BeanException {
        Connection connexion = null;
        PreparedStatement preparedStatement;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE users SET first_name = ? WHERE id = ?;");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, String.valueOf(id));

            if (preparedStatement.executeUpdate() == 0)
                throw new BeanException("The user " + id + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }
    }

    @Override
    public void updateLastName(int id, String lastName) throws DAOException, BeanException {
        Connection connexion = null;
        PreparedStatement preparedStatement;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE users SET last_name = ? WHERE id = ?;");
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, String.valueOf(id));

            if (preparedStatement.executeUpdate() == 0)
                throw new BeanException("The user " + id + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }
    }

    @Override
    public void updateSex(int id, String sex) throws DAOException, BeanException {
        Connection connexion = null;
        PreparedStatement preparedStatement;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE users SET sex = ? WHERE id = ?;");
            preparedStatement.setString(1, sex);
            preparedStatement.setString(2, String.valueOf(id));

            if (preparedStatement.executeUpdate() == 0)
                throw new BeanException("The user " + id + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }
    }

    @Override
    public void updateBirthDate(int id, String birthDate) throws DAOException, BeanException {
        Connection connexion = null;
        PreparedStatement preparedStatement;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE users SET birth_date = ? WHERE id = ?;");
            preparedStatement.setString(1, birthDate);
            preparedStatement.setString(2, String.valueOf(id));

            if (preparedStatement.executeUpdate() == 0)
                throw new BeanException("The user " + id + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }
    }

    @Override
    public void updateEmail(int id, String email) throws DAOException, BeanException {
        Connection connexion = null;
        PreparedStatement preparedStatement;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE users SET email = ? WHERE id = ?;");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, String.valueOf(id));

            if (preparedStatement.executeUpdate() == 0)
                throw new BeanException("The user " + id + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }
    }

    @Override
    public void updatePassword(int id, String password) throws DAOException, BeanException {
        Connection connexion = null;
        PreparedStatement preparedStatement;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE users SET password = ? WHERE id = ?;");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, String.valueOf(id));

            if (preparedStatement.executeUpdate() == 0)
                throw new BeanException("The user " + id + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }
    }

    @Override
    public void deleteUser(int id) throws DAOException, BeanException {
        Connection connexion = null;
        PreparedStatement preparedStatement;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM users WHERE id = ?;");
            preparedStatement.setString(1, String.valueOf(id));

            if (preparedStatement.executeUpdate() == 0)
                throw new BeanException("The user " + id + " doesn't exist");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the data base");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the data base");
            }
        }
    }
}