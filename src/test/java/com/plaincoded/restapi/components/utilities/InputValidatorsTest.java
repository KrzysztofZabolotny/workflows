package com.plaincoded.restapi.components.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InputValidatorsTest {


    InputValidators inputValidators;

    @BeforeEach
    void init(){

        inputValidators = new InputValidators();
    }


    @Test
    @DisplayName("Test for required password length")
    public void testForRequiredPasswordLength(){
        String passwordWithSevenChars = "abcdefg";
        assertTrue(inputValidators.hasRequiredPasswordLength(passwordWithSevenChars));
    }

    @Test
    @DisplayName("Test for checking upper characters")
    public void testForUppercaseChars(){
        String passwordWithUpperCharacters = "ABCDEFG";
        assertTrue(inputValidators.hasRequiredUppercaseChars(passwordWithUpperCharacters));
    }

    @Test
    @DisplayName("Test for checking lower characters")
    public void testForLowerCharacters(){
        String passwordWithLowerChars = "abcdefg";
        assertTrue(inputValidators.hasRequiredLowercaseChars(passwordWithLowerChars));
    }


    @Test
    @DisplayName("Test for checking numbers")
    public void testForNumbers() {

        String numbers = "123456";
        assertTrue(inputValidators.hasRequiredNumbers(numbers));
    }

    @Test
    @DisplayName("Test for special characters")
    public void doesPasswordContainSpecialChars() {
        String specialCharacters = "@#$%&_";
        assertTrue(inputValidators.hasSpecialChars(specialCharacters));
    }

    @Test
    @DisplayName("Test for valid password")
    public void testForValidPassword(){
        String validPassword = "pA$$w0rD";

        assertTrue(inputValidators.isValidPassword(validPassword));

    }

    @Test
    @DisplayName("Test for valid email adress")
    public void testForValidEmail(){
        String validEmail = "user@user.com";
        assertTrue(inputValidators.isValidEmail(validEmail));
    }



}