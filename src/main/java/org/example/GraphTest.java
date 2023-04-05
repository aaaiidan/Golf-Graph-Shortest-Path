package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

import java.io.*;

import java.util.Arrays;


class GraphTest {

    private Graph graph;


    public void createGraph(String filename, int level) {
        graph = new Graph(filename, level);
    }

    @BeforeEach
    void setUp() {
        graph = new Graph("src/main/java/org/example/log2023-01-14 18=13.txt", 3);
    }

    @AfterEach
    void tearDown() {
        graph = null;
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
        assertEquals(8, graph.shortestPath(12, 44));
    }

    @Test
    @DisplayName("Find Adjacents")
    public void getAdjacentsTest() {
        createGraph("src/main/java/org/example/log2023-01-14 18=13.txt", 1);
        assertEquals("[1, 3, 17, 32, 40, 44, 46, 47]", Arrays.toString(graph.getAdjacents(5)));
    }

    @Test
    void getAdjacencyListTestLevel1() {
        createGraph("src/main/java/org/example/log2023-01-14 18=13.txt", 1);
        assertEquals(34, graph.getAdjacecnyList().size());
    }

    @Test
    void getAdjacencyListTestLevel2() {
        createGraph("src/main/java/org/example/log2023-01-14 18=13.txt", 2);
        assertEquals(48, graph.getAdjacecnyList().size());
    }

    @Test
    void getAdjacencyListTestLevel3() {
        assertEquals(50, graph.getAdjacecnyList().size());
    }

    @Test
    void testReadGraphFile() throws IOException {
        // Create a temporary file with some test data
        File tempFile = File.createTempFile("graph", ".txt");

        // Call the method under test
        graph.readGraphFile(tempFile.getAbsolutePath(), 3);

        // Verify the results
        assertEquals(50, graph.getAdjacecnyList().size());
        assertEquals("[1, 11, 25, 28, 35, 36, 47]", Arrays.toString(graph.getAdjacents(0)));
        assertEquals("[0, 5, 8, 11, 18]", Arrays.toString(graph.getAdjacents(1)));
        assertEquals("[15, 19, 47]", Arrays.toString(graph.getAdjacents(2)));
    }

    @Test
    void largestAdjacentTestLevel1() {
        createGraph("src/main/java/org/example/log2023-01-14 18=13.txt", 1);
        assertEquals(8, graph.largestAdjacents());
    }

    @Test
    void largestAdjacentTestLevel2() {
        createGraph("src/main/java/org/example/log2023-01-14 18=13.txt", 2);
        assertEquals(11, graph.largestAdjacents());
    }

    @Test
    void largestAdjacentTestLevel3() {
        assertEquals(11, graph.largestAdjacents());
    }
}