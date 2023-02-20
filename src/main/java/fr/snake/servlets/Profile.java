package fr.snake.servlets;

import fr.snake.beans.BeanException;
import fr.snake.beans.User;
import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/user/*")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserDAO userDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		StringBuffer requestURL = request.getRequestURL();
		String completeURL = requestURL.toString();
		String username = completeURL.substring(completeURL.lastIndexOf('/') + 1);

		try {
			User user = userDAO.getUser(username);
			request.setAttribute("user", user);

			InputStream profilePicture = user.getProfilePicture();

			if (profilePicture != null) {
				byte[] image = profilePicture.readAllBytes();
				String base64Encoded = new String(Base64.getEncoder().encode(image), StandardCharsets.UTF_8);

				response.setContentType("image/jpg");
				request.setAttribute("profilePicture", base64Encoded);
			}
		} catch (DAOException e) {
			request.setAttribute("error", e.getMessage());
		} catch (BeanException e) {
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/page-not-found.jsp").forward(request, response);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
