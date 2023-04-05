package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph implements GraphInterface {
    private final HashMap<Integer, ArrayList<Node>> adjacencyList;

    public Graph(String fileName, int level){
        this.adjacencyList = new HashMap<>();
        readGraphFile(fileName, level);
    }

    private void readGraphFile(String fileName, int level) {
        try {
            File graphFile = new File(fileName);
            Scanner scanner = new Scanner(graphFile);
            Scanner scanner2 = new Scanner(graphFile);

            int noOfLines = 0;
            int count = 0;

            while (scanner.hasNextLine()) {
                noOfLines++;
                scanner.nextLine();
            }
            scanner.close();
            System.out.println(noOfLines);

            while(scanner2.hasNext() && count <= (noOfLines / 3) * level){
                count++;
                int src = scanner2.nextInt();
                int dest = scanner2.nextInt();
                int weight = Integer.parseInt((scanner2.next() + scanner2.next()).replaceAll("[^0-9]", ""));

                addNode(src);
                addNode(dest);
                addEdge(src, dest, weight);
            }
            System.out.println(adjacencyList.get(0));
            scanner2.close();
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void addNode(int node){
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(int src, int dest, int weight){
        adjacencyList.get(src).add(new Node(dest, weight));
        adjacencyList.get(dest).add(new Node(src, weight));
    }

    public ArrayList<Integer> getNodes(){
        return new ArrayList<>(adjacencyList.keySet());
    }

    public int shortestPath(int start, int end){
        int max = 0;
        for (int i = 0; i < adjacencyList.keySet().size(); i++){
            max = Math.max(max, getNodes().get(i));
        }
        DijkstrasAlgorithm algo = new DijkstrasAlgorithm(max + 1, adjacencyList);
        algo.runAlgorithm(start);
        return algo.getDistance(end);
    }

    public int[] getAdjacent(int node) {
        int[] adjacent = new int[adjacencyList.get(node).size()];
        for (int i = 0; i < adjacent.length; i++) {
            adjacent[i] =  adjacencyList.get(node).get(i).getValue();
        }
        return adjacent;
    }

    public int[] getAdjacentWeights(int node){
        int[] adjacentWeights = new int[adjacencyList.get(node).size()];
        for (int i = 0; i < adjacentWeights.length; i++) {
            adjacentWeights[i] =  adjacencyList.get(node).get(i).getWeight();
        }
        return adjacentWeights;
    }

    public int largestAdjacent(){
        int max = 0;
        for (int i = 0; i < adjacencyList.size(); i++){
            if(adjacencyList.containsKey(i)){
                if(max < adjacencyList.get(i).size()){
                    max = adjacencyList.get(i).size();
                }
            }
        }
        return max;
    }

    public HashMap<Integer, ArrayList<Node>> getAdjacencyList(){
        return adjacencyList;
    }
}