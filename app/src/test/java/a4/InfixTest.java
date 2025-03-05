package a4;

import static org.junit.Assert.*;
import org.junit.Test;
    
public class InfixTest {

    @Test
    public void testSingle() {
        String[] expressions = {"3.14159", "7"};
        Double[] expected = {3.14159, 7.0};

        for (int i = 0; i < expressions.length; i++) {
            assertEquals("Failed for expression: " + expressions[i], expected[i], Infix.infixToPostfix(Tokenizer.readTokens(expressions[i])), 0.00001);
        }
    }

    @Test
    public void testSimple() {
        String[] expressions = {
            "6+1", "2+3+2", "1+1+1+1+1+1+1", "10-3", "15-6-2", "12-1-1-1-1-1",
            "7*1", "0.2*35","7*1*1*1*1*1*1", "14/2", "70/5/2", "1+2*3",
            "0-1-2*3+4*5-6", "2*4-1", "15-2*4", "18/2-2", "11-8/2","10+2-5", "10-5+2", "70*4/40",
            "70/5*0.5", "100/10-9/3"
        };
        for (int i = 0; i < expressions.length; i++) {
            assertEquals("Failed for expression: " + expressions[i], 7.0, Infix.infixToPostfix(Tokenizer.readTokens(expressions[i])), 0.00001);
        }
    }

    @Test
    public void testParen() {
        String[] expressions = {
            "(7)", "(((7)))", "(5+2)", 
            "(2+3)+2", "2+(3+2)", "((2+1)+(1+3))", 
             "(15-6)-2", "15-(10-2)", "(3.5*4)*0.5", "0.5*(4*3.5)", "70/(100/10)", "(42/2)/3", 
            "(11+3)/2", "0.1*(35+35)", "70/(13-8)/2", "(9-1-1)", "(9)-(2)",
            "(((((1+2)*3)-1)/4)+5)", "(7*(5-(4*(5-(4*(5-4))))))", 
            "(5*(1+(((7-4)*(1+3))-((6+9)/(7-2)))-3)/0.2)/25"
        };
        
        for (int i = 0; i < expressions.length; i++) {
            assertEquals("Failed for expression: " + expressions[i], 7.0, Infix.infixToPostfix(Tokenizer.readTokens(expressions[i])), 0.00001);
        }
    }

    @Test
    public void testExponent() {
        String[] expressions = {
            "4^2", "2^4", "2^2^2", "4^16^0.25", "65536^0.5^2", "(3+1)^(5-3)",
            "6+3^3-17", "(20*0.2)^(10/5)", "40*6^3/540"
        };
        
        for (int i = 0; i < expressions.length; i++) {
            assertEquals("Failed for expression: " + expressions[i], 16.0, Infix.infixToPostfix(Tokenizer.readTokens(expressions[i])), 0.00001);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInfixThrowsException() {

        String[] expressions = {
            "+", "(7", "7)", "7+", ")7(", "2 5 +", "((2+5)", "(5+2))"
        };
        for (int i = 0; i < expressions.length; i++) {
            Infix.infixToPostfix(Tokenizer.readTokens(expressions[i]));
        }
    }
}

    