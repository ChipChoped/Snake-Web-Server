package fr.snake.servlets;

import fr.snake.beans.BeanException;
import fr.snake.beans.User;
import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;
import fr.snake.enums.InfoState;
import fr.snake.forms.UpdateInfoForm;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import static fr.snake.utils.SHA256.encrypt;

/**
 * Servlet implementation class profileSetting
 */
@WebServlet("/settings/profile")
public class ProfileSettings extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
	private final UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileSettings() {
        super();
		DAOFactory daoFactory = DAOFactory.getInstance();
		userDAO = daoFactory.getUserDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = getUserID(request);

		if (userID != null) {
			try {
				User user = userDAO.getUser(Integer.parseInt(userID));
				InputStream profilePicture = user.getProfilePicture();

				if (profilePicture != null) {
					byte[] image = profilePicture.readAllBytes();
					String base64Encoded = new String(Base64.getEncoder().encode(image), StandardCharsets.UTF_8);

					response.setContentType("image/jpg,png");
					request.setAttribute("profilePicture", base64Encoded);
				}

				request.setAttribute("user", user);
				this.getServletContext().getRequestDispatcher("/WEB-INF/profile-settings.jsp").forward(request, response);
			} catch (DAOException | BeanException e) {
				request.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/page-not-found.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("error", "You must be logged in to access this page");
			this.getServletContext().getRequestDispatcher("/WEB-INF/page-not-found.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UpdateInfoForm form = new UpdateInfoForm();
		String submit = request.getParameter("submit");
		String userID = getUserID(request);

		try {
			User user = userDAO.getUser(Integer.parseInt(userID));

			switch (submit) {
				case "username":
					form.canUpdateUsername(request.getParameter("username"), user.getUsername());

					if (form.getUsernameState() == InfoState.UPDATABLE)
						userDAO.updateUsername(Integer.parseInt(userID), request.getParameter("username"));

					break;
				case "first-name":
					form.canUpdateFirstName(request.getParameter("first-name"), user.getFirstName());

					if (form.getFirstNameState() == InfoState.UPDATABLE)
						userDAO.updateFirstName(Integer.parseInt(userID), request.getParameter("first-name"));

					break;
				case "last-name":
					form.canUpdateLastName(request.getParameter("last-name"), user.getLastName());

					if (form.getLastNameState() == InfoState.UPDATABLE)
						userDAO.updateLastName(Integer.parseInt(userID), request.getParameter("last-name"));

					break;
				case "sex":
					form.canUpdateSex(request.getParameter("sex"), user.getSex());

					if (form.getSexState() == InfoState.UPDATABLE)
						userDAO.updateSex(Integer.parseInt(userID), request.getParameter("sex"));

					break;
				case "birth-date":
					form.canUpdateBirthDate(request.getParameter("birth-date"), user.getBirthDate());

					if (form.getBirthDateState() == InfoState.UPDATABLE)
						userDAO.updateBirthDate(Integer.parseInt(userID), request.getParameter("birth-date"));

					break;
				case "email":
					form.canUpdateEmail(request.getParameter("email"), request.getParameter("email-rep"), user.getEmail());

					if (form.getEmailState() == InfoState.UPDATABLE)
						userDAO.updateEmail(Integer.parseInt(userID), request.getParameter("email"));

					break;
				case "password":
					form.canUpdatePassword(request.getParameter("current-password"), request.getParameter("password"), request.getParameter("password-rep"), user.getPassword());

					if (form.getCurrentPasswordState() == InfoState.UPDATABLE && form.getPasswordState() == InfoState.UPDATABLE)
						userDAO.updatePassword(Integer.parseInt(userID), encrypt(request.getParameter("password")));

					break;
			}

			request.setAttribute("form", form);
			request.setAttribute("passedSubmit", submit);
			doGet(request, response);
		} catch (DAOException | BeanException | NoSuchAlgorithmException e) {
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/page-not-found.jsp").forward(request, response);
		}
	}

	private String getUserID(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		if (userID == null){
			Cookie[] cookies = request.getCookies();

			if (cookies != null)
				for (Cookie cookie : cookies)
					if (cookie.getName().equals("userID"))
						userID = cookie.getValue();
		}

		return userID;
	}

	private void update(String value, Method update) {

	}
}
