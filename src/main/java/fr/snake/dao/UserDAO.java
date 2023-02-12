package fr.snake.dao;

import java.util.List;

import fr.snake.beans.BeanException;
import fr.snake.beans.User;

public interface UserDAO {
    void addUser(User user) throws DAOException;
    User getUser(String username) throws DAOException, BeanException;
    List<User> getAll() throws DAOException;
    boolean doLoginsExist(String username, String password) throws DAOException;
    boolean isUsernameTaken(String username) throws DAOException;
    boolean isEmailTaken(String email) throws DAOException;
}