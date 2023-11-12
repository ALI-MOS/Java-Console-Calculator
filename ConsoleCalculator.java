package com.mycompany.consolecalculator;

import static com.mycompany.consolecalculator.Validator.checkValidChars;
import static com.mycompany.consolecalculator.Validator.validate;
import java.util.Scanner;
import java.util.List;


/**
 * 
 * @author Ali Mosa
 */

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
            
            //check for valid input    
            boolean validString = checkValidChars(input);
            if (!validString) {
                System.out.println("Invalid Characters in Input.");
                continue;
            }
            //if true go to tokenize method and make the list
            List<String> tokens = Tokenizer.tokenize(input);

            boolean validEquation = validate(tokens);
            if (!validEquation) {
                System.out.println("Invalid Equation Structure.");
                continue;
            }
            double result = Solver.solve(tokens);
            //if the result is int without any fraction 
            //print it as int not double type.
            if (result - (int) result == 0)
                System.out.println((int) result);
            else
                System.out.println(result);
            }

        }
}
