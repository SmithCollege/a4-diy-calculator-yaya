package a4;

import java.util.ArrayDeque;

public class Infix {

    public static Double infixToPostfix(ArrayDeque<Object> tokens) {
        
        ArrayDeque<Object> stack = new ArrayDeque<>();
        ArrayDeque<Object> queue = new ArrayDeque<>();


        while(!tokens.isEmpty()){//ask about why the loop exits even when stack isnt empty (like when it encounters an operator of lesser value)
            Object token = tokens.getFirst();
            tokens.removeFirst();

            if (token instanceof Double) {
                queue.addLast(token);
            } else if (token instanceof Character) {

                if(token.equals('(')){
                    stack.push(token);
                }else if (token.equals(')')){
                    while (!stack.peek().equals('(')){

                        if (stack.isEmpty()){
                            throw new IllegalArgumentException("There are mismatched parentheses.");
                        }
                        queue.addLast(stack.pop());
                    }
                    stack.pop();
                }


                while(!stack.isEmpty()){
                    if(precedence((Character)stack.peek())>=precedence((Character)token)){
                        queue.addLast(stack.pop());
                    }
                }
                stack.push(token);
            }
        }

        while(!stack.isEmpty()){
            if(stack.peek().equals('(') || stack.peek().equals(')') ){
                throw new IllegalArgumentException("You have mismatched parentheses.");
            }else{
                queue.addLast(stack.pop());
            }
        }

        return(Postfix.postfix(queue));
        }


    public static int precedence(Character operator) {
    switch (operator) {
        case '+':
        case '-':
            return 1; // Low precedence
        case '*':
        case '/':
            return 2; // high precedence
        
        default:
            return -1; // Invalid operator
    }
}


}






