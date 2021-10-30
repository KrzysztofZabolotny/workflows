
package com.plaincoded.restapi.exceptions;

public class NoPasswordException extends Exception{

    public NoPasswordException(){
        super("Password is null");
    }
}
