package ru.nsu.borodkin;

/**
 * Класс, представляющий операцию сложения двух выражений.
 * Реализует интерфейс {@link Expression}.
 */
public class Add implements Expression {
    private final Expression left, right;

    /**
     * Конструктор, создающий выражение сложения.
     *
     * @param left левое выражение
     * @param right правое выражение
     */
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Выводит выражение в виде строки, представляющей операцию сложения.
     * Формат: (левая_часть + правая_часть)
     */


    @Override
    public String toString() {
        return "(" +
                left.toString() +
                "+" +
                right.toString() +
                ")";
    }

    /**
     * Находит производную от выражения сложения по переменной.
     * Производная от суммы равна сумме производных.
     *
     * @param var переменная, по которой вычисляется производная
     * @return новое выражение, представляющее производную
     */
    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }

    /**
     * Вычисляет значение выражения для заданных значений переменных.
     *
     * @param variables строка, представляющая значения переменных
     * @return результат вычисления суммы
     */
    @Override
    public int eval(String variables) {
        return left.eval(variables) + right.eval(variables);
    }
}
