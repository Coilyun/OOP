package ru.nsu.borodkin;

public class Number implements Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0); 
    }

    @Override
    public int eval(String variables) {
        return value;
    }
}
