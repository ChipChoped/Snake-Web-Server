package fr.snake.dao;

import java.util.List;

import fr.snake.beans.User;

public interface UserDAO {
    void add(User user) throws DAOException;
    List<User> getAll() throws DAOException;
    boolean doLoginsExist(String username, String password) throws DAOException;
    boolean isUsernameTaken(String username) throws DAOException;
    boolean isEmailTaken(String email) throws DAOException;
}