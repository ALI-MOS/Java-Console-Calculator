
package com.mycompany.consolecalculator;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Ali Mosa
 */
public class Testing {
    public static void main(String[] args) {
        Hashtable<String, Double> tests = new Hashtable<>();
        
        tests.put("1", 1.0);
        tests.put("-1", -1.0);
        tests.put("1+1", 2.0);
        tests.put("1-1", 0.0);
        tests.put("1+1*2", 3.0);
        tests.put("1*-1", -1.0);
        tests.put("1*(1+2)", 3.0);
        tests.put("1/2", 0.5);
        tests.put("-1/2", -0.5);
        tests.put("-(2*(5*-5))+100", 150.0);
        tests.put("-(-1)", 1.0);
        tests.put("---1", -1.0);
        tests.put("-1-1", -2.0);
        
        int errors = 0;
        Enumeration<String> keys = tests.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            try {
                double result = Solver.solve(Tokenizer.tokenize(key));
                if (tests.get(key) != result) {
                    System.out.println("Test not passed: " + key + " resulted " + result + " instead of " + tests.get(key)+ ".");
                    errors++;
                }
            }
            catch (Exception e) {
                System.out.println("Test not passed: " + key + " produced an error.");
                errors++;
            }
        }
        if (errors == 0)
            System.out.println("All tests are completed: No errors found.");
        else 
            System.out.println("All tests are completed: " + errors + " errors found.");

    }
}
