package com.mycompany.consolecalculator;

import static com.mycompany.consolecalculator.Validator.checkValidChars;
import static com.mycompany.consolecalculator.Validator.validate;
import java.util.Scanner;
import java.util.List;



// TODO 

// - documentation
// - exe

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
            

            boolean validString = checkValidChars(input);
            if (!validString) {
                System.out.println("Invalid Characters in Input.");
                continue;
            }
            List<String> tokens = Tokenizer.tokenize(input);

            boolean validEquation = validate(tokens);
            if (!validEquation) {
                System.out.println("Invalid Equation Structure.");
                continue;
            }
            double result = Solver.solve(tokens);

            if (result - (int) result == 0)
                System.out.println((int) result);
            else
                System.out.println(result);
            }

        }
}
