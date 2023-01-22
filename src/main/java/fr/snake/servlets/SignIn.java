package fr.snake.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.snake.beans.User;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/sign-in")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/sign-in.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		if (request.getParameter("email").equals(request.getParameter("email-rep")) &&
		request.getParameter("password").equals(request.getParameter("password-rep"))) {
			
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setAge(Integer.valueOf(request.getParameter("age")));
		
		request.setAttribute("user", user);
		response.getWriter().append("???");

		this.getServletContext().getRequestDispatcher("/WEB-INF/successfull-sign-in.jsp").forward(request, response);
		}
		//else
			//doGet(request, response);
	}

}
