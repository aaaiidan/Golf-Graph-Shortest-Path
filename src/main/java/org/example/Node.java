package org.example;

public class Node  {
    private int value;
    private int weight;

    public Node(int value, int weight){
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }
}
