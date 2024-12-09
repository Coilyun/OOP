package ru.nsu.borodkin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс для тестирования выражений.
 * Содержит тесты для различных операций с выражениями.
 */
public class ExpressionTest {

    /**
     * Тестирует создание и вывод выражения сложения.
     */
    @Test
    public void testAddExpression() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        e.print();
        System.out.println();
    }

    /**
     * Тестирует вычисление производной от выражения сложения.
     */
    @Test
    public void testDerivativeOfAddExpression() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        Expression derivative = e.derivative("x");
        derivative.print();
        System.out.println();
    }

    /**
     * Тестирует вычисление значения выражения.
     */
    @Test
    public void testEval() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        int result = e.eval("x = 10;");
        assertEquals(23, result);
    }

    /**
     * Тестирует вычисление производной от переменной.
     */
    @Test
    public void testDerivativeOfVariable() {
        Expression var = new Variable("x");
        Expression derivative = var.derivative("x");
        derivative.print();
        System.out.println();
    }

    /**
     * Тестирует вычисление производной от числа.
     */
    @Test
    public void testDerivativeOfNumber() {
        Expression number = new Number(5);
        Expression derivative = number.derivative("x");
        derivative.print();
        System.out.println();
    }

    /**
     * Тестирует создание и вывод выражения вычитания.
     */
    @Test
    public void testSubExpression() {
        Expression e = new Sub(new Number(5), new Variable("x"));
        e.print();
        System.out.println();
    }

    /**
     * Тестирует вычисление производной от выражения вычитания.
     */
    @Test
    public void testDerivativeOfSubExpression() {
        Expression e = new Sub(new Number(5), new Variable("x"));
        Expression derivative = e.derivative("x");
        derivative.print();
        System.out.println();
    }

    /**
     * Тестирует вычисление значения выражения вычитания.
     */
    @Test
    public void testEvalSubExpression() {
        Expression e = new Sub(new Number(5), new Variable("x"));
        int result = e.eval("x = 3;");
        assertEquals(2, result);
    }

    /**
     * Тестирует создание и вывод выражения деления.
     */
    @Test
    public void testDivExpression() {
        Expression e = new Div(new Number(10), new Variable("x"));
        e.print();
        System.out.println();
    }

    /**
     * Тестирует вычисление производной от выражения деления.
     */
    @Test
    public void testDerivativeOfDivExpression() {
        Expression e = new Div(new Number(10), new Variable("x"));
        Expression derivative = e.derivative("x");
        derivative.print();
        System.out.println();
    }

    /**
     * Тестирует вычисление значения выражения деления.
     */
    @Test
    public void testEvalDivExpression() {
        Expression e = new Div(new Number(10), new Variable("x"));
        int result = e.eval("x = 2;");
        assertEquals(5, result);
    }
}
