package org.example;
import org.graphstream.graph.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.swing_viewer.SwingViewer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //Graph graph = new Graph("src/log2023-01-14 18=13.txt", 1);
        //System.out.println(graph.shortestPath(0, 1));
        //graph.getAdjacent(4);

        Model model = new Model("src/main/java/org/example/log2023-01-14 18=13.txt");
        View view = new View();
        new Controller(model, view);

    }
}