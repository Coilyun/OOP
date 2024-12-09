package ru.nsu.borodkin;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixGraphTest {

    @Test
    void testAddVertex() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(2);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        assertTrue(graph.getNeighbors("A").isEmpty());
        assertTrue(graph.getNeighbors("B").isEmpty());
        assertTrue(graph.getNeighbors("C").isEmpty());
    }

    @Test
    void testAddEdge() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(2);
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        List<String> neighbors = graph.getNeighbors("A");
        assertEquals(1, neighbors.size());
        assertTrue(neighbors.contains("B"));
    }

    @Test
    void testRemoveEdge() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(2);
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");
        graph.removeEdge("A", "B");

        List<String> neighbors = graph.getNeighbors("A");
        assertTrue(neighbors.isEmpty());
    }

    @Test
    void testRemoveVertex() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(2);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        graph.removeVertex("B");

        List<String> neighbors = graph.getNeighbors("A");
        assertEquals(1, neighbors.size());
        assertTrue(neighbors.contains("C"));
    }

    @Test
    void testExpandMatrix() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(1);
        graph.addVertex("A");
        graph.addVertex("B");

        assertDoesNotThrow(() -> graph.addVertex("C")); // Проверяем, что добавление больше начальной емкости работает
    }

    @Test
    void testGetNeighbors() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        List<String> neighbors = graph.getNeighbors("A");
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains("B"));
        assertTrue(neighbors.contains("C"));
    }

    @Test
    void testEquals() {
        AdjacencyMatrixGraph graph1 = new AdjacencyMatrixGraph(2);
        AdjacencyMatrixGraph graph2 = new AdjacencyMatrixGraph(2);

        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addEdge("A", "B");

        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addEdge("A", "B");

        assertEquals(graph1, graph2);
    }

    @Test
    void testToString() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(2);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        String expected = "0: 0 1 \n1: 0 0 \n";
        assertEquals(expected, graph.toString());
    }

}
