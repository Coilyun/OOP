package ru.nsu.borodkin;

/**
 * Интерфейс для математических выражений.
 */
public interface Expression {
    
    /**
     * Выводит выражение.
     */
    public abstract void print();

    /**
     * Находит производную выражения по переменной.
     *
     * @param var переменная, по которой вычисляется производная
     * @return производная выражения
     */
    public abstract Expression derivative(String var);
    
    /**
     * Вычисляет значение выражения.
     *
     * @param variables строка с значениями переменных
     * @return результат вычисления
     */
    public abstract int eval(String variables);
};
