package fr.snake.forms;

import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class SignUpForm {
	private boolean validUsername;
	private boolean validFirstName;
	private boolean validLastName;
	private boolean checkedSex;
	private boolean validAge;
	private boolean validEmail;
	private boolean sameEmails;
	private boolean validPassword;
	private boolean samePasswords;

	private boolean usernameTaken;
	private boolean emailTaken;

	private UserDAO userDAO;
	
	public void checkNewAccount(HttpServletRequest request) throws DAOException {
		Pattern usernamePattern = Pattern.compile("\\w{3,20}");
		Matcher usernameMatch = usernamePattern.matcher(request.getParameter("username"));
		Pattern namePattern = Pattern.compile("[A-ZÉÈÀÎÏÂË][a-zéèàêîïäë]{1,29}");
		Matcher firstNameMatch = namePattern.matcher(request.getParameter("first-name"));
		Matcher lastNameMatch = namePattern.matcher(request.getParameter("last-name"));
		Pattern emailPattern = Pattern.compile("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");
		Matcher emailMatch = emailPattern.matcher(request.getParameter("email"));

		LocalDate birthDate = !request.getParameter("birth-date").isEmpty() ? LocalDate.parse(request.getParameter("birth-date")) : null;
		LocalDate todayDate = LocalDate.now();

		validUsername = usernameMatch.find();
		validFirstName = firstNameMatch.find();
		validLastName = lastNameMatch.find();
		checkedSex = request.getParameter("sex").equals("M") || request.getParameter("sex").equals("F");
		validAge = (birthDate != null && Period.between(birthDate, todayDate).getYears() >= 13);
		validEmail = emailMatch.find();
		sameEmails = request.getParameter("email").equals(request.getParameter("email-rep"));
		validPassword = request.getParameter("password").length() >= 8 && request.getParameter("password").length() <= 20;
		samePasswords = request.getParameter("password").equals(request.getParameter("password-rep"));

		DAOFactory daoFactory = DAOFactory.getInstance();
		userDAO = daoFactory.getUserDao();

		usernameTaken = userDAO.isUsernameTaken(request.getParameter("username"));
		emailTaken = userDAO.isEmailTaken(request.getParameter("email"));
	}
	
	public boolean checkIfSignedIn() {
		return validUsername && validFirstName && validLastName
				&& validAge && checkedSex &&validEmail && sameEmails &&
				validPassword && samePasswords && !usernameTaken && !emailTaken;
	}

	public boolean isValidUsername() {
		return validUsername;
	}

	public void setValidUsername(boolean validUsername) {
		this.validUsername = validUsername;
	}

	public boolean isValidFirstName() {
		return validFirstName;
	}

	public void setValidFirstName(boolean validFirstName) {
		this.validFirstName = validFirstName;
	}

	public boolean isValidLastName() {
		return validLastName;
	}

	public void setValidLastName(boolean validLastName) {
		this.validLastName = validLastName;
	}

	public boolean isCheckedSex() {
		return checkedSex;
	}

	public void setCheckedSex(boolean checkedSex) {
		this.checkedSex = checkedSex;
	}

	public boolean isValidAge() {
		return validAge;
	}

	public void setValidAge(boolean validAge) {
		this.validAge = validAge;
	}

	public boolean isValidEmail() {
		return validEmail;
	}

	public void setValidEmail(boolean validEmail) {
		this.validEmail = validEmail;
	}

	public boolean isSameEmails() {
		return sameEmails;
	}

	public void setSameEmails(boolean sameEmails) {
		this.sameEmails = sameEmails;
	}

	public boolean isValidPassword() {
		return validPassword;
	}

	public void setValidPassword(boolean validPassword) {
		this.validPassword = validPassword;
	}

	public boolean isSamePasswords() {
		return samePasswords;
	}

	public void setSamePasswords(boolean samePasswords) {
		this.samePasswords = samePasswords;
	}

	public boolean isUsernameTaken() {
		return usernameTaken;
	}

	public void setUsernameTaken(boolean usernameTaken) {
		this.usernameTaken = usernameTaken;
	}

	public boolean isEmailTaken() {
		return emailTaken;
	}

	public void setEmailTaken(boolean emailTaken) {
		this.emailTaken = emailTaken;
	}
}
