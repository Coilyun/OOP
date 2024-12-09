package ru.nsu.borodkin;

/**
 * Класс, представляющий операцию умножения двух выражений.
 * Реализует интерфейс {@link Expression}.
 */
public class Mul implements Expression {
    private final Expression left, right;

    /**
     * Конструктор для создания выражения умножения.
     *
     * @param left левое выражение
     * @param right правое выражение
     */
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return
                "(" +
                        left.toString() +
                        "*" +
                        right.toString() +
                        ")";
    }

    /**
     * Выводит выражение в виде строки, представляющей операцию умножения.
     * Формат: (левая_часть * правая_часть)
     */

    /**
     * Находит производную от выражения умножения по переменной.
     * Производная от произведения равна произведению производной первого и второго выражений.
     *
     * @param var переменная, по которой вычисляется производная
     * @return новое выражение, представляющее производную
     */
    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var)));
    }

    /**
     * Вычисляет значение выражения.
     *
     * @param variables строка, представляющая значения переменных
     * @return результат вычисления произведения
     */
    @Override
    public int eval(String variables) {
        return left.eval(variables) * right.eval(variables);
    }
}
