
package com.plaincoded.restapi.exceptions;

public class DuplicateUserException extends Exception{
    public DuplicateUserException(){
        super("User already exists");
    }
}
