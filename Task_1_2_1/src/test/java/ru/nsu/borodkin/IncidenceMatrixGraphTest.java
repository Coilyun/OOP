package ru.nsu.borodkin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class IncidenceMatrixGraphTest {
    private IncidenceMatrixGraph graph;

    @BeforeEach
    public void setUp() {
        graph = new IncidenceMatrixGraph();
    }

    @Test
    public void testAddVertex() {
        graph.addVertex("A");
        graph.addVertex("B");

        // Проверяем, что после добавления вершин у "A" нет соседей
        List<String> neighborsA = graph.getNeighbors("A");
        assertTrue(neighborsA.isEmpty(), "'A' не должен иметь соседей");

        // Проверяем, что после добавления вершин у "B" нет соседей
        List<String> neighborsB = graph.getNeighbors("B");
        assertTrue(neighborsB.isEmpty(), "'B' не должен иметь соседей");
    }

    @Test
    public void testAddEdge() {
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        List<String> neighbors = graph.getNeighbors("A");
        assertTrue(neighbors.contains("B"), "'A' должен иметь соседа 'B'");
        assertFalse(neighbors.contains("A"), "'A' не должен быть своим собственным соседом");

        // Проверка отсутствия рёбер, если они не были добавлены
        List<String> neighborsB = graph.getNeighbors("B");
        assertTrue(neighborsB.isEmpty(), "'B' не должен иметь соседей, если рёбер нет");
    }

    @Test
    public void testRemoveVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
    
        // Проверяем, что "A" и "B" имеют рёбра
        assertTrue(graph.getNeighbors("A").contains("B"), "'A' должен иметь соседа 'B'");
    
        // Удаляем вершину "B"
        graph.removeVertex("B");
    
        // Проверяем, что "A" больше не имеет соседа "B"
        List<String> neighborsA = graph.getNeighbors("A");
        assertFalse(neighborsA.contains("B"), "'A' не должен иметь соседа 'B' после удаления 'B'");
    }
    

    @Test
    public void testRemoveEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        graph.removeEdge("A", "B");

        List<String> neighbors = graph.getNeighbors("A");
        assertFalse(neighbors.contains("B"), "'A' не должен иметь соседа 'B' после удаления ребра");
    }

    @Test
    public void testToString() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        String graphString = graph.toString();
        assertTrue(graphString.contains("Vertices: [A, B]"), "Строковое представление должно содержать вершины");
        assertTrue(graphString.contains("Edges:"), "Строковое представление должно содержать рёбра");
    }
}
