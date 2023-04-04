package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import java.io.File;

import java.util.Arrays;

class GraphTest {

    private Graph graph;


    public void createGraph(String filename, int level) {
        graph = new Graph(filename, level);
    }

    @Test
    @DisplayName("Find shortest path")
    public void shortestPathTestLevel1() {
        createGraph("src/main/java/org/example/log2023-01-14 18=13.txt", 1);
        assertEquals(7, graph.shortestPath(3, 46));
    }

    @Test
    @DisplayName("Find shortest path")
    public void shortestPathTestLevel2() {
        createGraph("src/main/java/org/example/log2023-01-14 18=13.txt", 2);
        assertEquals(9, graph.shortestPath(47, 8));
    }

    @Test
    @DisplayName("Find shortest path")
    public void shortestPathTestLevel3() {
        createGraph("src/main/java/org/example/log2023-01-14 18=13.txt", 3);
        assertEquals(8, graph.shortestPath(12, 44));
    }

    @Test
    @DisplayName("Find Adjacents")
    public void getAdjacentsTest() {
        createGraph("src/main/java/org/example/log2023-01-14 18=13.txt", 1);
        assertEquals("[1, 3, 17, 32, 40, 44, 46, 47]", Arrays.toString(graph.getAdjacents(5)));
    }

    /*
    @Test
    @DisplayName("File read succesfully")
    public void readGraphFileTest() {
        int noOfLines = 0;
        while (graph.readGraphFile("src/main/java/org/example/log2023-01-14 18=13.txt", 3)) {

        }
        assertEquals(123, );
    }
   
     */
}