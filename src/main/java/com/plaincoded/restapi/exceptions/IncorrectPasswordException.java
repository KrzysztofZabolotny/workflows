package com.plaincoded.restapi.exceptions;

public class IncorrectPasswordException extends Exception{

    public IncorrectPasswordException(){
        super("incorrect password structure");
    }
}
