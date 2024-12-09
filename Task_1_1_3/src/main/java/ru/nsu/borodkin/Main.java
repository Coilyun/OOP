package ru.nsu.borodkin;

/**
 * Главный класс для демонстрации работы с выражениями.
 */
public class Main {
    
    /**
     * Основной метод, демонстрирующий создание выражения,
     * нахождение его производной и вычисление значения.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        System.out.println(e);  // (3+(2*x))
        System.out.println();

        Expression de = e.derivative("x");
        System.out.println(de);  // (0+((0*x)+(2*1)))
        System.out.println();

        int result = e.eval("x = 10;");
        System.out.println(result);  // 23
    }
}
