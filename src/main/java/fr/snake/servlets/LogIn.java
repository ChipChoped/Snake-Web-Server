package fr.snake.servlets;

import fr.snake.beans.BeanException;
import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;
import fr.snake.forms.LogInForm;

import java.io.IOException;
import java.io.Serial;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/log-in")
public class LogIn extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
	private final UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
		DAOFactory daoFactory = DAOFactory.getInstance();
		userDAO = daoFactory.getUserDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/log-in.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogInForm form = new LogInForm();

		try {
			form.checkIfLoggedIn(request);

			if (request.getParameter("remember-me") != null) {
				Cookie cookie = new Cookie("userID", Integer.toString(userDAO.getID(request.getParameter("username"))));
				cookie.setMaxAge(60 * 60 * 24 * 30);
				response.addCookie(cookie);
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("userID", Integer.toString(userDAO.getID(request.getParameter("username"))));
			}

			this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		} catch (BeanException | DAOException | NoSuchAlgorithmException e) {
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/page-not-found.jsp").forward(request, response);
		}

		doGet(request, response);
	}
}
