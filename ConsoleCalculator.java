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

            ArrayList<String> array = tokenize(input);
            int result = solve(array);
            System.out.println(result);
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
    
    public static ArrayList<String> tokenize(String equation){
        String currentNumber = "";
        ArrayList<String> tokens = new ArrayList<>();
        
        for (int i = 0; i < equation.length(); i++){
            char c = equation.charAt(i);
 
            if (c == ' ') {
                
            } 
            else if (isOperator(c)){
                if (!currentNumber.isEmpty()){
                    tokens.add(currentNumber);
                    currentNumber = "";
                }
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
    
    public static void printArray(ArrayList<String> array){
        for (int i = 0; i < array.size(); i++) {   
            System.out.println(array.get(i)); 
        }
    }
    
    public static int solve(ArrayList<String> tokens){      
        int result = Integer.parseInt(tokens.get(0));
       
        for (int i = 1; i < tokens.size(); i = i + 2){
            switch (tokens.get(i)) {
                case "+" -> result += Integer.parseInt(tokens.get(i+1));
                case "-" -> result -= Integer.parseInt(tokens.get(i+1));
                case "*" -> result *= Integer.parseInt(tokens.get(i+1));
                case "/" -> result /= Integer.parseInt(tokens.get(i+1));
                default -> {
                }
            }
        }
        
        return result;
    }
}
