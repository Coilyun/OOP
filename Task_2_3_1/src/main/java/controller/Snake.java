package controller;

import model.Cell;
import model.SnakeData;

import java.util.List;

public class Snake {

    private final int fieldWidth;
    private final int fieldHeight;
    private final SnakeData snakeData;

    private Direction currentDirection;
    private Direction nextDirection;
    private boolean isGrowingOnCurrentStep;



    public Snake(int startX, int startY, int fieldWidth, int fieldHeight) {
        snakeData = new SnakeData(startX, startY);
        currentDirection = Direction.RIGHT;
        nextDirection = Direction.RIGHT;
        isGrowingOnCurrentStep = false;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setDirection(Direction newDirection) {
        // Предотвращаем движение в противоположном направлении
        if ((currentDirection == Direction.UP && newDirection != Direction.DOWN) ||
            (currentDirection == Direction.DOWN && newDirection != Direction.UP) ||
            (currentDirection == Direction.LEFT && newDirection != Direction.RIGHT) ||
            (currentDirection == Direction.RIGHT && newDirection != Direction.LEFT)) {
            this.nextDirection = newDirection;
        }
    }

    public void move() {
        // Обновляем текущее направление
        currentDirection = nextDirection;
        
        // Получаем координаты головы
        Cell head = snakeData.getHead();
        int newX = head.getX();
        int newY = head.getY();

        // Вычисляем новые координаты головы
        switch (currentDirection) {
            case UP:
                newY--;
                break;
            case DOWN:
                newY++;
                break;
            case LEFT:
                newX--;
                break;
            case RIGHT:
                newX++;
                break;
        }

        // Перемещаем змейку через границы экрана
        if (newX < 0) newX = fieldWidth - 1;
        if (newY < 0) newY = fieldHeight - 1;
        if (newX >= fieldWidth) newX = 0;
        if (newY >= fieldHeight) newY = 0;

        snakeData.move(newX, newY, isGrowingOnCurrentStep);
        isGrowingOnCurrentStep = false;
    }

    public SnakeData getSnakeData() {
        return snakeData;
    }

    public void grow() {
        isGrowingOnCurrentStep = true;
    }

    public boolean eat(Cell foodCell) {
        return snakeData.collidesWith(snakeData.getHead(), List.of(foodCell));
    }

    public Cell getNextHeadPosition() {
        Cell head = snakeData.getHead();
        int newX = head.getX();
        int newY = head.getY();
        
        switch (currentDirection) {
            case UP:
                newY = (newY - 1 + fieldHeight) % fieldHeight;
                break;
            case DOWN:
                newY = (newY + 1) % fieldHeight;
                break;
            case LEFT:
                newX = (newX - 1 + fieldWidth) % fieldWidth;
                break;
            case RIGHT:
                newX = (newX + 1) % fieldWidth;
                break;
        }
        
        return new Cell(newX, newY);
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
} 