package ru.nsu.borodkin;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {

    @Test
    void testAddVertex() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex("A");

        assertTrue(graph.getNeighbors("A").isEmpty(), "Новая вершина должна быть без соседей");
    }

    @Test
    void testRemoveVertex() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");  // Ребро от A к B

        // Проверяем, что ребро A -> B существует
        assertTrue(graph.getNeighbors("A").contains("B"));

        // Удаляем вершину B
        graph.removeVertex("B");

        // Проверяем, что B больше не существует в графе
        assertFalse(graph.getNeighbors("A").contains("B"));  // A не должен иметь соседа B
        assertTrue(graph.getNeighbors("A").isEmpty());  // A не должен иметь других соседей
    }


    



    @Test
    void testAddEdge() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        List<String> neighbors = graph.getNeighbors("A");
        assertTrue(neighbors.contains("B"), "Ребро должно быть добавлено");
    }

    @Test
    void testRemoveEdge() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        graph.removeEdge("A", "B");
        List<String> neighbors = graph.getNeighbors("A");
        assertFalse(neighbors.contains("B"), "Ребро должно быть удалено");
    }

    @Test
    void testGetNeighbors() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        List<String> neighbors = graph.getNeighbors("A");
        assertEquals(1, neighbors.size(), "У вершины A должен быть один сосед");
        assertEquals("B", neighbors.get(0), "Соседом A должна быть вершина B");
    }

    @Test
    void testEquals() {
        AdjacencyListGraph graph1 = new AdjacencyListGraph();
        AdjacencyListGraph graph2 = new AdjacencyListGraph();

        graph1.addVertex("A");
        graph1.addEdge("A", "B");

        graph2.addVertex("A");
        graph2.addEdge("A", "B");

        assertEquals(graph1, graph2, "Графы с одинаковыми данными должны быть равны");
    }

    @Test
    void testToString() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex("A");
        graph.addEdge("A", "B");

        String expected = "A: [B]\n";
        assertEquals(expected, graph.toString(), "Метод toString должен возвращать корректное представление графа");
    }

    @Test
    void testTopologicalSortStub() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addVertex("A");
        List<String> sorted = graph.topologicalSort();

        assertTrue(sorted.isEmpty(), "Заглушка топологической сортировки должна возвращать пустой список");
    }
}
