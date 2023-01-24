package fr.snake.forms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class SignInForm {
	private boolean validUsername;
	private boolean ageOver13;
	private boolean validEmail;
	private boolean sameEmails;
	private boolean passwordOver8Characters;
	private boolean samePasswords;
	
	public void checkNewAccount(HttpServletRequest request) {
		Pattern usernamePattern = Pattern.compile("[a-zA-Z0-9_]{3,20}");
		Matcher usernameMatch = usernamePattern.matcher(request.getParameter("username"));
		Pattern emailPattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
		Matcher emailMatch = emailPattern.matcher(request.getParameter("email"));
		
		validUsername = usernameMatch.find() ? true : false;
		ageOver13 = !request.getParameter("age").isEmpty() && Integer.valueOf(request.getParameter("age")) >= 13? true : false;
		validEmail = emailMatch.find() ? true : false;
		sameEmails = request.getParameter("email").equals(request.getParameter("email-rep")) ? true : false;
		passwordOver8Characters = request.getParameter("password").length() >= 8 ? true : false;
		samePasswords = request.getParameter("password").equals(request.getParameter("password-rep")) ? true : false;
	}
	
	public boolean checkIfSignedIn() {
		return validUsername && ageOver13 && validEmail && sameEmails && passwordOver8Characters && samePasswords;
	}

	public boolean isValidUsername() {
		return validUsername;
	}

	public void setValidUsername(boolean usernameEmpty) {
		this.validUsername = usernameEmpty;
	}

	public boolean isAgeOver13() {
		return ageOver13;
	}

	public void setAgeOver13(boolean ageOver13) {
		this.ageOver13 = ageOver13;
	}

	public boolean isValidEmail() {
		return validEmail;
	}

	public void setValidEmail(boolean correctEmail) {
		this.validEmail = correctEmail;
	}

	public boolean isSameEmails() {
		return sameEmails;
	}

	public void setSameEmails(boolean sameEmails) {
		this.sameEmails = sameEmails;
	}

	public boolean isPasswordOver8Characters() {
		return passwordOver8Characters;
	}

	public void setPasswordOver8Characters(boolean passwordOver8Characters) {
		this.passwordOver8Characters = passwordOver8Characters;
	}

	public boolean isSamePasswords() {
		return samePasswords;
	}

	public void setSamePasswords(boolean samePasswords) {
		this.samePasswords = samePasswords;
	}
}
