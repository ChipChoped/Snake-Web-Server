package fr.snake.dao;

import fr.snake.beans.BeanException;
import fr.snake.beans.Game;

import java.util.List;

public interface GameDAO {
    void addGame(Game game) throws DAOException, BeanException;
    List<Game> getUserRecentGames(int userID) throws DAOException;
}
