package ru.nsu.borodkin;

/**
 * Класс, представляющий операцию вычитания двух выражений.
 * Реализует интерфейс {@link Expression}.
 */
public class Sub implements Expression {
    private final Expression left, right;

    /**
     * Конструктор, создающий выражение разности.
     *
     * @param left левое выражение
     * @param right правое выражение
     */
    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Выводит выражение в виде строки, представляющей операцию разности.
     * Формат: (левая_часть - правая_часть)
     */
    @Override
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("-");
        right.print();
        System.out.print(")");
    }

    /**
     * Находит производную от выражения разности по переменной.
     * Производная от разности равна разности производных.
     *
     * @param var переменная, по которой вычисляется производная
     * @return новое выражение, представляющее производную
     */
    @Override
    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }

    /**
     * Вычисляет значение выражения для заданных значений переменных.
     *
     * @param variables строка, представляющая значения переменных
     * @return результат вычисления разности
     */
    @Override
    public int eval(String variables) {
        return left.eval(variables) - right.eval(variables);
    }
}
