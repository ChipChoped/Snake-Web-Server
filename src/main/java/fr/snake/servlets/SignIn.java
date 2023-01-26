package fr.snake.servlets;

import fr.snake.forms.SignInForm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

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
		if (request.getAttribute("firstAttempt") == null)
			request.setAttribute("firstAttempt", true);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/sign-in.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SignInForm form = new SignInForm();
		form.checkNewAccount(request);
		
		if (form.checkIfSignedIn()) {
			User user = new User();

			user.setUsername(request.getParameter("username"));
			user.setFirstName(request.getParameter("first-name"));
			user.setLastName(request.getParameter("last-name"));
			user.setSex(request.getParameter("sex"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setAge(Period.between(LocalDate.parse(request.getParameter("birth-date")), LocalDate.now()).getYears());
			
			request.setAttribute("user", user);
	
			this.getServletContext().getRequestDispatcher("/WEB-INF/successfull-sign-in.jsp").forward(request, response);
		}
		else {
			request.setAttribute("form", form);
			request.setAttribute("firstAttempt", false);
			doGet(request, response);
		}
	}
}
