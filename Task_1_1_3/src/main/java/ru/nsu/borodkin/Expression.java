package ru.nsu.borodkin;

/**
 * Интерфейс для математических выражений.
 */
public interface Expression {

    /**
     * Находит производную выражения по переменной.
     *
     * @param var переменная, по которой вычисляется производная
     * @return производная выражения
     */
    Expression derivative(String var);
    
    /**
     * Вычисляет значение выражения.
     *
     * @param variables строка с значениями переменных
     * @return результат вычисления
     */
    int eval(String variables);
};
