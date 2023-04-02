package org.example;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class View {
    private JFrame frame;
    private JFrame frame2;
    private JPanel startPanel;
    private JPanel graphPanelDisplay;
    private JPanel choicePanel;
    private JPanel infoPanel;
    private JLabel title;
    private JButton[] levelButton;
    private JButton[] choiceButton;
    private Border border;
    private Graph graph;

    public View(){

        frame = new JFrame("Golf Graph");
        startPanel = new JPanel();
        graphPanelDisplay = new JPanel();
        choicePanel = new JPanel();
        infoPanel = new JPanel();
        title = new JLabel("Golf Graph!", SwingConstants.CENTER);
        levelButton = new JButton[3];
        border = BorderFactory.createLineBorder(Color.black);
        graph = new SingleGraph("Graph");
        graph.setAttribute("ui.stylesheet", stylesheet);

        frame.setLayout(null);
        frame.setSize(1200, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(startPanel);
        frame.add(graphPanelDisplay);
        frame.add(infoPanel);
        frame.add(choicePanel);

        for (int i = 0; i < levelButton.length; i++){
            levelButton[i] = new JButton();
            levelButton[i].setText("Level - " + (i + 1));
            levelButton[i].setName(Integer.toString(i+1));
        }

        startPanel.setVisible(true);
        startPanel.add(title);
        startPanel.setLayout(null);
        startPanel.setBounds(0, 0, 1200, 800);

        int yAxis = 250;
        for (int i = 0; i < levelButton.length; i++){
            startPanel.add(levelButton[i]);
            levelButton[i].setBounds(475, yAxis, 250, 100);
            yAxis += 150;
        }

        title.setBounds(550, 0, 100, 100);
    }

    public void setGraphGamePanel(int level, HashMap adjacencyList, int[] currentAdjacents, int maxAdj) {
        ArrayList<Node> node;


        for (Object key : adjacencyList.keySet()){
            graph.addNode(key.toString());
            graph.getNode(key.toString()).setAttribute("ui.label", key);
        }

        for(Object key : adjacencyList.keySet()){
            node = (ArrayList<Node>) adjacencyList.get(key);
            for (int j = 0; j < node.size(); j++){
                int dest = node.get(j).getValue();
                int weight = node.get(j).getWeight();
                String strSrc = key.toString();

                if(graph.getEdge(node.get(j).getValue() + "-" + strSrc) == null){
                    graph.addEdge(key + "-" + dest, strSrc, Integer.toString(dest)).setAttribute("ui.label", Integer.toString(weight));
                }
            }
        }

        SwingViewer viewer = new SwingViewer(graph, SwingViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        ViewPanel view = (ViewPanel) viewer.addDefaultView(false);


        frame.add(view);
        view.setVisible(true);
        view.setBounds(5, 5, 1175, 600);
        view.setBorder(border);
        setChoicePanel(maxAdj, currentAdjacents);
        setInfoPanel();

    }
    public void setChoicePanel(int maxAdj,  int[] currentAdjacent) {
        choicePanel.setLayout(new GridLayout(2, 2, 5, 5));
        choicePanel.setVisible(true);
        choicePanel.setBounds(0, 610, 600, 160);
        choicePanel.setBackground(Color.red);

        choiceButton = new JButton[maxAdj];

        for (int i = 0; i < maxAdj; i++){
            choiceButton[i] = new JButton();
            choiceButton[i].setMargin(new Insets(50, 50, 50, 50));
            choiceButton[i].setBorder(null);
            choicePanel.add(choiceButton[i]);
        }

        for (int i = 0; i < maxAdj; i++){
            if(i < currentAdjacent.length){
                choiceButton[i].setText(String.valueOf(currentAdjacent[i]));
                choiceButton[i].setName(String.valueOf(currentAdjacent[i]));
            } else {
                choiceButton[i].setText("X");
                choiceButton[i].setEnabled(false);
            }
        }

    }

    public void setInfoPanel(){
        infoPanel.setVisible(true);
        infoPanel.setBounds(600, 610, 600, 190);
        infoPanel.setBackground(Color.green);

    }

    public void removeStartPanel(){
        startPanel.setVisible(false);
        frame.remove(startPanel);
    }

    public void addLevelButtonActionListeners(ActionListener selectLevel){
        for(JButton level: levelButton) {
            level.addActionListener(selectLevel);
        }
    }

    public void addChoiceButtonActionListeners(ActionListener selectNode){
        for(JButton button: choiceButton){
            button.addActionListener(selectNode);
        }
    }

    public void UpdateButtons(int maxAdj, int[] currentAdjacent) {
        for (int i = 0; i < maxAdj; i++){
            if(i < currentAdjacent.length){
                choiceButton[i].setText(String.valueOf(currentAdjacent[i]));
                choiceButton[i].setName(String.valueOf(currentAdjacent[i]));
                choiceButton[i].setEnabled(true);
            } else {
                choiceButton[i].setText("X");
                choiceButton[i].setEnabled(false);
            }
        }
    }

    public void updateNodes(int curNode, int[] adjacentNodes, ArrayList<Integer> visited){

        //reset all nodes that are not adjacent to red
        for(org.graphstream.graph.Node node : graph) {
            for (int adjacent : adjacentNodes){
                if (Integer.parseInt(node.getId()) != adjacent){
                    node.removeAttribute("ui.class");
                }
            }
        }

        //change all adjacent nodes to blue
        for(int node : adjacentNodes){
            graph.getNode(Integer.toString(node)).setAttribute("ui.class", "available");
        }

        //change all visited nodes to gold
        for(int i = 0; i < visited.size(); i++){
            graph.getNode(Integer.toString(visited.get(i))).removeAttribute("ui.class");
            graph.getNode(Integer.toString(visited.get(i))).setAttribute("ui.class", "visited");
            if (visited.size() > 1 && i+1 < visited.size()){
                if(graph.getEdge(visited.get(i) + "-" + visited.get(i+1)) != null){
                    graph.getEdge(visited.get(i) + "-" + visited.get(i+1)).setAttribute("ui.class", "visited");
                } else{
                    graph.getEdge(visited.get(i+1) + "-" + visited.get(i)).setAttribute("ui.class", "visited");
                }
            }
        }

        //change current node to white
        graph.getNode(Integer.toString(curNode)).removeAttribute("ui.class");
        graph.getNode(Integer.toString(curNode)).setAttribute("ui.class", "current");

    }

    public void resetNodes(){
        for(org.graphstream.graph.Node node : graph) {
            node.removeAttribute("ui.class");
        }
        graph.edges().forEach(edge -> {
            edge.removeAttribute("ui.class");
        });
    }

    private String stylesheet = ""
            //+ "edge {size: 3px; text-size: 25px; text-color: black; edge-color} "
            + "edge { size: 4px; text-size: 25px; text-color: #3f301d; text-style: bold; fill-color: #a8ba9a; }"
            + "edge.visited { fill-color: #FC0; fill-mode: plain; shadow-mode: plain; shadow-width: 3px; shadow-color: #4c3d00; shadow-offset: 0px; }"
            + "node { size: 25px; text-color: white; text-size: 20px; text-alignment: center; fill-color: red; fill-mode: plain; stroke-mode: plain; stroke-color: black; }" //Unavailable Node
            + "node.current { text-color: black; fill-color: white;} " // shadow-mode: plain; shadow-width: 3px; shadow-color: #FC0; shadow-offset: 0px;
            + "node.available { fill-color: blue;}"
            + "node.visited { fill-color: #FC0; }"
            + "graph { fill-mode: image-tiled; fill-image: url('src/main/resources/background3.png'); }";

}
