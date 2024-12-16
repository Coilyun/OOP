package ru.nsu.borodkin;

import java.io.*;
import java.util.*;

public class IncidenceMatrixGraph implements Graph {
    private int[][] incidenceMatrix;
    private int vertexCount;
    private int edgeCount;

    public IncidenceMatrixGraph(int initialVertexCount) {
        this.vertexCount = initialVertexCount;
        this.edgeCount = 0;
        this.incidenceMatrix = new int[vertexCount][0];
    }

    @Override
    public void addVertex(Integer vertex) {
        if (vertex >= vertexCount) {
            int newSize = vertex + 1;
            int[][] newMatrix = new int[newSize][edgeCount];
            for (int i = 0; i < vertexCount; i++) {
                System.arraycopy(incidenceMatrix[i], 0, newMatrix[i], 0, edgeCount);
            }
            incidenceMatrix = newMatrix;
            vertexCount = newSize;
        }
    }

    @Override
    public void removeVertex(Integer vertex) {
        if (vertex >= vertexCount) {
            return;
        }

        // Удаляем все рёбра, связанные с данной вершиной
        for (int i = 0; i < edgeCount; i++) {
            if (incidenceMatrix[vertex][i] != 0) {
                for (int j = 0; j < vertexCount; j++) {
                    incidenceMatrix[j][i] = 0;
                }
            }
        }

        // Убираем строку с данной вершиной
        int[][] newMatrix = new int[vertexCount - 1][edgeCount];
        for (int i = 0, newRow = 0; i < vertexCount; i++) {
            if (i != vertex) {
                newMatrix[newRow++] = incidenceMatrix[i];
            }
        }
        incidenceMatrix = newMatrix;
        vertexCount--;
    }

    @Override
    public void addEdge(Integer from, Integer to) {
        addVertex(from);
        addVertex(to);

        // Увеличиваем количество рёбер
        int[][] newMatrix = new int[vertexCount][edgeCount + 1];
        for (int i = 0; i < vertexCount; i++) {
            System.arraycopy(incidenceMatrix[i], 0, newMatrix[i], 0, edgeCount);
        }
        incidenceMatrix = newMatrix;
        edgeCount++;

        // Добавляем ребро между вершинами
        int edgeIndex = edgeCount - 1;
        incidenceMatrix[from][edgeIndex] = 1;
        incidenceMatrix[to][edgeIndex] = -1;
    }

    @Override
    public void removeEdge(Integer from, Integer to) {
        for (int i = 0; i < edgeCount; i++) {
            if ((incidenceMatrix[from][i] == 1 && incidenceMatrix[to][i] == -1)
                    || (incidenceMatrix[from][i] == -1 && incidenceMatrix[to][i] == 1)) {
                incidenceMatrix[from][i] = 0;
                incidenceMatrix[to][i] = 0;
                break;
            }
        }
    }

    @Override
    public List<Integer> getNeighbors(Integer vertex) {
        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < edgeCount; i++) {
            if (incidenceMatrix[vertex][i] != 0) {
                for (int j = 0; j < vertexCount; j++) {
                    if (j != vertex && incidenceMatrix[j][i] != 0) {
                        neighbors.add(j);
                    }
                }
            }
        }
        return neighbors;
    }

    @Override
    public void readFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] verticesInLine = line.split(" ");
                Integer from = Integer.parseInt(verticesInLine[0]);
                Integer to = Integer.parseInt(verticesInLine[1]);
                addEdge(from, to);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IncidenceMatrixGraph)) {
            return false;
        }
        IncidenceMatrixGraph other = (IncidenceMatrixGraph) obj;
        return Arrays.deepEquals(this.incidenceMatrix, other.incidenceMatrix);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vertexCount; i++) {
            sb.append(i).append(": ");
            for (int j = 0; j < edgeCount; j++) {
                sb.append(incidenceMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, result);
            }
        }
        return result;
    }

    private void topologicalSortUtil(int vertex, boolean[] visited, List<Integer> result) {
        visited[vertex] = true;

        // Ищем все рёбра, исходящие из вершины
        for (int i = 0; i < edgeCount; i++) {
            if (incidenceMatrix[vertex][i] != 0) {
                for (int j = 0; j < vertexCount; j++) {
                    if (incidenceMatrix[j][i] != 0 && !visited[j]) {
                        topologicalSortUtil(j, visited, result);
                    }
                }
            }
        }

        result.add(0, vertex);
    }
}
