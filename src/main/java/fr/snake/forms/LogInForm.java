package fr.snake.forms;

import fr.snake.beans.BeanException;
import fr.snake.beans.User;
import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;

public class LogInForm {
    private User user;

    public void checkIfLoggedIn(HttpServletRequest request) throws BeanException, DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDao();
        user = userDAO.getUserLoggedIn(request.getParameter("username"),
                request.getParameter("password"));

        if (user == null)
            throw new BeanException("Username or password is wrong");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
