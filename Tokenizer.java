package com.mycompany.consolecalculator;

import java.util.ArrayList;
import java.util.List;


public class Tokenizer {
    public static boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }
    
    public static boolean isOperator(char c) {
        return (
            c == '+'
            || c == '-'
            || c == '*'
            || c == '/'
            || c == '('
            || c == ')'
        ); 
    }
    
    public static List<String> tokenize(String equation) throws Exception {
        String currentNumber = "";
        List<String> tokens = new ArrayList<>();
        
        for (int i = 0; i < equation.length(); i++){
            char c = equation.charAt(i);
 
            if (c == ' ') {
                
            } 
            else if (isOperator(c)){
                if(!currentNumber.isEmpty()){
                    tokens.add(currentNumber);
                    currentNumber = "";
                }
                tokens.add(Character.toString(c));
            }
            else if (isDigit(c)){          
                currentNumber += c;          
            }
            else {
                throw new Exception("Bad Input.");
            }
        }
         
        if (!currentNumber.isEmpty()) {
            tokens.add(currentNumber);
        }
 
        return tokens;
    }
}
