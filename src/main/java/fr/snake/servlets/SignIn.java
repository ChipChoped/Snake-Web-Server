package fr.snake.servlets;

import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;
import fr.snake.db.UsersDB;
import fr.snake.forms.SignUpForm;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.snake.beans.User;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/sign-up")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
		DAOFactory daoFactory = DAOFactory.getInstance();
		userDAO = daoFactory.getUserDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("firstAttempt") == null)
			request.setAttribute("firstAttempt", true);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/sign-up.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SignUpForm form = new SignUpForm();
		form.checkNewAccount(request);

		if (form.checkIfSignedIn()) {
			User user = new User();

			user.setUsername(request.getParameter("username"));
			user.setFirstName(request.getParameter("first-name"));
			user.setLastName(request.getParameter("last-name"));
			user.setSex(request.getParameter("sex"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setBirthDate(request.getParameter("birth-date"));
			user.setInscriptionDate(String.valueOf(LocalDate.now()));

			userDAO.add(user);
			
			request.setAttribute("user", user);
			this.getServletContext().getRequestDispatcher("/WEB-INF/successfull-sign-up.jsp").forward(request, response);
		}
		else {
			request.setAttribute("form", form);
			request.setAttribute("firstAttempt", false);
			doGet(request, response);
		}
	}
}
