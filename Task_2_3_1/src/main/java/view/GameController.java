package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import javafx.stage.Stage;
import model.*;
import java.util.List;
import javafx.scene.shape.Line;

public class GameController {
    private static final int CELL_SIZE = 20;
    private static final int MIN_SPEED = 200;
    private static final int MAX_SPEED = 500;
    private static final int DEFAULT_SPEED = 300;
    private static final int DEFAULT_FOOD_COUNT = 3;

    @FXML private GridPane gameGrid;
    @FXML private Label scoreLabel;
    @FXML private Button startButton;
    @FXML private Button newGameButton;
    @FXML private Slider speedSlider;
    @FXML private Slider foodCountSlider;
    @FXML private Label speedLabel;
    @FXML private Label foodCountLabel;

    private SnakeGame game;
    private Timeline gameLoop;
    private boolean isPaused;

    @FXML
    public void initialize() {
        setupSliders();
        
        // Инициализация игры
        game = new SnakeGame(20, 20, (int)foodCountSlider.getValue(), (int)speedSlider.getValue());
        isPaused = true;
        
        // Сброс текста кнопки Старт
        startButton.setText("Старт");
        
        // Настройка игрового поля
        setupGameGrid();
        
        // Создание игрового цикла
        gameLoop = new Timeline(new KeyFrame(Duration.millis(game.getGameSpeed()), e -> updateGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        
        // Настройка обработчиков клавиш
        setupKeyHandlers();
        
        // Обновление интерфейса
        updateUI();
    }

    private void setupSliders() {
        // Настройка слайдера скорости
        speedSlider.setMin(MIN_SPEED);
        speedSlider.setMax(MAX_SPEED);
        speedSlider.setValue(DEFAULT_SPEED);
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            speedLabel.setText("Скорость: " + newVal.intValue());
            if (game != null) {
                game.setGameSpeed(newVal.intValue());
                // Инвертируем скорость: чем больше значение, тем быстрее
                gameLoop.setRate((double)newVal.intValue() / MIN_SPEED);
            }
        });

        // Настройка слайдера количества еды
        foodCountSlider.setMin(1);
        foodCountSlider.setMax(10);
        foodCountSlider.setValue(DEFAULT_FOOD_COUNT);
        foodCountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            foodCountLabel.setText("Количество еды: " + newVal.intValue());
            if (game != null) {
                game.setMaxFoodCount(newVal.intValue());
            }
        });
    }

    private void setupGameGrid() {
        // Очистка сетки
        gameGrid.getChildren().clear();
        
        // Создание клеток
        for (int i = 0; i < game.getHeight(); i++) {
            for (int j = 0; j < game.getWidth(); j++) {
                Pane cell = new Pane();
                cell.setPrefSize(CELL_SIZE, CELL_SIZE);
                cell.setStyle("-fx-background-color: #2c3e50;");
                gameGrid.add(cell, j, i);
            }
        }
    }

    private void setupKeyHandlers() {
        gameGrid.setOnKeyPressed(event -> {
            if (isPaused) return;
            
            KeyCode code = event.getCode();
            switch (code) {
                case W:
                    game.getSnake().setDirection(Snake.Direction.UP);
                    break;
                case S:
                    game.getSnake().setDirection(Snake.Direction.DOWN);
                    break;
                case A:
                    game.getSnake().setDirection(Snake.Direction.LEFT);
                    break;
                case D:
                    game.getSnake().setDirection(Snake.Direction.RIGHT);
                    break;
                default:
                    break;
            }
        });
        
        // Устанавливаем фокус на игровое поле
        gameGrid.setFocusTraversable(true);
        gameGrid.requestFocus();
    }

    private void updateGame() {
        if (isPaused) return;
        
        game.move();
        updateUI();
        
        if (game.isGameOver()) {
            gameLoop.stop();
            isPaused = true;
            // Скрываем кнопку Старт
            startButton.setVisible(false);
            // Используем Platform.runLater для безопасного отображения диалога
            javafx.application.Platform.runLater(this::showGameOver);
        }
    }

    private void updateUI() {
        // Обновление счета
        scoreLabel.setText("Счет: " + game.getScore());
        
        // Очистка поля
        for (int i = 0; i < game.getHeight(); i++) {
            for (int j = 0; j < game.getWidth(); j++) {
                Pane cell = (Pane) gameGrid.getChildren().get(i * game.getWidth() + j);
                cell.setStyle("-fx-background-color: #2c3e50;");
                cell.getChildren().clear();
            }
        }
        
        // Отрисовка змейки
        List<Cell> snakeBody = game.getSnake().getBody();
        for (int i = 0; i < snakeBody.size(); i++) {
            Cell cell = snakeBody.get(i);
            Pane pane = (Pane) gameGrid.getChildren().get(cell.getY() * game.getWidth() + cell.getX());
            pane.getChildren().clear();
            
            if (i == 0) { // Голова
                Circle head = new Circle(CELL_SIZE / 2.0, CELL_SIZE / 2.0, CELL_SIZE / 2.0);
                head.setFill(Color.valueOf("#2ecc71"));
                
                // Добавляем глаза
                Circle leftEye = new Circle(CELL_SIZE * 0.2);
                Circle rightEye = new Circle(CELL_SIZE * 0.2);
                leftEye.setFill(Color.WHITE);
                rightEye.setFill(Color.WHITE);
                
                // Позиционируем глаза в зависимости от направления
                Snake.Direction dir = game.getSnake().getCurrentDirection();
                switch (dir) {
                    case UP:
                        leftEye.setCenterX(CELL_SIZE * 0.3);
                        leftEye.setCenterY(CELL_SIZE * 0.3);
                        rightEye.setCenterX(CELL_SIZE * 0.7);
                        rightEye.setCenterY(CELL_SIZE * 0.3);
                        break;
                    case DOWN:
                        leftEye.setCenterX(CELL_SIZE * 0.3);
                        leftEye.setCenterY(CELL_SIZE * 0.7);
                        rightEye.setCenterX(CELL_SIZE * 0.7);
                        rightEye.setCenterY(CELL_SIZE * 0.7);
                        break;
                    case LEFT:
                        leftEye.setCenterX(CELL_SIZE * 0.3);
                        leftEye.setCenterY(CELL_SIZE * 0.3);
                        rightEye.setCenterX(CELL_SIZE * 0.3);
                        rightEye.setCenterY(CELL_SIZE * 0.7);
                        break;
                    case RIGHT:
                        leftEye.setCenterX(CELL_SIZE * 0.7);
                        leftEye.setCenterY(CELL_SIZE * 0.3);
                        rightEye.setCenterX(CELL_SIZE * 0.7);
                        rightEye.setCenterY(CELL_SIZE * 0.7);
                        break;
                }
                pane.getChildren().addAll(head, leftEye, rightEye);
            } else if (i == snakeBody.size() - 1) { // Хвост
                // Создаем овал для хвоста
                Circle tail = new Circle(CELL_SIZE / 2.0, CELL_SIZE / 2.0, CELL_SIZE / 2.0 * 0.6);
                tail.setFill(Color.valueOf("#27ae60"));
                pane.getChildren().add(tail);
            } else { // Тело
                Circle body = new Circle(CELL_SIZE / 2.0, CELL_SIZE / 2.0, CELL_SIZE / 2.0 * 0.8);
                body.setFill(Color.valueOf("#27ae60"));
                pane.getChildren().add(body);
            }
        }
        
        // Отрисовка еды
        for (Food food : game.getFoods()) {
            Cell pos = food.getPosition();
            Pane pane = (Pane) gameGrid.getChildren().get(pos.getY() * game.getWidth() + pos.getX());
            pane.getChildren().clear();
            
            // Яблоко
            Circle apple = new Circle(CELL_SIZE / 2.0, CELL_SIZE / 2.0, CELL_SIZE / 2.0 * 0.8);
            apple.setFill(Color.valueOf("#e74c3c"));
            
            // Листок
            Polygon leaf = new Polygon();
            Double[] leafPoints = {
                CELL_SIZE / 2.0, CELL_SIZE * 0.1,  // Верхняя точка
                CELL_SIZE * 0.8, CELL_SIZE * 0.3,  // Правая точка
                CELL_SIZE * 0.4, CELL_SIZE * 0.4   // Нижняя точка
            };
            leaf.getPoints().addAll(leafPoints);
            leaf.setFill(Color.valueOf("#2ecc71"));
            
            // Черешок
            javafx.scene.shape.Line stem = new javafx.scene.shape.Line(
                CELL_SIZE / 2.0, CELL_SIZE * 0.4,  // Начало черешка
                CELL_SIZE / 2.0, CELL_SIZE * 0.1   // Конец черешка
            );
            stem.setStroke(Color.valueOf("#2ecc71"));
            stem.setStrokeWidth(2);
            
            pane.getChildren().addAll(apple, stem, leaf);
        }
        
        // Отрисовка стен
        for (Wall wall : game.getWalls()) {
            Cell pos = wall.getPosition();
            Pane pane = (Pane) gameGrid.getChildren().get(pos.getY() * game.getWidth() + pos.getX());
            pane.getChildren().clear();
            
            // Создаем стену
            javafx.scene.shape.Rectangle wallRect = new javafx.scene.shape.Rectangle(CELL_SIZE, CELL_SIZE);
            wallRect.setFill(Color.valueOf("#8B4513")); // Коричневый цвет для стен
            wallRect.setStroke(Color.valueOf("#654321")); // Темно-коричневый для обводки
            wallRect.setStrokeWidth(2);
            
            // Добавляем текстуру стены
            for (int i = 0; i < 3; i++) {
                javafx.scene.shape.Line line = new javafx.scene.shape.Line(
                    CELL_SIZE * 0.2, CELL_SIZE * (0.2 + i * 0.3),
                    CELL_SIZE * 0.8, CELL_SIZE * (0.2 + i * 0.3)
                );
                line.setStroke(Color.valueOf("#A0522D"));
                line.setStrokeWidth(1);
                pane.getChildren().add(line);
            }
            
            pane.getChildren().add(wallRect);
        }
    }

    private void showGameOver() {
        // Анимация проигрыша
        Timeline blinkAnimation = new Timeline(
            new KeyFrame(Duration.seconds(0.2), e -> {
                List<Cell> snakeBody = game.getSnake().getBody();
                for (int i = 0; i < snakeBody.size(); i++) {
                    Cell cell = snakeBody.get(i);
                    Pane pane = (Pane) gameGrid.getChildren().get(cell.getY() * game.getWidth() + cell.getX());
                    pane.getChildren().clear();
                    
                    if (i == 0) { // Голова
                        // Создаем круг для анимации смерти головы
                        Circle deathCircle = new Circle(CELL_SIZE / 2.0, CELL_SIZE / 2.0, CELL_SIZE / 2.0 * 0.8);
                        deathCircle.setFill(Color.valueOf("#c0392b"));
                        deathCircle.setStroke(Color.valueOf("#e74c3c"));
                        deathCircle.setStrokeWidth(2);
                        
                        // Добавляем эффект "мертвых глаз"
                        Circle leftEye = new Circle(CELL_SIZE * 0.2);
                        Circle rightEye = new Circle(CELL_SIZE * 0.2);
                        leftEye.setFill(Color.WHITE);
                        rightEye.setFill(Color.WHITE);
                        
                        // Позиционируем глаза
                        leftEye.setCenterX(CELL_SIZE * 0.3);
                        leftEye.setCenterY(CELL_SIZE * 0.3);
                        rightEye.setCenterX(CELL_SIZE * 0.7);
                        rightEye.setCenterY(CELL_SIZE * 0.3);
                        
                        // Добавляем "X" на глазах
                        Line leftX1 = new Line(CELL_SIZE * 0.2, CELL_SIZE * 0.2, CELL_SIZE * 0.4, CELL_SIZE * 0.4);
                        Line leftX2 = new Line(CELL_SIZE * 0.4, CELL_SIZE * 0.2, CELL_SIZE * 0.2, CELL_SIZE * 0.4);
                        Line rightX1 = new Line(CELL_SIZE * 0.6, CELL_SIZE * 0.2, CELL_SIZE * 0.8, CELL_SIZE * 0.4);
                        Line rightX2 = new Line(CELL_SIZE * 0.8, CELL_SIZE * 0.2, CELL_SIZE * 0.6, CELL_SIZE * 0.4);
                        
                        leftX1.setStroke(Color.BLACK);
                        leftX2.setStroke(Color.BLACK);
                        rightX1.setStroke(Color.BLACK);
                        rightX2.setStroke(Color.BLACK);
                        
                        pane.getChildren().addAll(deathCircle, leftEye, rightEye, leftX1, leftX2, rightX1, rightX2);
                    } else { // Тело
                        // Просто красный круг для тела
                        Circle bodyCircle = new Circle(CELL_SIZE / 2.0, CELL_SIZE / 2.0, CELL_SIZE / 2.0 * 0.8);
                        bodyCircle.setFill(Color.valueOf("#c0392b"));
                        bodyCircle.setStroke(Color.valueOf("#e74c3c"));
                        bodyCircle.setStrokeWidth(2);
                        pane.getChildren().add(bodyCircle);
                    }
                }
            }),
            new KeyFrame(Duration.seconds(0.4), e -> {
                List<Cell> snakeBody = game.getSnake().getBody();
                for (int i = 0; i < snakeBody.size(); i++) {
                    Cell cell = snakeBody.get(i);
                    Pane pane = (Pane) gameGrid.getChildren().get(cell.getY() * game.getWidth() + cell.getX());
                    pane.getChildren().clear();
                    
                    if (i == 0) { // Голова
                        Circle head = new Circle(CELL_SIZE / 2.0, CELL_SIZE / 2.0, CELL_SIZE / 2.0);
                        head.setFill(Color.valueOf("#2ecc71"));
                        head.setStroke(Color.valueOf("#27ae60"));
                        head.setStrokeWidth(2);
                        
                        // Добавляем глаза
                        Circle leftEye = new Circle(CELL_SIZE * 0.2);
                        Circle rightEye = new Circle(CELL_SIZE * 0.2);
                        leftEye.setFill(Color.WHITE);
                        rightEye.setFill(Color.WHITE);
                        
                        // Позиционируем глаза
                        leftEye.setCenterX(CELL_SIZE * 0.3);
                        leftEye.setCenterY(CELL_SIZE * 0.3);
                        rightEye.setCenterX(CELL_SIZE * 0.7);
                        rightEye.setCenterY(CELL_SIZE * 0.3);
                        
                        pane.getChildren().addAll(head, leftEye, rightEye);
                    } else { // Тело
                        Circle body = new Circle(CELL_SIZE / 2.0, CELL_SIZE / 2.0, CELL_SIZE / 2.0 * 0.8);
                        body.setFill(Color.valueOf("#27ae60"));
                        body.setStroke(Color.valueOf("#2ecc71"));
                        body.setStrokeWidth(2);
                        pane.getChildren().add(body);
                    }
                }
            })
        );
        blinkAnimation.setCycleCount(3);
        blinkAnimation.setOnFinished(e -> {
            // Блокируем взаимодействие с игрой
            gameGrid.setDisable(true);
            startButton.setDisable(true);
            newGameButton.setDisable(true);
            
            // Показываем диалог в отдельном потоке
            javafx.application.Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Игра окончена");
                alert.setHeaderText(null);
                alert.setContentText("Ваш счет: " + game.getScore());
                
                // Делаем диалог модальным
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.setAlwaysOnTop(true);
                
                alert.showAndWait();
                
                // Разблокируем кнопку "Новая игра"
                newGameButton.setDisable(false);
            });
        });
        blinkAnimation.play();
    }

    @FXML
    private void handleStartButton() {
        if (isPaused) {
            gameLoop.play();
            isPaused = false;
            startButton.setText("Пауза");
            gameGrid.requestFocus(); // Возвращаем фокус на игровое поле
            
            // Блокируем слайдеры во время игры
            speedSlider.setDisable(true);
            foodCountSlider.setDisable(true);
        } else {
            gameLoop.pause();
            isPaused = true;
            startButton.setText("Старт");
            
            // Разблокируем слайдеры при паузе
            speedSlider.setDisable(true);
            foodCountSlider.setDisable(true);
        }
    }

    @FXML
    private void handleNewGameButton() {
        gameLoop.stop();
        isPaused = true;
        // Показываем кнопку Старт
        startButton.setVisible(true);
        startButton.setDisable(false);
        // Сбрасываем текст кнопки Старт
        startButton.setText("Старт");
        // Разблокируем игровое поле
        gameGrid.setDisable(false);
        // Разблокируем слайдеры
        speedSlider.setDisable(false);
        foodCountSlider.setDisable(false);
        initialize();
    }
} 