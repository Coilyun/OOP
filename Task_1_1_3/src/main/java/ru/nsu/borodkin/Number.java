package ru.nsu.borodkin;

/**
 * Класс, представляющий число в выражении.
 * Реализует интерфейс {@link Expression}.
 */
public class Number implements Expression {
    private final int value;

    /**
     * Конструктор для создания числа.
     *
     * @param value значение числа
     */
    public Number(int value) {
        this.value = value;
    }


    /**
     * Выводит число.
     */

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Находит производную от числа.
     * Производная от числа всегда равна 0.
     *
     * @param var переменная, по которой вычисляется производная
     * @return число 0
     */
    @Override
    public Expression derivative(String var) {
        return new Number(0); 
    }

    /**
     * Возвращает значение числа.
     *
     * @param variables строка, представляющая значения переменных (не используется для чисел)
     * @return значение числа
     */
    @Override
    public int eval(String variables) {
        return value;
    }
}
