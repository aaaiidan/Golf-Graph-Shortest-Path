package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Model {
    private Graph graph;
    private int par;
    private int score;
    private int hole;
    private int totalScore;
    private int level;
    private final int noOfHoles;
    private int maxAdjacent;
    private final String fileName;
    private HashMap adjacencyList;
    private int currentNode;
    private int destNode;
    private int currentWeight;
    private int[] currentAdjacent;
    private int[] currentAdjacentWeights;
    private ArrayList<Integer> visited;

    public Model(String fileName){
        this.fileName = fileName;
        this.visited = new ArrayList<>();
        this.noOfHoles = 9;
    }

    public void createGraph(){
        this.graph = new Graph(fileName, level);
        maxAdjacent = graph.largestAdjacent();
        adjacencyList = graph.getAdjacencyList();
        this.score = 0;
        this.hole = 1;
        this.totalScore = 0;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public int getMaxAdjacent() {
        return this.maxAdjacent;
    }
    public HashMap getAdjacencyList(){
        return adjacencyList;
    }
    public void setPar(){
        this.par = graph.shortestPath(currentNode, destNode);
    }
    public int getPar(){
        return this.par;
    }

    public void setWeight(int chosenNode){
        ArrayList<Node> currentAdjacentArrayList = (ArrayList<Node>) adjacencyList.get(currentNode);
        for(int i = 0; i < currentAdjacentArrayList.size(); i++){
            if(currentAdjacentArrayList.get(i).getValue() == chosenNode){
                this.currentWeight = currentAdjacentArrayList.get(i).getWeight();
                i = currentAdjacentArrayList.size();
            }
        }
    }

    public int getWeight(){
        return this.currentWeight;
    }
    public void setRandomNodes() {
        int start = -1;
        int max = 0;
        for (Object key: adjacencyList.keySet()){
            max = Math.max(max, Integer.parseInt(key.toString()));
        }

        while(!adjacencyList.containsKey(start)){
            start = new Random().nextInt(max);
        }

        int end = start;
        while (end == start || !adjacencyList.containsKey(end)){
            end =  new Random().nextInt(max);
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

    public void setCurrentAdjacent(){
        this.currentAdjacent = graph.getAdjacent(currentNode);
    }

    public int[] getCurrentAdjacent(){
        return currentAdjacent;
    }
    public void setCurrentAdjacentWeights(){
        this.currentAdjacentWeights = graph.getAdjacentWeights(currentNode);
    }
    public int[] getCurrentAdjacentWeights(){
        return currentAdjacentWeights;
    }

    public void setCurrentNode(int currentNode) {
        this.currentNode = currentNode;
    }

    public void setScore(){
        this.score += currentWeight;
    }


    public int getScore(){
        return this.score;
    }

    public void setTotalScore(){
        this.totalScore += (this.score - this.par);
        this.score = 0;
    }

    public int getTotalScore(){
        return this.totalScore;
    }

    public void setHole(){
        this.hole++;
    }

    public int getHole(){
        return this.hole;
    }

    public void setVisited(int node){
        this.visited.add(node);
    }

    public ArrayList<Integer> getVisited(){
        return this.visited;
    }

    public void clearVisited(){
        this.visited.clear();
    }

    public int getNoOfHoles(){
        return this.noOfHoles;
    }
}
