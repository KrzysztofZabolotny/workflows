
package com.plaincoded.restapi.components.utilities;

import com.plaincoded.restapi.entities.userEntities.User;
import org.apache.commons.validator.routines.EmailValidator;

public class InputValidators {

    private final static int MIN_PASSWORD_LENGTH = 6;

    public InputValidators() {
    }

    public boolean isValidEmail(String email) {//checks if email has a valid structure, and does not contain a '+' character

        String prohibitedMatcher = "(.*[+].*)";
        return EmailValidator.getInstance().isValid(email)&&!email.matches(prohibitedMatcher);
    }


    public boolean isValidPassword(String password) {//validates password structure, which needs to be length >=6, have small and big chars, numbers, and special char
        boolean isValid = true;
        if (!hasRequiredPasswordLength(password)) {isValid = false;}
        if (!hasRequiredUppercaseChars(password)) isValid = false;
        if (!hasRequiredLowercaseChars(password)) isValid = false;
        if (!hasRequiredNumbers(password)) isValid = false;
        if (!hasSpecialChars(password)) isValid = false;
        return isValid;
    }

    boolean hasRequiredPasswordLength(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    boolean hasRequiredUppercaseChars(String password) {
        String upperCaseChars = "(.*[A-Z].*)";
        return password.matches(upperCaseChars);
    }

    boolean hasRequiredLowercaseChars(String password) {
        String lowerCaseChars = "(.*[a-z].*)";
        return password.matches(lowerCaseChars);
    }

    boolean hasRequiredNumbers(String password) {
        String numbers = "(.*[0-9].*)";
        return password.matches(numbers);
    }

    boolean hasSpecialChars(String password) {
        String specialChars = "(.*[@,#,$,%,&,_].*$)";
        return password.matches(specialChars);
    }

    public boolean hasUsername(User user){
        return user.getUsername() != null;
    }

    public boolean hasPassword(User user){
        return user.getPassword() != null;
    }

}
