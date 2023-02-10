package fr.snake.forms;

import fr.snake.beans.BeanException;
import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;

public class LogInForm {
    private boolean loginsExist;

    public void checkIfLoggedIn(HttpServletRequest request) throws BeanException, DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDao();
        loginsExist = userDAO.doLoginsExist(request.getParameter("username"),
                request.getParameter("password"));

        if (!loginsExist)
            throw new BeanException("Username or password is wrong");
    }

    public boolean isLoginsExist() {
        return loginsExist;
    }

    public void setLoginsExist(boolean loginsExist) {
        this.loginsExist = loginsExist;
    }
}
