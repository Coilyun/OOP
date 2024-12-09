package ru.nsu.borodkin;

/**
 * Класс, представляющий операцию деления двух выражений.
 * Реализует интерфейс {@link Expression}.
 */
public class Div implements Expression {
    private final Expression left, right;

    /**
     * Конструктор для создания выражения деления.
     *
     * @param left левое выражение
     * @param right правое выражение
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Выводит выражение в виде строки, представляющей операцию деления.
     * Формат: (левая_часть / правая_часть)
     */

    @Override
    public String toString() {
        return
                "(" +
                        left.toString() +
                        " / " +
                        right.toString() +
                        ")";
    }

    /**
     * Находит производную от выражения деления по переменной.
     * Производная от деления равна (f'(x) * g(x) - f(x) * g'(x)) / (g(x))^2.
     *
     * @param var переменная, по которой вычисляется производная
     * @return новое выражение, представляющее производную
     */
    @Override
    public Expression derivative(String var) {
        Expression leftDerivative = left.derivative(var);
        Expression rightDerivative = right.derivative(var);
        
        Expression numerator = new Sub(
            new Mul(leftDerivative, right),
            new Mul(left, rightDerivative)
        );
        
        Expression denominator = new Mul(right, right);
        
        return new Div(numerator, denominator);
    }

    /**
     * Вычисляет значение выражения.
     *
     * @param variables строка, представляющая значения переменных
     * @return результат вычисления деления
     */
    @Override
    public int eval(String variables) {
        return left.eval(variables) / right.eval(variables);
    }
}
