package ru.nsu.shadrina;

public class Main {
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        e.print();  // (3+(2*x))
        System.out.println();

        Expression de = e.derivative("x");
        de.print();  // (0+((0*x)+(2*1)))
        System.out.println();

        // Вычисление значения выражения при x = 10
        int result = e.eval("x = 10;");
        System.out.println(result);  // 23
    }
}
