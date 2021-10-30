
package com.plaincoded.restapi.exceptions;

public class NoUsernameException extends Exception{

    public NoUsernameException(){
        super("Username is null");
    }
}
