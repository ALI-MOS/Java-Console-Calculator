package com.mycompany.consolecalculator;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ali Mosa
 */
public class Utility {
    public static String[] operators = {"-", "+", "*", "/", ")", "("};
    /**
     * 
     * @param c
     * @return true or false
     * Check for Digit 0 -> 9
     */
    public static boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }
    
    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }
    
    /**
     * 
     * @param c
     * @return true or false 
     * Check for operator
     */
    public static boolean isOperator(char c) {
        return isOperator(Character.toString(c)); 
    }
    
    /**
     * checking if it is operator 
     * @param token: tokens array of tokens from Tokenizer class
     * @return 
     */
    public static boolean isOperator(String token) {
        return Arrays.asList(operators).contains(token);
    }
    
    public static boolean isSpace(char c) {
        return c == ' ' || c == '\t';
    }
    
    public static boolean isSeperator(char c) {
        return isOperator(c) || isSpace(c);
    }
    
    /**
     *
     * @param array 
     */
    public static void printArray(List<String> array){
        System.out.print("{");
        for (int i = 0; i < array.size(); i++) {   
            System.out.print(array.get(i)); 
            if (i == array.size() - 1)
                System.out.print(", ");
        }
        System.out.println("}");
    } 
}
