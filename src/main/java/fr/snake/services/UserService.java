package fr.snake.services;

import fr.snake.beans.BeanException;
import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;
import fr.snake.dto.LoginsDTO;
import fr.snake.dto.UserIDDTO;
import fr.snake.functions.SHA256;

import java.security.NoSuchAlgorithmException;

public class UserService {
    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final UserDAO userDAO = daoFactory.getUserDao();

    public static boolean doLoginsExist(LoginsDTO logins) throws DAOException, NoSuchAlgorithmException {
        return userDAO.doLoginsExist(logins.getUsername(), logins.getPassword());
    }

    public static UserIDDTO getUserID(String username) throws DAOException, BeanException {
        UserIDDTO userID = new UserIDDTO();
        userID.setId(userDAO.getID(username));

        return userID;
    }

    public static void updateOnline(UserIDDTO ID, boolean online) throws DAOException, BeanException {
         userDAO.updateOnline(ID.getId(), online);
    }
}
