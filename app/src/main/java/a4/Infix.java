package a4;

import java.util.ArrayDeque;

public class Infix {

    public static Double infixToPostfix(ArrayDeque<Object> tokens) {
        
        ArrayDeque<Object> stack = new ArrayDeque<>();
        ArrayDeque<Object> queue = new ArrayDeque<>();

        while (!tokens.isEmpty()) {
            Object token = tokens.getFirst();
            tokens.removeFirst();

            if (token instanceof Double) {
                queue.addLast(token);  // Add operand (number) directly to queue
            } else if (token instanceof Character) {
                Character tokenChar = (Character) token;

                if (whatIs(tokenChar).equals("operator")) {
                   
                    while (!stack.isEmpty() && precedence((Character) stack.peek()) >= precedence(tokenChar)) {
                        queue.addLast(stack.pop());
                    }
                    stack.push(tokenChar);  
                    
                }else if(whatIs(tokenChar).equals("exponent")){
                    while (!stack.isEmpty() && precedence((Character) stack.peek()) > precedence(tokenChar)) {
                        queue.addLast(stack.pop());
                    }
                    stack.push(tokenChar);  

                } else if (whatIs(tokenChar).equals("parenthesis")) {
                    if (tokenChar.equals('(')) {
                        stack.push(tokenChar);  
                    } else if (tokenChar.equals(')')) {
                        
                        while (!stack.isEmpty() && !stack.peek().equals('(')) {
                            queue.addLast(stack.pop());
                        }

                        if (stack.isEmpty()) {
                            throw new IllegalArgumentException("There are mismatched parentheses.");
                        }
                        stack.pop(); 
                    }
                }
            }
        }

        
        while (!stack.isEmpty()) {
            if (stack.peek().equals('(') || stack.peek().equals(')')) {
                throw new IllegalArgumentException("You have mismatched parentheses.");
            } else {
                queue.addLast(stack.pop());
            }
        }

        // Now that we have the postfix tokens in the queue, pass it to the Postfix evaluator
        return Postfix.postfix(queue);
    }

    public static int precedence(Character operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1; 
            case '*':
            case '/':
                return 2; 

            case '^':
                return 3;
            default:
                return -1; 
        }
    }

    public static String whatIs(Character nonDouble) {
        switch (nonDouble) {
            case '+':
            case '-':
            case '*':
            case '/':
                return "operator";
            case '(':
            case ')':
                return "parenthesis";

            case'^':
                return "exponent";
            default:
                throw new IllegalArgumentException("Unsupported character: " + nonDouble);
        }
    }
}






