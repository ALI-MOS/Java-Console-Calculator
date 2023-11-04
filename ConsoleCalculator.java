package com.mycompany.consolecalculator;

import java.util.Scanner;
import java.util.ArrayList;
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
                ArrayList<String> array = tokenize(input);
                int result = solve(array);
                System.out.println(result);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }    
    
    public static boolean isDigit(char c){
        return (c >= '0' && c <= '9');
    }
    
    public static boolean isOperator(char c){
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
                if(c == '-' && (i == 0 || isOperator(equation.charAt(i - 1)))){
                    currentNumber += c;
                    continue;
                }
 
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

        int result = 0;
        try {
            
       
            for (int i = 1; i < tokens.size(); i = i + 2){
                switch (tokens.get(i)) {
                    case "*" -> {
                        result = Integer.parseInt(tokens.get(i-1)) * Integer.parseInt(tokens.get(i+1));
                        tokens.remove(i+1);
                        tokens.remove(i);
                        tokens.set(i-1, Integer.toString(result));
                        i = i - 2;
                    }
                    case "/" -> {
                        result = Integer.parseInt(tokens.get(i-1)) / Integer.parseInt(tokens.get(i+1));
                        tokens.remove(i+1);
                        tokens.remove(i);
                        tokens.set(i-1, Integer.toString(result));
                        i = i - 2;
                    }
                    default -> {}
                }
            }
            
            for (int i = 1; i < tokens.size(); i = i + 2){
                switch (tokens.get(i)) {
                    case "+" -> {
                        result = Integer.parseInt(tokens.get(i-1)) + Integer.parseInt(tokens.get(i+1));
                        tokens.remove(i+1);
                        tokens.remove(i);
                        tokens.set(i-1, Integer.toString(result));
                        i = i - 2;
                    }
                    case "-" -> {
                        result = Integer.parseInt(tokens.get(i-1)) - Integer.parseInt(tokens.get(i+1));
                        tokens.remove(i+1);
                        tokens.remove(i);
                        tokens.set(i-1, Integer.toString(result));
                        i = i - 2;
                    }
                    
                    default -> {}
                }
            }
        } 
        catch (NumberFormatException e){ 
            throw new Exception ("Bad Input.");
        }
        
        return Integer.parseInt(tokens.get(0));
    }
}
