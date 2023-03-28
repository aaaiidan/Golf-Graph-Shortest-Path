package org.example;

import java.util.HashMap;
import java.util.Random;

public class Model {
    private Graph graph;
    private int par;
    private int score;
    private int hole;
    private int totalScore;
    private int level;
    private int maxAdjacents;
    private String fileName;
    private HashMap adjacencyList;
    private int currentNode;
    private int destNode;

    public Model(String fileName){
        this.fileName = fileName;
    }

    public void createGraph(){
        this.graph = new Graph(fileName, level);
        maxAdjacents = graph.largestAdjacents();
        adjacencyList = graph.getAdjacecnyList();
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public int getMaxAdjacents() {
        return this.maxAdjacents;
    }
    public HashMap getAdjacencyList(){
        return adjacencyList;
    }

    public void setRandomNodes() {
        int start = new Random().nextInt(adjacencyList.size());
        int end = start;
        while (end == start){
            end =  new Random().nextInt(adjacencyList.size());
        }
        System.out.println(start + " - " + end);
        currentNode = start;
        destNode = end;
    }

    public int getCurrentNode(){
        return currentNode;
    }
    public int getDestNode(){
        return destNode;
    }

    public int[] setCurrentAdjacents(){
        int[] list = graph.getAdjacents(currentNode);
        System.out.println(list);
        return graph.getAdjacents(currentNode);
    }
}
