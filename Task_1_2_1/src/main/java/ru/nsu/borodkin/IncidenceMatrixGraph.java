package ru.nsu.borodkin;
import java.util.*;

public class IncidenceMatrixGraph implements Graph {
    private final List<String> vertices = new ArrayList<>();
    private final List<int[]> edges = new ArrayList<>();
    

    @Override
    public void addVertex(String vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
    }

    @Override
    public void removeVertex(String vertex) {
        int vertexIndex = vertices.indexOf(vertex);
        if (vertexIndex == -1) return;

        vertices.remove(vertexIndex);

        // Удаляем связанные ребра
        edges.removeIf(edge -> edge[vertexIndex] != 0);
    }

    @Override
    public void addEdge(String from, String to) {
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);

        if (fromIndex == -1 || toIndex == -1) return;

        int[] edge = new int[vertices.size()];
        edge[fromIndex] = 1;
        edge[toIndex] = -1;
        edges.add(edge);
    }

    @Override
    public void removeEdge(String from, String to) {
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);

        if (fromIndex == -1 || toIndex == -1) return;

        edges.removeIf(edge -> edge[fromIndex] == 1 && edge[toIndex] == -1);
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        int vertexIndex = vertices.indexOf(vertex);
        if (vertexIndex == -1) return Collections.emptyList();

        List<String> neighbors = new ArrayList<>();
        for (int[] edge : edges) {
            if (edge[vertexIndex] == 1) { // Исходящее ребро
                for (int i = 0; i < edge.length; i++) {
                    if (edge[i] == -1) {
                        neighbors.add(vertices.get(i));
                    }
                }
            }
        }
        return neighbors;
    }

    @Override
    public void readFromFile(String filePath) {
        // Чтение графа из файла (реализация зависит от формата файла)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Если ссылки одинаковые
        if (o == null || getClass() != o.getClass()) return false;  // Если объект другого типа

        IncidenceMatrixGraph that = (IncidenceMatrixGraph) o;
        
        // Сравниваем вершины и рёбра
        return Objects.equals(vertices, that.vertices) && Objects.equals(edges, that.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices, edges);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\n");
        sb.append("Edges:\n");
        for (int[] edge : edges) {
            sb.append(Arrays.toString(edge)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<String> topologicalSort() {
        return Collections.emptyList(); // Заглушка
    }
}
