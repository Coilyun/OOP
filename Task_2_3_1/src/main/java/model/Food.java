package model;

public class Food {
    private Cell position;
    private int value;

    public Food(Cell position) {
        this.position = position;
        this.value = 10; // Стандартное значение очков за еду
    }

    public Food(Cell position, int value) {
        this.position = position;
        this.value = value;
    }

    public Cell getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }
} 