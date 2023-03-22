package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class DijkstrasAlgorithm {
    private int distance[];
    private ArrayList<Integer> settled;
    private  PriorityQueue<Node> priorityQueue;
    private int noOfN;
    private HashMap<Integer, ArrayList<Node>> adjacencyList;

    public DijkstrasAlgorithm(int noOfN, HashMap<Integer, ArrayList<Node>> adjacencyList){
        this.adjacencyList = adjacencyList;
        this.noOfN = noOfN;
        distance = new int[noOfN];
        settled = new ArrayList<>();

        priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                if(n1.getWeight() < n2.getWeight()){
                    return -1;
                }
                if (n1.getWeight() > n2.getWeight()){
                    return 1;
                }
                return 0;
            }
        });

    }

    public void runAlgorithm (int start, int end){

        for (int i = 0; i < noOfN; i++){
            distance[i] = Integer.MAX_VALUE;
        }

        priorityQueue.add(new Node(start, 0));
        distance[start] = 0;

        while (settled.size() != noOfN){
            if (priorityQueue.isEmpty()){
                return;
            }

            int minDistNode = priorityQueue.remove().getValue();
            if (!settled.contains(minDistNode)){
                settled.add(minDistNode);
                allEdges(minDistNode);
            }
        }

    }

    public void allEdges(int node){
        int edgeDistance = -1;
        int newDistance = -1;

        for (int i = 0; i< adjacencyList.get(node).size(); i++){
            Node newNode = adjacencyList.get(node).get(i);

            if (!settled.contains(newNode.getValue())){
                edgeDistance = newNode.getWeight();
                newDistance = distance[node] + edgeDistance;

                if (newDistance < distance[newNode.getValue()]){
                    distance[newNode.getValue()] = newDistance;
                }
                priorityQueue.add(new Node(newNode.getValue(), distance[newNode.getValue()]));
            }

        }
    }

    public int getDistance(int i) {
        return distance[i];
    }
}
