package org.example;

import java.util.ArrayList;

public interface GraphInterface {
    void addNode(int node);
    void addEdge(int src, int dest, int weight);
    ArrayList<Integer> getNodes();
    int shortestPath(int start, int end);
    int[] getAdjacent(int node);
    int[] getAdjacentWeights(int node);
}
