package fr.snake.servlets;

import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;
import fr.snake.forms.SignUpForm;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.snake.beans.User;

import static fr.snake.utils.SHA256.encrypt;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/sign-up")
@MultipartConfig(
		fileSizeThreshold   = 1024 * 1024,  // 1 MB
		maxFileSize         = 1024 * 1024 * 10, // 10 MB
		maxRequestSize      = 1024 * 1024 * 15 // 15 MB
)
public class SignUp extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
	public static final int BUFFER_SIZE = 10240;
	private final UserDAO userDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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

		try {
			form.checkNewAccount(request);
		} catch (DAOException e) {
			request.setAttribute("error", e.getMessage());
		}

		if (form.checkIfSignedIn()) {
			User user = new User();

			user.setUsername(request.getParameter("username"));
			user.setFirstName(request.getParameter("first-name"));
			user.setLastName(request.getParameter("last-name"));
			user.setSex(request.getParameter("sex"));
			user.setEmail(request.getParameter("email"));
			try {
				user.setPassword(encrypt(request.getParameter("password")));
			} catch (NoSuchAlgorithmException e) {
				request.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/page-not-fount.jsp").forward(request, response);
			}
			user.setBirthDate(request.getParameter("birth-date"));
			user.setInscriptionDate(String.valueOf(LocalDate.now()));
			user.setVictories(0);

			Part part = request.getPart("profile-picture");
			String fileName = getFileName(part);
			boolean pp;

			if (fileName != null && !fileName.isEmpty()) {
				pp = true;
				user.setProfilePicture(part.getInputStream());
			}
			else {
				pp = false;
				user.setProfilePicture(null);
			}

			try {
				userDAO.addUser(user, pp);
			} catch (DAOException e) {
				request.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/page-not-found.jsp").forward(request, response);
			}

			request.setAttribute("user", user);
			this.getServletContext().getRequestDispatcher("/WEB-INF/successfull-sign-up.jsp").forward(request, response);
		}
		else {
			request.setAttribute("form", form);
			request.setAttribute("firstAttempt", false);
			doGet(request, response);
		}
	}

	private static String getFileName(Part part) {
		for (String contentDisposition : part.getHeader("content-disposition").split(";"))
			if (contentDisposition.trim().startsWith("filename"))
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");

		return null;
	}
}
