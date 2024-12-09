package ru.nsu.borodkin;

public interface Expression {
    public abstract void print();
    public abstract Expression derivative(String var);
    public abstract int eval(String variables);
};