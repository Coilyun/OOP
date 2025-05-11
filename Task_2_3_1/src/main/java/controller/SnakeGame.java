package controller;

import model.Cell;
import model.Food;
import model.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame {
    private int width;
    private int height;
    private Snake snake;
    private List<Food> foods;
    private List<Wall> walls;
    private boolean isGameOver;
    private int score;
    private int maxFoodCount;
    private int gameSpeed;
    private static final int MIN_WALLS = 2;
    private static final int MAX_WALLS = 5;
    private static final int WALL_RADIUS = 3; // Минимальное расстояние между стенами

    public SnakeGame(int width, int height, int maxFoodCount, int gameSpeed) {
        this.width = width;
        this.height = height;
        this.foods = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.score = 0;
        this.isGameOver = false;
        this.maxFoodCount = maxFoodCount;
        this.gameSpeed = gameSpeed;
        
        // Инициализация змейки в центре поля
        int startX = width / 2;
        int startY = height / 2;
        this.snake = new Snake(startX, startY, width, height);
        
        // Добавление стен
        generateWalls();
        
        // Добавление начальной еды
        for (int i = 0; i < maxFoodCount; i++) {
            addFood();
        }
    }

    private void generateWalls() {
        Random random = new Random();
        int wallCount = random.nextInt(MAX_WALLS - MIN_WALLS + 1) + MIN_WALLS;
        
        for (int i = 0; i < wallCount; i++) {
            boolean validPosition = false;
            int attempts = 0;
            int x = 0, y = 0;
            int wallLength = random.nextInt(3) + 2; // Длина стены от 2 до 4 единиц
            boolean isHorizontal = random.nextBoolean(); // Случайное направление стены
            
            // Пытаемся найти подходящую позицию для стены
            while (!validPosition && attempts < 100) {
                x = random.nextInt(width - (isHorizontal ? wallLength : 0));
                y = random.nextInt(height - (isHorizontal ? 0 : wallLength));
                
                // Проверяем, не находится ли позиция слишком близко к змейке
                if (isNearSnake(x, y, wallLength, isHorizontal)) {
                    attempts++;
                    continue;
                }
                
                // Проверяем, не находится ли позиция слишком близко к другим стенам
                if (isNearOtherWalls(x, y, wallLength, isHorizontal)) {
                    attempts++;
                    continue;
                }
                
                validPosition = true;
            }
            
            if (validPosition) {
                // Создаем стену заданной длины
                for (int j = 0; j < wallLength; j++) {
                    int wallX = isHorizontal ? x + j : x;
                    int wallY = isHorizontal ? y : y + j;
                    walls.add(new Wall(new Cell(wallX, wallY)));
                }
            }
        }
    }

    private boolean isNearSnake(int x, int y, int length, boolean isHorizontal) {
        for (int i = 0; i < length; i++) {
            int checkX = isHorizontal ? x + i : x;
            int checkY = isHorizontal ? y : y + i;
            
            for (Cell cell : snake.getSnakeData().getBody()) {
                if (Math.abs(cell.getX() - checkX) <= WALL_RADIUS && 
                    Math.abs(cell.getY() - checkY) <= WALL_RADIUS) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNearOtherWalls(int x, int y, int length, boolean isHorizontal) {
        for (int i = 0; i < length; i++) {
            int checkX = isHorizontal ? x + i : x;
            int checkY = isHorizontal ? y : y + i;
            
            for (Wall wall : walls) {
                if (Math.abs(wall.getPosition().getX() - checkX) <= WALL_RADIUS && 
                    Math.abs(wall.getPosition().getY() - checkY) <= WALL_RADIUS) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean move() {
        if (isGameOver) {
            return false;
        }

        // Получаем следующую позицию головы
        Cell nextHead = snake.getNextHeadPosition();
        Cell currentHead = snake.getSnakeData().getHead();
        
        // Проверяем все возможные промежуточные позиции при повороте
        for (Wall wall : walls) {
            int wallX = wall.getPosition().getX();
            int wallY = wall.getPosition().getY();
            
            // Проверяем столкновение с текущей позицией головы
            if (currentHead.getX() == wallX && currentHead.getY() == wallY) {
                isGameOver = true;
                return false;
            }
            
            // Проверяем столкновение со следующей позицией головы
            if (nextHead.getX() == wallX && nextHead.getY() == wallY) {
                isGameOver = true;
                return false;
            }
            
            // Проверяем промежуточные позиции при повороте
            if (snake.getSnakeData().biggerThanOne()) {
                Cell secondCell = snake.getSnakeData().getSecondCell();
                
                // Если змейка поворачивает
                if (currentHead.getX() != secondCell.getX() && currentHead.getY() != secondCell.getY()) {
                    // Проверяем клетки, через которые пройдет тело при повороте
                    if ((wallX == currentHead.getX() && wallY == secondCell.getY()) ||
                        (wallX == secondCell.getX() && wallY == currentHead.getY())) {
                        isGameOver = true;
                        return false;
                    }
                }
            }
        }
        
        // Перемещаем змейку
        snake.move();
        
        // Проверяем столкновение с собой
        if (snake.getSnakeData().collidesWithSelf()) {
            isGameOver = true;
            return false;
        }

        // Проверяем столкновение с едой
        Cell head = snake.getSnakeData().getHead();
        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            if (head.getX() == food.getPosition().getX() && head.getY() == food.getPosition().getY()) {
                snake.grow();
                score += 10;
                foods.remove(i);
                addFood();
                break;
            }
        }

        // Добавляем новую еду, если её мало
        while (foods.size() < maxFoodCount) {
            addFood();
        }

        return true;
    }

    private void addFood() {
        Random random = new Random();
        Cell foodCell;
        boolean validPosition;

        do {
            validPosition = true;
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            foodCell = new Cell(x, y);

            // Проверка на пересечение со змейкой
            if (snake.eat(foodCell)) {
                validPosition = false;
                continue;
            }

            // Проверка на пересечение со стенами
            for (Wall wall : walls) {
                if (foodCell.equals(wall.getPosition())) {
                    validPosition = false;
                    break;
                }
            }

            // Проверка на пересечение с другой едой
            for (Food food : foods) {
                if (foodCell.equals(food.getPosition())) {
                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);

        foods.add(new Food(foodCell));
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getScore() {
        return score;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public int getMaxFoodCount() {
        return maxFoodCount;
    }

    public void setMaxFoodCount(int maxFoodCount) {
        this.maxFoodCount = maxFoodCount;
        // Обновляем количество еды на поле
        while (foods.size() < maxFoodCount) {
            addFood();
        }
        while (foods.size() > maxFoodCount) {
            foods.remove(foods.size() - 1);
        }
    }
} 