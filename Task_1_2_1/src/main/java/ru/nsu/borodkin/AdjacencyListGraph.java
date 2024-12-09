package ru.nsu.borodkin;
import java.util.*;

public class AdjacencyListGraph implements Graph {
    private final Map<String, List<String>> adjacencyList = new HashMap<>();

    @Override
    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void removeVertex(String vertex) {
        adjacencyList.remove(vertex);

        for (List<String> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
    }


    @Override
    public void addEdge(String from, String to) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    @Override
    public void removeEdge(String from, String to) {
        List<String> neighbors = adjacencyList.get(from);
        if (neighbors != null) {
            neighbors.remove(to);
        }
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    @Override
    public void readFromFile(String filePath) {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdjacencyListGraph)) return false;
        AdjacencyListGraph other = (AdjacencyListGraph) obj;
        return adjacencyList.equals(other.adjacencyList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<String> topologicalSort() {
        return Collections.emptyList();
    }
}
