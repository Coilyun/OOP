package ru.nsu.borodkin;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixGraphTest {

    @Test
    void testAddVertex() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(2);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        assertTrue(graph.getNeighbors(1).isEmpty());
        assertTrue(graph.getNeighbors(2).isEmpty());
        assertTrue(graph.getNeighbors(3).isEmpty());
    }

    @Test
    void testAddEdge() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(2);
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);

        List<Integer> neighbors = graph.getNeighbors(1);
        assertEquals(1, neighbors.size());
        assertTrue(neighbors.contains(2));
    }

    @Test
    void testRemoveEdge() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(2);
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);
        graph.removeEdge(1, 2);

        List<Integer> neighbors = graph.getNeighbors(1);
        assertTrue(neighbors.isEmpty());
    }

    @Test
    void testRemoveVertex() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        graph.removeVertex(2);

        List<Integer> neighbors = graph.getNeighbors(1);
        assertEquals(1, neighbors.size());
        assertTrue(neighbors.contains(3));
    }

    @Test
    void testExpandMatrix() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(1);
        graph.addVertex(1);
        graph.addVertex(2);

        assertDoesNotThrow(() -> graph.addVertex(3));
    }

    @Test
    void testGetNeighbors() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        List<Integer> neighbors = graph.getNeighbors(1);
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains(2));
        assertTrue(neighbors.contains(3));
    }

    @Test
    void testEquals() {
        AdjacencyMatrixGraph graph1 = new AdjacencyMatrixGraph(2);
        AdjacencyMatrixGraph graph2 = new AdjacencyMatrixGraph(2);

        graph1.addVertex(1);
        graph1.addVertex(2);
        graph1.addEdge(1, 2);

        graph2.addVertex(1);
        graph2.addVertex(2);
        graph2.addEdge(1, 2);

        assertEquals(graph1, graph2);
    }
    
    @Test
    void testReadFromFile() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        
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
    void testTopologicalSort() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        
        // Добавляем вершины и рёбра
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        
        List<Integer> sorted = graph.topologicalSort();
        
        // Проверка, что сортировка корректна (1 должен быть до 2, 3 и 4)
        assertTrue(sorted.indexOf(1) < sorted.indexOf(2), "1 должен быть до 2");
        assertTrue(sorted.indexOf(1) < sorted.indexOf(3), "1 должен быть до 3");
        assertTrue(sorted.indexOf(1) < sorted.indexOf(4), "1 должен быть до 4");
        assertTrue(sorted.indexOf(2) < sorted.indexOf(4), "2 должен быть до 4");
        assertTrue(sorted.indexOf(3) < sorted.indexOf(4), "3 должен быть до 4");
    }
}
