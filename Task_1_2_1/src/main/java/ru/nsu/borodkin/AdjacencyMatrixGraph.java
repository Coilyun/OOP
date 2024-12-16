package ru.nsu.borodkin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AdjacencyMatrixGraph implements Graph {
    private final Map<Integer, Integer> vertexIndexMap;
    private int[][] matrix;
    private int size;

    public AdjacencyMatrixGraph(int initialCapacity) {
        this.vertexIndexMap = new HashMap<>();
        this.matrix = new int[initialCapacity][initialCapacity];
        this.size = 0;
    }

    @Override
    public void addVertex(Integer vertex) {
        if (vertexIndexMap.containsKey(vertex)) {
            return;
        }

        if (size == matrix.length) {
            expandMatrix();
        }

        vertexIndexMap.put(vertex, size++);
    }

    @Override
    public void removeVertex(Integer vertex) {
        Integer index = vertexIndexMap.remove(vertex);
        if (index == null) {
            return;
        }

        for (int i = index; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = matrix[i + 1][j];
                matrix[j][i] = matrix[j][i + 1];
            }
        }

        size--;
    }

    @Override
    public void addEdge(Integer from, Integer to) {
        addVertex(from);
        addVertex(to);

        Integer fromIndex = vertexIndexMap.get(from);
        Integer toIndex = vertexIndexMap.get(to);
        if (fromIndex != null && toIndex != null) {
            matrix[fromIndex][toIndex] = 1;
        }
    }

    @Override
    public void removeEdge(Integer from, Integer to) {
        Integer fromIndex = vertexIndexMap.get(from);
        Integer toIndex = vertexIndexMap.get(to);
        if (fromIndex != null && toIndex != null) {
            matrix[fromIndex][toIndex] = 0;
        }
    }

    @Override
    public List<Integer> getNeighbors(Integer vertex) {
        Integer index = vertexIndexMap.get(vertex);
        if (index == null) {
            return new ArrayList<>();
        }

        List<Integer> neighbors = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : vertexIndexMap.entrySet()) {
            if (matrix[index][entry.getValue()] == 1) {
                neighbors.add(entry.getKey());
            }
        }
        return neighbors;
    }

    @Override
    public void readFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] edges = line.split(" ");
                Integer vertex = Integer.parseInt(edges[0]);
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
        if (!(obj instanceof AdjacencyMatrixGraph)) {
            return false;
        }
        AdjacencyMatrixGraph other = (AdjacencyMatrixGraph) obj;
        return this.size == other.size && Arrays.deepEquals(this.matrix, other.matrix);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : vertexIndexMap.entrySet()) {
            sb.append(entry.getKey()).append(": ");
            for (int i = 0; i < size; i++) {
                sb.append(matrix[entry.getValue()][i]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        for (Integer vertex : vertexIndexMap.keySet()) {
            visited.put(vertex, false);
        }

        for (Integer vertex : vertexIndexMap.keySet()) {
            if (!visited.get(vertex)) {
                topologicalSortUtil(vertex, visited, result);
            }
        }

        return result;
    }

    private void topologicalSortUtil(Integer vertex, Map<Integer, Boolean> visited, List<Integer> result) {
        visited.put(vertex, true);
        Integer vertexIndex = vertexIndexMap.get(vertex);

        for (Map.Entry<Integer, Integer> entry : vertexIndexMap.entrySet()) {
            if (matrix[vertexIndex][entry.getValue()] == 1 && !visited.get(entry.getKey())) {
                topologicalSortUtil(entry.getKey(), visited, result);
            }
        }

        result.add(0, vertex);
    }

    private void expandMatrix() {
        int newSize = matrix.length * 2;
        int[][] newMatrix = new int[newSize][newSize];
        for (int i = 0; i < size; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, size);
        }
        matrix = newMatrix;
    }
}
