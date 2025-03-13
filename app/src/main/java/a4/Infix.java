package a4;

import java.util.ArrayDeque;

/**
 * converts an infix expression to postfix notation using a stack and queue and then evaluates it using the postfix class
 */
public class Infix {

  /**
   * Converts an infix expression (given as a queue of tokens) to postfix notation.
   * 
   * @param tokens An ArrayDeque of tokens representing the infix expression, where each token is either a Double or a Character.
   * @return A Double value representing the result of the postfix expression evaluation.
   */
  public static Double infixToPostfix(ArrayDeque < Object > tokens) {

    ArrayDeque < Object > stack = new ArrayDeque < > ();
    ArrayDeque < Object > queue = new ArrayDeque < > ();

    while (!tokens.isEmpty()) {
      Object token = tokens.getFirst();
      tokens.removeFirst();

      if (token instanceof Double) {
        queue.addLast(token);
      } else if (token instanceof Character) {
        Character tokenChar = (Character) token;

        if (whatIs(tokenChar).equals("operator")) {

          while (!stack.isEmpty() && precedence((Character) stack.peek()) >= precedence(tokenChar)) {
            queue.addLast(stack.pop());
          }
          stack.push(tokenChar);

        } else if (whatIs(tokenChar).equals("exponent")) {
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

    return Postfix.postfix(queue);
  }

  /**
   * Determines the precedence of a given operator.
   * 
   * @param operator The operator whose precedence is being determined.
   * @return An integer representing the precedence level. 
   */
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

  /**
   * Determines the type of a character 
   * 
   * @param nonDouble the character 
   * @return A String indicating the type of the character
   */
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

    case '^':
      return "exponent";
    default:
      throw new IllegalArgumentException("Unsupported character: " + nonDouble);
    }
  }
}


