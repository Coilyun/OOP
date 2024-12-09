package ru.nsu.borodkin;

/**
 * Класс, представляющий переменную в выражении.
 * Реализует интерфейс {@link Expression}.
 */
public class Variable implements Expression {
    private final String name;

    /**
     * Конструктор для создания переменной.
     *
     * @param name имя переменной
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Выводит имя переменной.
     */
    @Override
    public void print() {
        System.out.print(name);
    }

    /**
     * Находит производную от переменной.
     * Производная от переменной по самой себе равна 1, иначе 0.
     *
     * @param var переменная, по которой вычисляется производная
     * @return 1, если переменная совпадает, иначе 0
     */
    @Override
    public Expression derivative(String var) {
        return new Number(name.equals(var) ? 1 : 0);
    }

    /**
     * Возвращает значение переменной, переданное в строке.
     *
     * @param variables строка с значениями переменных в формате "имя=значение"
     * @return значение переменной
     */
    @Override
    public int eval(String variables) {
        String[] parts = variables.split(";");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue[0].trim().equals(name)) {
                return Integer.parseInt(keyValue[1].trim());
            }
        }
        return 0;
    }
}
