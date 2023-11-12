package com.mycompany.consolecalculator;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ali Mosa
 */
public class Utility {
    public static String[] operators = {"-", "+", "*", "/", ")", "("};
    /**
     * check if c is digit from 0 to 9
     * @param c
     * @return 
     */
    public static boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }
    /**
     * loop to check number in string
     * @param s
     * @return 
     */
    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }
    
    /**
     * check the character c is operator
     * @param c
     * @return 
     */
    public static boolean isOperator(char c) {
        return isOperator(Character.toString(c)); 
    }
    /**
     * check the string token is in the operator array
     * @param token
     * @return 
     */
    public static boolean isOperator(String token) {
        return Arrays.asList(operators).contains(token);
    }
    /**
     * check if char c is space or tap
     * @param c
     * @return 
     */
    public static boolean isSpace(char c) {
        return c == ' ' || c == '\t';
    }
    /**
     * check if char c is space or operator 
     * @param c
     * @return 
     */
    public static boolean isSeperator(char c) {
        return isOperator(c) || isSpace(c);
    }
    /**
     * method to print the elements of the array
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
