package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    private HashMap<Integer, ArrayList<Node>> adjacencyList;
    private int level;

    public Graph(String fileName, int level){
        this.adjacencyList = new HashMap<>();
        this.level = level;
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

    public ArrayList<Node> getEdges(int src){
        return adjacencyList.get(src);
    }

    public int shortestPath(int start, int end){
        DijkstrasAlgorithm algo = new DijkstrasAlgorithm(getNodes().size(), adjacencyList);
        algo.runAlgorithm(start, end);
        return algo.getDistance(end);
    }

    public int[] getAdjacents(int node) {
        int[] adjacents = new int[adjacencyList.get(node).size()];
        for (int i = 0; i < adjacents.length; i++) {
            adjacents[i] =  adjacencyList.get(node).get(i).getValue();
        }
        return adjacents;
    }

    public int largestAdjacents(){
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

    public HashMap getAdjacecnyList(){
        return adjacencyList;
    }
}
