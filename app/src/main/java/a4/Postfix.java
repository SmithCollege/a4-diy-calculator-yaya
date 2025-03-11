package a4;

import java.util.ArrayDeque;

public class Postfix {

    public static Double postfix(ArrayDeque<Object> tokens) {
        
        ArrayDeque<Object> stack = new ArrayDeque<>();
        Double num1;
        Double num2;
        Character operator;

        while (!tokens.isEmpty()) {
            Object token = tokens.getFirst();
            tokens.removeFirst();
            
            if (token instanceof Double) {
                stack.push(token);
            } else if (token instanceof Character) {
                
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Not enough elements to process operation.");
                } else {
                    num1 = (Double) stack.pop();
                    num2 = (Double) stack.pop();
                    operator = (Character) token;

                    Double result = calculate(operator, num2, num1);
                    stack.push(result);
                }
            } else {
                throw new IllegalArgumentException("Non Double/Character argument");
            }
        }

        if (stack.size() > 1) {
            throw new IllegalArgumentException("More than one item in stack at end of operation.");
        } else {
            return (Double) stack.pop();
        }
    }

    // Corrected method signature for Double parameters
    public static Double calculate(Character operator, Double num1, Double num2) {
        switch (operator) {
            case '+':
                return num1 + num2;

            case '-':
                return num1 - num2;

            case '*':
                return num1 * num2;

            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("You cannot divide by 0.");
                }
                return num1 / num2;
            case '^':
                return Math.pow(num1,num2);

            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

}