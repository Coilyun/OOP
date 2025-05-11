package model;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private List<Cell> body;
    private Direction currentDirection;
    private Direction nextDirection;
    private boolean isGrowing;
    private int width;
    private int height;

    public Snake(int startX, int startY, int width, int height) {
        body = new ArrayList<>();
        body.add(new Cell(startX, startY)); // Голова
        body.add(new Cell(startX - 1, startY)); // Тело
        body.add(new Cell(startX - 2, startY)); // Хвост
        currentDirection = Direction.RIGHT;
        nextDirection = Direction.RIGHT;
        isGrowing = false;
        this.width = width;
        this.height = height;
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
            nextDirection = newDirection;
        }
    }

    public void move() {
        // Обновляем текущее направление
        currentDirection = nextDirection;
        
        // Получаем координаты головы
        Cell head = body.get(0);
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
        if (newX < 0) newX = width - 1;
        if (newY < 0) newY = height - 1;
        if (newX >= width) newX = 0;
        if (newY >= height) newY = 0;

        // Добавляем новую голову
        body.add(0, new Cell(newX, newY));

        // Удаляем хвост, если змея не растет
        if (!isGrowing) {
            body.remove(body.size() - 1);
        } else {
            isGrowing = false;
        }
    }

    public List<Cell> getBody() {
        return body;
    }

    public void grow() {
        isGrowing = true;
    }

    public boolean collidesWith(Cell cell) {
        // Проверяем столкновение со всем телом, кроме головы
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(cell)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesWithWall(int width, int height) {
        // Теперь змейка может проходить через стены
        return false;
    }

    public boolean collidesWithSelf() {
        Cell head = body.get(0);
        // Проверяем столкновение с остальным телом
        return collidesWith(head);
    }

    public Cell getNextHeadPosition() {
        Cell head = body.get(0);
        int newX = head.getX();
        int newY = head.getY();
        
        switch (currentDirection) {
            case UP:
                newY = (newY - 1 + height) % height;
                break;
            case DOWN:
                newY = (newY + 1) % height;
                break;
            case LEFT:
                newX = (newX - 1 + width) % width;
                break;
            case RIGHT:
                newX = (newX + 1) % width;
                break;
        }
        
        return new Cell(newX, newY);
    }
} 