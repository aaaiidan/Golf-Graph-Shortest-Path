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
    private int maxAdjacents;
    private String fileName;
    private HashMap adjacencyList;
    private int currentNode;
    private int destNode;
    private int currentWeight;
    private int[] currentAdjacent;
    private ArrayList<Integer> visited;

    public Model(String fileName){
        this.fileName = fileName;
        this.score = 0;
        this.hole = 1;
        this.visited = new ArrayList<>();
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
        this.currentAdjacent = graph.getAdjacents(currentNode);
    }

    public int[] getCurrentAdjacent(){
        return currentAdjacent;
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

}
