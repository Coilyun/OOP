package model;

public class Wall {
    private Cell position;

    public Wall(Cell position) {
        this.position = position;
    }

    public Cell getPosition() {
        return position;
    }
} 