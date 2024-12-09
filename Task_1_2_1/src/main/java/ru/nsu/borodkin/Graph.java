package ru.nsu.borodkin;
import java.util.List;

public interface Graph {
    void addVertex(String vertex);                // Добавить вершину
    void removeVertex(String vertex);            // Удалить вершину
    void addEdge(String from, String to);        // Добавить ребро
    void removeEdge(String from, String to);     // Удалить ребро
    List<String> getNeighbors(String vertex);    // Получить список соседей
    void readFromFile(String filePath);          // Загрузить граф из файла
    boolean equals(Object obj);                  // Проверить равенство графов
    String toString();                           // Строковое представление графа
    List<String> topologicalSort();              // Алгоритм топологической сортировки
}
