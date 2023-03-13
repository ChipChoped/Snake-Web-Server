package fr.snake.services;

import fr.snake.beans.Game;
import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.GameDAO;
import fr.snake.dto.GameDTO;

public class GameService {
    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final GameDAO gameDAO = daoFactory.getGameDao();

    public static void addGame(GameDTO gameDTO) throws DAOException {
        gameDAO.addGame(new Game(gameDTO));
    }
}
