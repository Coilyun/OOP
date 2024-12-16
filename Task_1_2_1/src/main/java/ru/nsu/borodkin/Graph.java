package ru.nsu.borodkin;
import java.util.List;

public interface Graph {
    void addVertex(Integer vertex);

    void removeVertex(Integer vertex);
    
    void addEdge(Integer from, Integer to);
    
    void removeEdge(Integer from, Integer to);
    
    List<Integer> getNeighbors(Integer vertex);
    
    void readFromFile(String filePath);
    
    boolean equals(Object obj);
    
    String toString();
    
    List<Integer> topologicalSort();
}
