package ru.nsu.shadrina;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionTest {

    @Test
    public void testAddExpression() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        e.print();
        System.out.println();
    }

    @Test
    public void testDerivativeOfAddExpression() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        Expression derivative = e.derivative("x");
        derivative.print();
        System.out.println();
    }

    @Test
    public void testEval() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        int result = e.eval("x = 10;");
        assertEquals(23, result);
    }

    @Test
    public void testDerivativeOfVariable() {
        Expression var = new Variable("x");
        Expression derivative = var.derivative("x");
        derivative.print();
        System.out.println();
    }

    @Test
    public void testDerivativeOfNumber() {
        Expression number = new Number(5);
        Expression derivative = number.derivative("x");
        derivative.print();
        System.out.println();
    }
}
