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
        assertEquals("(3+(2*x))", e.toString());
    }

    /**
     * Тестирует вычисление производной от выражения сложения.
     */
    @Test
    public void testDerivativeOfAddExpression() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        Expression derivative = e.derivative("x");
        // Производная от (3 + (2 * x)) по x будет 2
        assertEquals("(0+((0*x)+(2*1)))", derivative.toString());
    }

    /**
     * Тестирует вычисление значения выражения.
     */
    @Test
    public void testEval() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        int result = e.eval("x = 10;");
        assertEquals(23, result);  // 3 + (2 * 10) = 23
    }

    /**
     * Тестирует вычисление производной от переменной.
     */
    @Test
    public void testDerivativeOfVariable() {
        Expression var = new Variable("x");
        Expression derivative = var.derivative("x");
        assertEquals("1", derivative.toString());  // производная от x по x равна 1
    }

    /**
     * Тестирует вычисление производной от числа.
     */
    @Test
    public void testDerivativeOfNumber() {
        Expression number = new Number(5);
        Expression derivative = number.derivative("x");
        assertEquals("0", derivative.toString());  // производная от числа равна 0
    }

    /**
     * Тестирует создание и вывод выражения вычитания.
     */
    @Test
    public void testSubExpression() {
        Expression e = new Sub(new Number(5), new Variable("x"));
        assertEquals("(5-x)", e.toString());
    }

    /**
     * Тестирует вычисление производной от выражения вычитания.
     */
    @Test
    public void testDerivativeOfSubExpression() {
        Expression e = new Sub(new Number(5), new Variable("x"));
        Expression derivative = e.derivative("x");
        assertEquals("(0-1)", derivative.toString()); // Производная от (5 - x) по x равна (0 - 1), что дает -1
    }


    /**
     * Тестирует вычисление значения выражения вычитания.
     */
    @Test
    public void testEvalSubExpression() {
        Expression e = new Sub(new Number(5), new Variable("x"));
        int result = e.eval("x = 3;");
        assertEquals(2, result);  // 5 - 3 = 2
    }

    /**
     * Тестирует создание и вывод выражения деления.
     */
    @Test
    public void testDivExpression() {
        Expression e = new Div(new Number(10), new Variable("x"));
        assertEquals("(10 / x)", e.toString());
    }

    /**
     * Тестирует вычисление производной от выражения деления.
     */
    @Test
    public void testDerivativeOfDivExpression() {
        Expression e = new Div(new Number(10), new Variable("x"));
        Expression derivative = e.derivative("x");
        assertEquals("(((0*x)-(10*1)) / (x*x))", derivative.toString()); // Сравнение строкового представления
    }


    /**
     * Тестирует вычисление значения выражения деления.
     */
    @Test
    public void testEvalDivExpression() {
        Expression e = new Div(new Number(10), new Variable("x"));
        int result = e.eval("x = 2;");
        assertEquals(5, result);  // 10 / 2 = 5
    }
}
