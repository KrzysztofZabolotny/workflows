
package com.plaincoded.restapi.exceptions;

public class IncorrectEmailException extends Exception {
    public IncorrectEmailException() {
        super("incorrect email structure");
    }
}