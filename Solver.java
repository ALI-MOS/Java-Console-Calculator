package com.mycompany.consolecalculator;

import java.util.List;

/**
 *
 * @author Ali Mosa
 */
public class Solver {
    public static int solve(List<String> tokens) throws Exception {        
        if (tokens.isEmpty()) {
            throw new Exception("No input.");
        }

        try {
            solveForParentheses(tokens);
            solveForminus(tokens);
            solveForMultDiv(tokens);
            solveForAddSub(tokens);
        } 
        catch (NumberFormatException e){ 
            throw new Exception ("Bad Input.");
        }
        
        return Integer.parseInt(tokens.get(0));
    }
    
    private static void solveForParentheses(List<String> tokens) throws Exception {
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
                    int result = solve(tokens.subList(start + 1, i));
                    i -= reduce(tokens, start, 3, Integer.toString(result));
                    start = -1;
                }
            }
        }
    }
    
    private static void solveForMultDiv(List<String> tokens) {
        for (int i = 1; i < tokens.size(); i = i + 2){
            int left = Integer.parseInt(tokens.get(i-1));
            int right = Integer.parseInt(tokens.get(i+1));
            
            switch (tokens.get(i)) {
                case "*" -> {
                    reduce(tokens, i - 1, 3, Integer.toString(left * right));
                    i = i - 2;
                }
                case "/" -> {
                    reduce(tokens, i - 1, 3, Integer.toString(left / right));
                    i = i - 2;
                }
            }
        }
    }
    
    private static void solveForminus(List<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("-") 
                && (i == 0 || Tokenizer.isOperator(tokens.get(i - 1).charAt(0)))) {
                i -= reduce(tokens, i, 2, tokens.get(i) + tokens.get(i + 1));
            } 
        }
    }
    
    private static void solveForAddSub(List<String> tokens) {
        for (int i = 1; i < tokens.size(); i = i + 2){
            int left = Integer.parseInt(tokens.get(i-1));
            int right = Integer.parseInt(tokens.get(i+1));
            
            switch (tokens.get(i)) {
                case "+" -> {
                    i -= reduce(tokens, i - 1, 3, Integer.toString(left + right));
                }
                case "-" -> {
                    i -= reduce(tokens, i - 1, 3, Integer.toString(left - right));
                }
            }
        }
    }
    
    // reduce reduces n places by 1 result (e.g. [1 + 2] -> [3])
    private static int reduce(List<String> tokens, int position, int n, String replacement) {
        tokens.set(position, replacement);
        
        for (int i = 0; i < n - 1; i++) {
            tokens.remove(position + 1);
        }
        
        return n - 1;
    }
}
