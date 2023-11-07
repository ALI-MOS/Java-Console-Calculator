package com.mycompany.consolecalculator;

/**
 * Class of exception that handle the invalid input
 * @author Ali Mosa
 */
public class InvalidInputException extends Exception {
    /**
     * 
     * @param message :return the exception massage
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
