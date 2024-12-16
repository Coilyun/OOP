package ru.nsu.borodkin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IncidenceMatrixGraphTest {
    private IncidenceMatrixGraph graph;

    @BeforeEach
    public void setUp() {
        graph = new IncidenceMatrixGraph(5);  // Инициализация графа с 5 вершинами
    }

    @Test
    public void testAddVertex() {
        graph.addVertex(1);
        graph.addVertex(2);

        List<Integer> neighborsA = graph.getNeighbors(1);
        assertTrue(neighborsA.isEmpty(), "'1' не должен иметь соседей");

        List<Integer> neighborsB = graph.getNeighbors(2);
        assertTrue(neighborsB.isEmpty(), "'2' не должен иметь соседей");
    }

    @Test
    public void testAddEdge() {
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);

        List<Integer> neighbors = graph.getNeighbors(1);
        assertTrue(neighbors.contains(2), "'1' должен иметь соседа '2'");
    }

    @Test
    public void testRemoveVertex() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        assertTrue(graph.getNeighbors(1).contains(2), "'1' должен иметь соседа '2'");

        graph.removeVertex(2);

        List<Integer> neighborsA = graph.getNeighbors(1);
        assertFalse(neighborsA.contains(2), "'1' не должен иметь соседа '2' после удаления '2'");
    }

    @Test
    public void testRemoveEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        graph.removeEdge(1, 2);

        List<Integer> neighbors = graph.getNeighbors(1);
        assertFalse(neighbors.contains(2), "'1' не должен иметь соседа '2' после удаления ребра");
    }

    @Test
    public void testTopologicalSort() {
        // Создаём граф с несколькими вершинами и рёбрами
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph(4);

        // Добавляем рёбра для графа
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);

        // Получаем результат топологической сортировки
        List<Integer> expected = Arrays.asList(0, 1, 2, 3);
        List<Integer> sorted = graph.topologicalSort();

        assertEquals(expected, sorted);
    }

    @Test
    public void testReadFromFile(@TempDir Path tempDir) throws IOException {
        // Создаём временный файл с данными для графа
        Path tempFile = tempDir.resolve("test_graph.txt");

        // Запись данных в файл
        String content = "0 1\n1 2\n2 3\n";
        try {
            Files.write(tempFile, content.getBytes());
        } catch (IOException e) {
            fail("Не удалось записать в файл: " + tempFile);
        }

        // Чтение графа из файла
        graph.readFromFile(tempFile.toString());

        // Проверяем, что граф правильно прочитан из файла
        List<Integer> neighbors0 = graph.getNeighbors(0);
        assertTrue(neighbors0.contains(1), "'0' должен иметь соседа '1'");

        List<Integer> neighbors1 = graph.getNeighbors(1);
        assertTrue(neighbors1.contains(2), "'1' должен иметь соседа '2'");

        List<Integer> neighbors2 = graph.getNeighbors(2);
        assertTrue(neighbors2.contains(3), "'2' должен иметь соседа '3'");

        Files.delete(tempFile);
    }
}
