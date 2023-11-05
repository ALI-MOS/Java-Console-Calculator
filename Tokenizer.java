/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consolecalculator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ali Mosa
 */
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
