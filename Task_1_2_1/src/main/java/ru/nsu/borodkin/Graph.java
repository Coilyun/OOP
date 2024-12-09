package ru.nsu.borodkin;
import java.util.List;

public interface Graph {
    void addVertex(String vertex);
    void removeVertex(String vertex);
    void addEdge(String from, String to);
    void removeEdge(String from, String to);
    List<String> getNeighbors(String vertex);
    void readFromFile(String filePath);
    boolean equals(Object obj);
    String toString();
    List<String> topologicalSort();
}
