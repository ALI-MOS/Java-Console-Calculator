package com.mycompany.consolecalculator;

import java.util.Scanner;
import java.util.List;


// TODO 
// 2. 1/2 integer to double

// 4. Validation


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
                List<String> tokens = Tokenizer.tokenize(input);
                int result = Solver.solve(tokens);
                System.out.println(result);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }    
    
    public static void printArray(List<String> array){
        for (int i = 0; i < array.size(); i++) {   
            System.out.println(array.get(i)); 
        }
    } 
}
