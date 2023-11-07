package com.mycompany.consolecalculator;

import static com.mycompany.consolecalculator.Utility.isNumber;
import static com.mycompany.consolecalculator.Utility.isOperator;
import static java.lang.Character.isDigit;
import static java.lang.Character.isSpace;
import java.util.List;

/**
 * Check if the input characters is valid numbers and operators only
 * @author Ali Mosa
 */
public class Validator {
    public static boolean checkValidChars(String equation) {
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
            if (
                !isDigit(c)
                && !isOperator(c)
                && !isSpace(c)
               )
                return false;                
        }
        return true;
    }
    
    public static boolean validate(List<String> tokens) {
        return checkParentheses(tokens) && checkStructure(tokens);
    }
    
    public static boolean checkParentheses(List<String> tokens) {
        int counter = 0;
        for (int i = 0; i < tokens.size(); i++) {
            String s = tokens.get(i);
            if (s.equals("(")) {
                counter += 1;
            }  
            else if (s.equals(")")) {
                counter -= 1;
                if (counter < 0)
                    return false;
            }              
        }
        return (counter == 0);
    }
    
    public static boolean checkStructure(List<String> tokens) {
        if (tokens.isEmpty())
            return false;
        
        String first = tokens.get(0);
        if (!isNumber(first) && !first.equals("(") 
                && !first.equals("-"))
            return false;
            
        for (int i = 1; i < tokens.size(); i++) {
            String t1 = tokens.get(i-1);
            String t2 = tokens.get(i);
            if (isNumber(t1) && isNumber(t2))
                return false;
            if (isOperator(t1) && isOperator(t2) 
                    && !t2.equals("(") && !t2.equals("-"))
                return false;
        }
        
        String last = tokens.get(tokens.size() - 1);
        if (!isNumber(last) && !last.equals(")"))
            return false;

        return true;
    }
}
