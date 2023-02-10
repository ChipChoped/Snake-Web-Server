package fr.snake.servlets;

import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;
import fr.snake.forms.SignUpForm;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.snake.beans.User;

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
	// Chemin à modifier
	// Le dossier .snake doit déjà exister
	public static final String FILE_PATH = "C:\\Users\\chaim\\.snake\\";

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
			user.setPassword(request.getParameter("password"));
			user.setBirthDate(request.getParameter("birth-date"));
			user.setInscriptionDate(String.valueOf(LocalDate.now()));

			Part part = request.getPart("profile-picture");
			String fileName = getFileName(part);

			if (fileName != null && !fileName.isEmpty())
				saveFile(part, user.getUsername());

			try {
				userDAO.add(user);
			} catch (DAOException e) {
				request.setAttribute("error", e.getMessage());
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

	private void saveFile(Part part, String fileName) throws IOException {
		try (BufferedInputStream input = new BufferedInputStream(part.getInputStream(), BUFFER_SIZE);
			 BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(new File(SignUp.FILE_PATH, fileName + ".jpg")), BUFFER_SIZE)) {

			byte[] buffer = new byte[BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		}
	}
}
