
package com.plaincoded.restapi.exceptions;

public class DocumentNotPresentException extends Exception{
    public DocumentNotPresentException(){
        super("Document does not exist");
    }
}
