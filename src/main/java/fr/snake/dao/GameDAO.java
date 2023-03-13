package fr.snake.dao;

import fr.snake.beans.Game;

import java.util.List;

public interface GameDAO {
    void addGame(Game game) throws DAOException;
    List<Game> getUserRecentGames(int userID) throws DAOException;
}
