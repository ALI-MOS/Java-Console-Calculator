package com.mycompany.consolecalculator;

import static com.mycompany.consolecalculator.Utility.isOperator;
import static com.mycompany.consolecalculator.Utility.isSeperator;
import static java.lang.Character.isDigit;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    /**
     * 
     * @param equation
     * @return
     */
    public static List<String> tokenize(String equation) {
        String currentNumber = "";
        List<String> tokens = new ArrayList<>();
        /**
         * this loop check for:
         * 1->is the first index is empty
         *  a->is it operator add var current number the make it equal to ""
         * 2->is it digit
         */
        for (int i = 0; i < equation.length(); i++){
            char c = equation.charAt(i);
 
            if (isSeperator(c)) {
                if (!currentNumber.isEmpty()){
                    tokens.add(currentNumber);
                    currentNumber = "";
                }
                if (isOperator(c))
                    tokens.add(Character.toString(c));
            }
            else if (isDigit(c)){          
                currentNumber += c;          
            }
        }
         
        if (!currentNumber.isEmpty()) {
            tokens.add(currentNumber);
        }
 
        return tokens;
    }
}
