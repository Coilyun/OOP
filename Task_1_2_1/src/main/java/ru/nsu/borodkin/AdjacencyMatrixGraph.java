package ru.nsu.borodkin;
import java.util.*;

public class AdjacencyMatrixGraph implements Graph {
    private final Map<String, Integer> vertexIndexMap = new HashMap<>();
    private int[][] matrix;
    private int size;

    public AdjacencyMatrixGraph(int initialCapacity) {
        matrix = new int[initialCapacity][initialCapacity];
        size = 0;
    }

    @Override
    public void addVertex(String vertex) {
        if (vertexIndexMap.containsKey(vertex)) return;

        if (size == matrix.length) {
            expandMatrix();
        }
        vertexIndexMap.put(vertex, size++);
    }

    @Override
    public void removeVertex(String vertex) {
        Integer index = vertexIndexMap.remove(vertex);
        if (index == null) return;

        for (int i = index; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = matrix[i + 1][j];
                matrix[j][i] = matrix[j][i + 1];
            }
        }
        size--;
    }

    @Override
    public void addEdge(String from, String to) {
        Integer fromIndex = vertexIndexMap.get(from);
        Integer toIndex = vertexIndexMap.get(to);
        if (fromIndex != null && toIndex != null) {
            matrix[fromIndex][toIndex] = 1;
        }
    }

    @Override
    public void removeEdge(String from, String to) {
        Integer fromIndex = vertexIndexMap.get(from);
        Integer toIndex = vertexIndexMap.get(to);
        if (fromIndex != null && toIndex != null) {
            matrix[fromIndex][toIndex] = 0;
        }
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        Integer index = vertexIndexMap.get(vertex);
        if (index == null) return Collections.emptyList();

        List<String> neighbors = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : vertexIndexMap.entrySet()) {
            if (matrix[index][entry.getValue()] == 1) {
                neighbors.add(entry.getKey());
            }
        }
        return neighbors;
    }

    @Override
    public void readFromFile(String filePath) {
        // Чтение графа из файла (реализация зависит от формата файла)
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdjacencyMatrixGraph)) return false;
        AdjacencyMatrixGraph other = (AdjacencyMatrixGraph) obj;

        return size == other.size && Arrays.deepEquals(matrix, other.matrix);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(i).append(": ");
            for (int j = 0; j < size; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<String> topologicalSort() {
        return Collections.emptyList(); // Заглушка
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
