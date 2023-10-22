package com.mycompany.consolecalculator;

import java.util.Scanner;
import java.util.ArrayList;
public class ConsoleCalculator {
    
    public static void main(String[] args) {
        System.out.println("Start");       
          Scanner sc = new Scanner(System.in);
          String input = sc.nextLine();     
        System.out.println("Your input is : " + input);
         
        ArrayList<String> array = tokenize(input);
        printArray(array);
            
    } 
    

    public static boolean isDigit(char input){
        return input >= '0' && input <= '9' ;
     }
    public static boolean isOperator(char input){
        return input == '+' ||input == '-'||input == '*'||input == '/'||input == '('||input == ')' ; 
    } 
    public static ArrayList<String> tokenize (String input){
        String currentNumber = "";
        ArrayList <String> array = new ArrayList<String>();
         
        for (int i=0; i<input.length();i++){
         
            if (input.charAt(i)== ' '){
            continue;
            } 
            if (isOperator (input.charAt(i))){
             
                if (!currentNumber.equals("")){
                    array.add(currentNumber);
                    currentNumber = "";
                }
                array.add(Character.toString(input.charAt(i))); 
            
            }else if (isDigit(input.charAt(i))){          
                currentNumber = currentNumber +input.charAt(i);
            
            }else if (currentNumber.equals("")) {
                    array.add(currentNumber);
                }
             }
         
        if (!currentNumber.equals("")) {
            array.add(currentNumber);
        }
        return array;
    } 
    public static void printArray(ArrayList<String> array){
            for (int i =0;i < array.size();i++){   
            System.out.println(array.get(i)); }
       }
}