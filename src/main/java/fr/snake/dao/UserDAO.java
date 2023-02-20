package fr.snake.dao;

import java.util.List;

import fr.snake.beans.BeanException;
import fr.snake.beans.User;

public interface UserDAO {
    void addUser(User user, boolean withPicture) throws DAOException;

    User getUser(int id) throws DAOException, BeanException;
    User getUser(String username) throws DAOException, BeanException;
    int getID(String username) throws DAOException, BeanException;
    List<User> getAll() throws DAOException;

    boolean doLoginsExist(String username, String password) throws DAOException;
    boolean isUsernameTaken(String username) throws DAOException;
    boolean isEmailTaken(String email) throws DAOException;

    void updateUsername(int id, String username) throws DAOException, BeanException;
    void updateFirstName(int id, String firstName) throws DAOException, BeanException;
    void updateLastName(int id, String lastName) throws DAOException, BeanException;
    void updateSex(int id, String sex) throws DAOException, BeanException;
    void updateBirthDate(int id, String birthDate) throws DAOException, BeanException;
    void updateEmail(int id, String email) throws DAOException, BeanException;
    void updatePassword(int id, String password) throws DAOException, BeanException;

    void deleteUser(int id) throws DAOException, BeanException;
}