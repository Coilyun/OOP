package model;

import java.util.ArrayList;
import java.util.List;

public class SnakeData {
    private final List<Cell> body = new ArrayList<>();

    public SnakeData(int startX, int startY) {
        body.add(new Cell(startX, startY)); // Голова
        body.add(new Cell(startX - 1, startY)); // Тело
        body.add(new Cell(startX - 2, startY)); // Хвост

    }

    public Cell getHead() {
        return body.get(0);
    }

    public List<Cell> getBody() {
        return body;
    }

    public void move(int newX, int newY, boolean isGrowingOnCurrentStep) {

        // Добавляем новую голову
        addNewHead(newX, newY);

        // Удаляем хвост, если змея не растет
        if (!isGrowingOnCurrentStep) {
            removeLastSegment();
        }
    }

    public boolean collidesWithSelf() {
        // Проверяем столкновение с остальным телом
        return collidesWith(getHead(), bodyWithoutHead());
    }

    public boolean collidesWith(Cell cell, List<Cell> target) {
        // Проверяем столкновение со всем телом, кроме головы
        for (Cell targetCell : target) {
            if (targetCell.equals(cell)) {
                return true;
            }
        }
        return false;
    }

    private void addNewHead(int newX, int newY) {
        body.add(0, new Cell(newX, newY));
    }

    private void removeLastSegment() {
        body.remove(body.size() - 1);
    }

    private List<Cell> bodyWithoutHead() {
        return body.subList(1, body.size());
    }

    public boolean biggerThanOne() {
        return body.size() > 1;
    }

    public Cell getSecondCell() {
        return body.get(1);
    }
}
