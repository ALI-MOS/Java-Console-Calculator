package com.mycompany.consolecalculator;

import java.util.List;


public class Solver {
    public static double solve(List<String> tokens) throws InvalidInputException {        
        if (tokens.isEmpty()) {
            throw new InvalidInputException("No input.");
        }

        try {
            solveForParentheses(tokens);
            solveForminus(tokens);
            solveForMultDiv(tokens);
            solveForAddSub(tokens);
        } 
        catch (NumberFormatException e){ 
            throw new InvalidInputException("Bad Input.");
        }
        
        return Double.parseDouble(tokens.get(0));
    }
    
    private static void solveForParentheses(List<String> tokens) throws InvalidInputException {
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
    
    private static void solveForminus(List<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("-") 
                && (i == 0 || isOperator(tokens.get(i - 1)))) {
                if (tokens.get(i + 1).equals("-"))
                    i -= reduce(tokens, i, 3, tokens.get(i + 2));
                else if (tokens.get(i + 1).charAt(0) == '-')
                    i -= reduce(tokens, i, 2, tokens.get(i + 1).substring(1));
                else 
                    i -= reduce(tokens, i, 2, tokens.get(i) + tokens.get(i + 1));
            } 
        }
    }
    
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
    
    // reduce reduces n places by 1 result (e.g. [1 + 2] -> [3])
    private static double reduce(List<String> tokens, int position, int n, String replacement) {
        tokens.set(position, replacement);
        
        for (int i = 0; i < n - 1; i++) {
            tokens.remove(position + 1);
        }
        
        return n - 1;
    }
    
    public static boolean isOperator(String token) {
        return (
            token.equals("-")
            || token.equals("+")
            || token.equals("*")
            || token.equals("/")
            || token.equals("(")
            || token.equals(")")
        );
    }
}
