package a4;

import org.junit.*;
import static org.junit.Assert.*;
    
public class PostfixTest {
    @Test
    public void testPostfixSingle() {
        String[] expressions = {
            "3.14159", "7"
        };
        
        double[] expectedResults = {
            3.14159, 7
        };
        
        for (int i = 0; i < expressions.length; i++) {
            assertEquals("Failed for expression: " + expressions[i], expectedResults[i], Postfix.postfix(Tokenizer.readTokens(expressions[i])), 0.00001);
        }
    }

    @Test
    public void testPostfixPlus() {
        String[] expressions = {
            "6 1 +", "0 7 +", "2 3 2 + +", "2 2 2 1 + + +", "1 1 1 1 1 1 1 + + + + + +", "2 2 + 2 1 + +"
        };
        
        for (int i = 0; i < expressions.length; i++) {
            assertEquals("Failed for expression: " + expressions[i], 7.0, Postfix.postfix(Tokenizer.readTokens(expressions[i])), 0.00001);
        }
    }

    @Test
    public void testPostfixMinus() {
        String[] expressions = {
            "9 2 -", "11 4 -", "15 6 - 2 -", "15 10 2 - -"
        };
        
        for (int i = 0; i < expressions.length; i++) {
            assertEquals("Failed for expression: " + expressions[i], 7.0, Postfix.postfix(Tokenizer.readTokens(expressions[i])), 0.00001);
        }
    }

    @Test
    public void testPostfixTimes() {
        String[] expressions = {
            "3.5 2 *", "1.75 2 2 * *", "1.75 2 * 2 *"
        };
        
        for (int i = 0; i < expressions.length; i++) {
            assertEquals("Failed for expression: " + expressions[i], 7.0, Postfix.postfix(Tokenizer.readTokens(expressions[i])), 0.00001);
        }
    }

    @Test
    public void testPostfixDiv() {
        String[] expressions = {
            "21 3 /", "70 5 / 2 /", "70 100 10 / /"
        };
        
        for (int i = 0; i < expressions.length; i++) {
            assertEquals("Failed for expression: " + expressions[i], 7.0, Postfix.postfix(Tokenizer.readTokens(expressions[i])), 0.00001);
        }
    }

    @Test
    public void testPostfixCombo() {
        String[] expressions = {
            "28 1 4 / *", "3 1 + 10 3 - * 4 /", "7 1 * 1 / 0 + 0 -"
        };
        
        for (int i = 0; i < expressions.length; i++) {
            assertEquals("Failed for expression: " + expressions[i], 7.0, Postfix.postfix(Tokenizer.readTokens(expressions[i])), 0.00001);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostfixThrowsException() {

        String[] expressions = {
            "+", "7 *", "7 7 7", "7 + 0", "3 b 4"
        };
        for (int i = 0; i < expressions.length; i++) {
             Postfix.postfix(Tokenizer.readTokens(expressions[i]));
        }
    }
}