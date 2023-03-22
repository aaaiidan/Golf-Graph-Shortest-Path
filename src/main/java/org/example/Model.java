package org.example;

public class Model {
    private Graph graph;
    private int par;
    private int score;
    private int hole;
    private int totalScore;
    private int level;
    private int maxAdjacents;
    private String fileName;

    public Model(String fileName){
        this.fileName = fileName;
    }

    public void createGraph(){
        this.graph = new Graph(fileName, level);
        maxAdjacents = graph.largestAdjacents();
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
}
