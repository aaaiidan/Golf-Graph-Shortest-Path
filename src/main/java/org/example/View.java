package org.example;

import java.util.Timer;
import java.util.TimerTask;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ArrayList;

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


    public View(){
        frame = new JFrame("Golf Graph");
        startPanel = new JPanel();
        graphPanelDisplay = new JPanel();
        choicePanel = new JPanel();
        infoPanel = new JPanel();
        title = new JLabel("Golf Graph!", SwingConstants.CENTER);
        levelButton = new JButton[3];
        border = BorderFactory.createLineBorder(Color.black);

        for (int i = 0; i < levelButton.length; i++){
            levelButton[i] = new JButton();
            levelButton[i].setText("Level - " + (i + 1));
            levelButton[i].setName(Integer.toString(i+1));
        }

        setFrame();
        hideAllPanels();
        setStartPanel();
    }

    public void setFrame(){
        frame.setLayout(null);
        frame.setSize(1200, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(startPanel);
    }

    public void hideAllPanels(){
        startPanel.setVisible(false);
        graphPanelDisplay.setVisible(false);
        choicePanel.setVisible(false);
        infoPanel.setVisible(false);
    }

    public void setStartPanel(){
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
        Graph graph = new SingleGraph("Graph-" + level);
        graph.setAttribute("ui.stylesheet", "edge {size: 5px; text-size: 25px;} node {size: 25px; text-size: 15px; text-color: white; } graph { fill-mode: image-tiled; fill-image: url('C:/Users/aidan/IdeaProjects/GolfGraph/src/main/resources/graphBackground2.png'); }");

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

    public void addGamePanels(){
        frame.add(graphPanelDisplay);
        frame.add(infoPanel);
        frame.add(choicePanel);

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
}