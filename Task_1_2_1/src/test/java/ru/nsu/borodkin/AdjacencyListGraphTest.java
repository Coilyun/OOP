package ru.nsu.borodkin;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {

    @Test
    void testAddVertex() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex(1);

        assertTrue(graph.getNeighbors(1).isEmpty(), "Новая вершина должна быть без соседей");
    }

    @Test
    void testRemoveVertex() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        graph.removeVertex(2);

        assertFalse(graph.getNeighbors(2).contains(1));  // 1 не должен иметь соседа 2
    }

    @Test
    void testAddEdge() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);

        List<Integer> neighbors = graph.getNeighbors(1);
        assertTrue(neighbors.contains(2), "Ребро должно быть добавлено");
    }

    @Test
    void testRemoveEdge() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        graph.removeEdge(1, 2);
        List<Integer> neighbors = graph.getNeighbors(1);
        assertFalse(neighbors.contains(2), "Ребро должно быть удалено");
    }

    @Test
    void testGetNeighbors() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        List<Integer> neighbors = graph.getNeighbors(1);
        assertEquals(1, neighbors.size(), "У вершины 1 должен быть один сосед");
        assertEquals(2, neighbors.get(0), "Соседом вершины 1 должна быть вершина 2");
    }

    @Test
    void testEquals() {
        AdjacencyListGraph graph1 = new AdjacencyListGraph();
        AdjacencyListGraph graph2 = new AdjacencyListGraph();

        graph1.addVertex(1);
        graph1.addEdge(1, 2);

        graph2.addVertex(1);
        graph2.addEdge(1, 2);

        assertEquals(graph1, graph2, "Графы с одинаковыми данными должны быть равны");
    }
    @Test
    void testToString() {
        // Создаём граф с несколькими вершинами и рёбрами
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        // Ожидаемое представление графа в строковом формате
        String expected = "1: [2, 3]\n2: [3]\n3: []\n";

        // Проверяем, что результат метода toString соответствует ожидаемому
        assertEquals(expected, graph.toString(), "Метод toString должен возвращать правильное представление графа");
    }
    @Test
    void testTopologicalSort() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        
        List<Integer> sorted = graph.topologicalSort();
        
        // Проверка, что сортировка корректна (1 должен быть до 2 и 3)
        assertTrue(sorted.indexOf(1) < sorted.indexOf(2), "1 должен быть до 2");
        assertTrue(sorted.indexOf(1) < sorted.indexOf(3), "1 должен быть до 3");
    }

    @Test
    void testReadFromFile() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        
        // Создаём временный файл с данными для графа
        String filename = "test_graph.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("1 2 3\n");
            writer.write("2 4\n");
            writer.write("3 4\n");
        } catch (IOException e) {
            fail("Не удалось записать в файл: " + filename);
        }
        
        // Чтение из файла
        graph.readFromFile(filename);
        
        // Проверка графа после чтения
        List<Integer> neighbors1 = graph.getNeighbors(1);
        assertTrue(neighbors1.contains(2) && neighbors1.contains(3), "Вершина 1 должна иметь соседи 2 и 3");
        
        List<Integer> neighbors2 = graph.getNeighbors(2);
        assertTrue(neighbors2.contains(4), "Вершина 2 должна иметь соседа 4");
        
        List<Integer> neighbors3 = graph.getNeighbors(3);
        assertTrue(neighbors3.contains(4), "Вершина 3 должна иметь соседа 4");
        
        // Удаляем временный файл
        new File(filename).delete();
    }

    @Test
    void testReadFromFileWithInvalidFile() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        
        // Попытка прочитать несуществующий файл
        assertThrows(RuntimeException.class, () -> graph.readFromFile("non_existent_file.txt"), "Ошибка чтения из файла должна быть выброшена");
    }

}
