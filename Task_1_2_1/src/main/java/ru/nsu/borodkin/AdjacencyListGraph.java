package ru.nsu.borodkin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AdjacencyListGraph implements Graph {
    private final Map<Integer, List<Integer>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    @Override
    public void addVertex(Integer vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void removeVertex(Integer vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return;
        }

        for (Integer neighbor : adjacencyList.get(vertex)) {
            adjacencyList.get(neighbor).remove(vertex);
        }

        adjacencyList.remove(vertex);
    }

    @Override
    public void addEdge(Integer from, Integer to) {
        addVertex(from);
        addVertex(to);
        if (!adjacencyList.get(from).contains(to)) {
            adjacencyList.get(from).add(to);
        }
    }

    @Override
    public void removeEdge(Integer from, Integer to) {
        List<Integer> neighbors = adjacencyList.get(from);
        if (neighbors != null) {
            neighbors.remove(to);
        }
    }

    @Override
    public List<Integer> getNeighbors(Integer vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    @Override
    public void readFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] edges = line.split(" ");
                int vertex = Integer.parseInt(edges[0]);
                addVertex(vertex);
                for (int i = 1; i < edges.length; i++) {
                    Integer connectedVertex = Integer.parseInt(edges[i]);
                    addEdge(vertex, connectedVertex);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AdjacencyListGraph)) {
            return false;
        }
        AdjacencyListGraph other = (AdjacencyListGraph) obj;
        return this.adjacencyList.equals(other.adjacencyList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        for (Integer vertex : adjacencyList.keySet()) {
            visited.put(vertex, false);
        }

        for (Integer vertex : adjacencyList.keySet()) {
            if (!visited.get(vertex)) {
                topologicalSortUtil(vertex, visited, result);
            }
        }

        return result;
    }

    private void topologicalSortUtil(Integer vertex, Map<Integer, Boolean> visited, List<Integer> result) {
        visited.put(vertex, true);

        for (Integer neighbor : adjacencyList.get(vertex)) {
            if (!visited.get(neighbor)) {
                topologicalSortUtil(neighbor, visited, result);
            }
        }

        result.add(0, vertex);
    }
}
