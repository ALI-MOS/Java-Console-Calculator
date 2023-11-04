package com.mycompany.consolecalculator;

import java.util.Scanner;
import java.util.ArrayList;


// TODO 
// 1. Handle parentheses
// 2. 1/2 integer to double
// 3. restructuring


public class ConsoleCalculator {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("q to exit.");

        while (true){                    
            System.out.print("> ");     
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("q")){
                break;
            }
            
            try {
                ArrayList<String> tokens = tokenize(input);
                int result = solve(tokens);
                System.out.println(result);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }    
    
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
    
    public static ArrayList<String> tokenize(String equation) throws Exception {
        String currentNumber = "";
        ArrayList<String> tokens = new ArrayList<>();
        
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
    
    public static void printArray(ArrayList<String> array){
        for (int i = 0; i < array.size(); i++) {   
            System.out.println(array.get(i)); 
        }
    }
    
    public static int solve(ArrayList<String> tokens) throws Exception {
        if (tokens.isEmpty()) {
            throw new Exception("No input.");
        }

        try {
            solveForminus(tokens);
            solveForMultDiv(tokens);
            solveForAddSub(tokens);
        } 
        catch (NumberFormatException e){ 
            throw new Exception ("Bad Input.");
        }
        
        return Integer.parseInt(tokens.get(0));
    }
    
    public static void solveForMultDiv(ArrayList<String> tokens) {
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
    
    public static void solveForminus(ArrayList<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("-") 
                && (i == 0 || isOperator(tokens.get(i - 1).charAt(0)))) {
                i -= reduce(tokens, i, 2, tokens.get(i) + tokens.get(i + 1));
            } 
        }
    }
    
    public static void solveForAddSub(ArrayList<String> tokens) {
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
    public static int reduce(ArrayList<String> tokens, int position, int n, String replacement) {
        tokens.set(position, replacement);
        
        for (int i = 0; i < n - 1; i++) {
            tokens.remove(position + 1);
        }
        
        return n - 1;
    }
}
