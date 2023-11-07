package com.mycompany.consolecalculator;

import static com.mycompany.consolecalculator.Utility.isOperator;
import java.util.List;

/***
 * 
 * @author Ali Mosa
 */
public class Solver {
    /***
     * Solve the tokens array for (Parentheses,minus,mult,div,add and sub)
     * @param tokens array of tokens (Strings) from Tokenizer class
     * @return one result in form of double
     * @throws InvalidInputException 
     */
    public static double solve(List<String> tokens){        
        solveForParentheses(tokens);
        solveForminus(tokens);
        solveForMultDiv(tokens);
        solveForAddSub(tokens);
        
        return Double.parseDouble(tokens.get(0));
    }
    /**
     * Solve the tokens array for (Parentheses) and reduce it to one result 
     * @param tokens:  tokens array of tokens from Tokenizer class
     */
    private static void solveForParentheses(List<String> tokens) {
        int counter = 0;
        int start = -1;

        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("(")) {
                counter++;
                if (start == -1) {
                    start = i;
                }
            }
            else if (tokens.get(i).equals(")")) {
                counter--;
                if (counter == 0) {
                    double result = solve(tokens.subList(start + 1, i));
                    i -= reduce(tokens, start, 3, Double.toString(result));
                    start = -1;
                }
            }
        }
    }
    /**
     * solve the tokens array for (multiplication and division) and reduce it to one result
     * @param tokens:  kens array of tokens from Tokenizer class
     */
    private static void solveForMultDiv(List<String> tokens) {
        for (int i = 1; i < tokens.size(); i = i + 2){
            double left = Double.parseDouble(tokens.get(i-1));
            double right  = Double.parseDouble(tokens.get(i+1));
            
            switch (tokens.get(i)) {
                case "*" -> {
                    reduce(tokens, i - 1, 3, Double.toString(left * right));
                    i = i - 2;
                }
                case "/" -> {
                    reduce(tokens, i - 1, 3, Double.toString(left / right));
                    i = i - 2;
                }
            }
        }
    }
    /***
     * solve the tokens array for (minus) and reduce it to one result
     * @param tokens: tokens array of tokens from Tokenizer class
     */
    private static void solveForminus(List<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("-") 
                && (i == 0 || isOperator(tokens.get(i - 1)))) {
                if (tokens.get(i + 1).equals("-")) {
                    reduce(tokens, i, 3, tokens.get(i + 2));
                    i -= 1;
                }
                else if (tokens.get(i + 1).charAt(0) == '-')
                    i -= reduce(tokens, i, 2, tokens.get(i + 1).substring(1));
                else 
                    i -= reduce(tokens, i, 2, tokens.get(i) + tokens.get(i + 1));
            } 
        }
    }
    /**
     * solve the tokens array for (addition and subtraction) and reduce it to one result
     * @param tokens:  tokens array of tokens from Tokenizer class
     */
    private static void solveForAddSub(List<String> tokens) {
        for (int i = 1; i < tokens.size(); i = i + 2){
            double left = Double.parseDouble(tokens.get(i-1));
            double right = Double.parseDouble(tokens.get(i+1));
            
            switch (tokens.get(i)) {
                case "+" -> {
                    i -= reduce(tokens, i - 1, 3, Double.toString(left + right));
                }
                case "-" -> {
                    i -= reduce(tokens, i - 1, 3, Double.toString(left - right));
                }
            }
        }
    }
    /**
     * 
     * @param tokens: tokens array of tokens from Tokenizer class
     * @param position: the index of array tokens
     * @param n: number of steps in the index
     * @param replacement: the replacement of value in the index of tokens
     * @return number of elements deleted
     */
    // reduce reduces n places by 1 result (e.g. [1 + 2] -> [3])
    private static double reduce(List<String> tokens, int position, int n, String replacement) {
        // replace first element with given value
        tokens.set(position, replacement);
        // delete (n-1) elements after position 
        for (int i = 0; i < n - 1; i++) {
            tokens.remove(position + 1);
        }
        
        return n - 1;
    }
}
