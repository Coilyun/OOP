package ru.nsu.borodkin;

public class Variable implements Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.print(name);
    }

    @Override
    public Expression derivative(String var) {
        return new Number(name.equals(var) ? 1 : 0);
    }

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
