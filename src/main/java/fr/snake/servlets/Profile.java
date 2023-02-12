package fr.snake.servlets;

import fr.snake.beans.BeanException;
import fr.snake.beans.User;
import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;

import java.io.IOException;
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
		} catch (DAOException e) {
			request.setAttribute("error", e.getMessage());
		} catch (BeanException e) {
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/page-not-found.jsp").forward(request, response);
		}

		/*ServletContext context = getServletContext();
		String path = context.getInitParameter("path");

		// Get requested image by path info.
		String requestedImage = request.getPathInfo();

		// Check if file name is actually supplied to the request URI.
		if (requestedImage == null) {
			// Do your thing if the image is not supplied to the request URI.
			// Throw an exception, or send 404, or show default/warning image, or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Decode the file name (might contain spaces and on) and prepare file object.
		File image = new File(path, "ChipChop.jpg");

		// Check if file actually exists in filesystem.
		if (!image.exists()) {
			// Do your thing if the file appears to be non-existing.
			// Throw an exception, or send 404, or show default/warning image, or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Get content type by filename.
		String contentType = getServletContext().getMimeType(image.getName());

		// Check if file is actually an image (avoid download of other files by hackers!).
		// For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
		if (contentType == null || !contentType.startsWith("image")) {
			// Do your thing if the file appears not being a real image.
			// Throw an exception, or send 404, or show default/warning image, or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Init servlet response.
		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(image.length()));

		// Write image content to response.
		Files.copy(image.toPath(), response.getOutputStream());

		request.setAttribute("imagePath", path + "profil_pictures/" + username + ".jpg");*/

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
