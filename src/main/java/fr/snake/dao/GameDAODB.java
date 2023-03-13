package fr.snake.dao;

import fr.snake.beans.BeanException;
import fr.snake.beans.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAODB implements GameDAO {
    private final DAOFactory daoFactory;

    public GameDAODB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static Game setGame(ResultSet result) throws SQLException {
        Game game = new Game();

        game.setId(result.getInt("id"));
        game.setUserID(result.getInt("user_id"));
        game.setWon(result.getBoolean("won"));
        game.setScore(result.getInt("score"));
        game.setDate(result.getString("date"));

        return game;
    }

    public static void setPreparedStatement(Game game, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, game.getUserID());
        preparedStatement.setBoolean(2, game.isWon());
        preparedStatement.setInt(3, game.getScore());
        preparedStatement.setString(4, game.getDate());
    }

    @Override
    public void addGame(Game game) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO games (user_id, won, score, date) VALUES(?, ?, ?, ?);");
            setPreparedStatement(game, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the database");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the database");
            }
        }
    }

    @Override
    public List<Game> getUserRecentGames(int userID) throws DAOException {
        List<Game> games = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT * FROM games WHERE user_id = '" + userID + "' ORDER BY date DESC LIMIT 20;");

            while (result.next())
                games.add(setGame(result));
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DAOException("Impossible to communicate with the database");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to communicate with the database");
            }
        }

        return games;
    }
}
