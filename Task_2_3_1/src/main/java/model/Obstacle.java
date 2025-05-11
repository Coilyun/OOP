package model;

public class Obstacle {
    private Cell position;

    public Obstacle(Cell position) {
        this.position = position;
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }
} 