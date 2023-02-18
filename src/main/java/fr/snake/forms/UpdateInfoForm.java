package fr.snake.forms;

import fr.snake.dao.DAOException;
import fr.snake.dao.DAOFactory;
import fr.snake.dao.UserDAO;
import fr.snake.enums.InfoState;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.snake.utils.SHA256.encrypt;

public class UpdateInfoForm {
    private InfoState usernameState;
    private InfoState firstNameState;
    private InfoState lastNameState;
    private InfoState sexState;
    private InfoState birthDateState;
    private InfoState emailState;
    private InfoState currentPasswordState;
    private InfoState passwordState;

    private final UserDAO userDAO;

    public UpdateInfoForm() {
        usernameState = InfoState.UNCHANGED;
        firstNameState = InfoState.UNCHANGED;
        lastNameState = InfoState.UNCHANGED;
        sexState = InfoState.UNCHANGED;
        birthDateState = InfoState.UNCHANGED;
        emailState = InfoState.UNCHANGED;
        currentPasswordState = InfoState.UNCHANGED;
        passwordState = InfoState.UNCHANGED;

        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getUserDao();
    }

    public void canUpdateUsername(String username, String currentUsername) throws DAOException {
        if (!username.equals(currentUsername)) {
            Pattern usernamePattern = Pattern.compile("\\w{3,20}");
            Matcher usernameMatch = usernamePattern.matcher(username);

            if (usernameMatch.find()) {
                if (userDAO.isUsernameTaken(username))
                    usernameState = InfoState.TAKEN;
                else
                    usernameState = InfoState.UPDATABLE;
            }
            else
                usernameState = InfoState.INVALID;
        }
    }

    public void canUpdateFirstName(String firstName, String currentFirstName) {
        if (!firstName.equals(currentFirstName)) {
            Pattern namePattern = Pattern.compile("[A-ZÉÈÀÎÏÂË][a-zéèàêîïäë]{1,29}");
            Matcher firstNameMatch = namePattern.matcher(firstName);

            if (firstNameMatch.find())
                firstNameState = InfoState.UPDATABLE;
            else
                firstNameState = InfoState.INVALID;
        }
    }

    public void canUpdateLastName(String lastName, String currentLastName) {
        if (!lastName.equals(currentLastName)) {
            Pattern namePattern = Pattern.compile("[A-ZÉÈÀÎÏÂË][a-zéèàêîïäë]{1,29}");
            Matcher lastNameMatch = namePattern.matcher(lastName);

            if (lastNameMatch.find())
                lastNameState = InfoState.UPDATABLE;
            else
                lastNameState = InfoState.INVALID;
        }
    }

    public void canUpdateSex(String sex, String currentSex) {
        if (!sex.equals(currentSex))
            if (sex.equals("M") || sex.equals("F"))
                sexState = InfoState.UPDATABLE;
            else
                sexState = InfoState.INVALID;
    }

    public void canUpdateBirthDate(String birthDate, String currentBirthDate) {
        if (!birthDate.equals(currentBirthDate)) {
            LocalDate birthDate_ = !birthDate.isEmpty() ? LocalDate.parse(birthDate) : null;
            LocalDate todayDate = LocalDate.now();

            if (birthDate_ != null && Period.between(birthDate_, todayDate).getYears() >= 13)
                birthDateState = InfoState.UPDATABLE;
            else
                birthDateState = InfoState.INVALID;
        }
    }

    public void canUpdateEmail(String email, String emailRepetition, String currentEmail) throws DAOException {
        if (!email.equals(currentEmail)) {
            Pattern emailPattern = Pattern.compile("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");
            Matcher emailMatch = emailPattern.matcher(email);

            if (emailMatch.find() && email.equals(emailRepetition)) {
                if (userDAO.isEmailTaken(email))
                    emailState = InfoState.TAKEN;
                else
                    emailState = InfoState.UPDATABLE;
            }
            else
                emailState = InfoState.INVALID;
        }
    }

    public void canUpdatePassword(String typedCurrentPassword, String password, String passwordRepetition, String currentPassword) throws NoSuchAlgorithmException {
        if (encrypt(typedCurrentPassword).equals(currentPassword)) {
            currentPasswordState = InfoState.UPDATABLE;

            if (!password.equals(currentPassword))
                if (password.length() >= 8 &&
                        password.length() <= 20 &&
                        password.equals(passwordRepetition))
                    passwordState = InfoState.UPDATABLE;
                else
                    passwordState = InfoState.INVALID;
        } else if (typedCurrentPassword.isEmpty()) {
            currentPasswordState = InfoState.UNCHANGED;
        } else
            currentPasswordState = InfoState.INVALID;
    }

    public InfoState getUsernameState() {
        return usernameState;
    }

    public void setUsernameState(InfoState usernameState) {
        this.usernameState = usernameState;
    }

    public InfoState getFirstNameState() {
        return firstNameState;
    }

    public void setFirstNameState(InfoState firstNameState) {
        this.firstNameState = firstNameState;
    }

    public InfoState getLastNameState() {
        return lastNameState;
    }

    public void setLastNameState(InfoState lastNameState) {
        this.lastNameState = lastNameState;
    }

    public InfoState getSexState() {
        return sexState;
    }

    public void setSexState(InfoState sexState) {
        this.sexState = sexState;
    }

    public InfoState getBirthDateState() {
        return birthDateState;
    }

    public void setBirthDateState(InfoState birthDateState) {
        this.birthDateState = birthDateState;
    }

    public InfoState getEmailState() {
        return emailState;
    }

    public void setEmailState(InfoState emailState) {
        this.emailState = emailState;
    }

    public InfoState getCurrentPasswordState() {
        return currentPasswordState;
    }

    public void setCurrentPasswordState(InfoState currentPasswordState) {
        this.currentPasswordState = currentPasswordState;
    }

    public InfoState getPasswordState() {
        return passwordState;
    }

    public void setPasswordState(InfoState passwordState) {
        this.passwordState = passwordState;
    }
}
