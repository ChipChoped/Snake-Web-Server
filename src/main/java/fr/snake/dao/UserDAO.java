package fr.snake.dao;

import java.util.List;

import fr.snake.beans.User;

public interface UserDAO {
    void add(User user );
    List<User> getAll();
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
}