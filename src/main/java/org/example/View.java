package org.example;

import org.graphstream.graph.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.graphicGraph.GraphicSprite;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

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

    public void setGraphGamePanel(int level, HashMap adjacencyList) {
        ArrayList<Node> node;
        Graph graph = new SingleGraph("Graph-" + level);
        graph.setAttribute("ui.stylesheet", stylesheet);

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
        view.setBackground(Color.red);
        view.setBorder(border);
        setChoicePanel(8);
        setInfoPanel();

    }
    public void setChoicePanel(int maxAdj) {
        choicePanel.setLayout(new GridLayout(2, 2, 5, 5));
        choicePanel.setVisible(true);
        choicePanel.setBounds(0, 610, 600, 160);
        choicePanel.setBackground(Color.red);

        choiceButton = new JButton[maxAdj];



        for (int i = 0; i < maxAdj; i++){
            System.out.println(maxAdj);
            choiceButton[i] = new JButton();
            choiceButton[i].setMargin(new Insets(50, 50, 50, 50));
            choiceButton[i].setText("Level - " + (i + 1));
            choiceButton[i].setName(Integer.toString(i+1));
            choicePanel.add(choiceButton[i]);
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

    public void addActionListeners(ActionListener selectLevel){
        for(JButton level: levelButton){
            level.addActionListener(selectLevel);
        }
    }


    private String stylesheet = ""
            //+ "edge {size: 3px; text-size: 25px; text-color: black; edge-color} "
            + "edge {text-size: px; fill-color: brown; shadow-mode: plain; shadow-width: 3px; shadow-color: #FC0; shadow-offset: 0px;}"
            + "node { size: 24px; text-size: 18px; fill-color: #20BF55, #01BAEF; stroke-mode: plain; stroke-color: #999; fill-mode: gradient-horizontal; shadow-mode: plain; shadow-width: 0px; shadow-color:#999; shadow-offset: 3px, -3px;}"
            //+ "node {size: 20px; fill-color: #FBD72B, #F9484A; fill-mode: gradient-horizontal; }}"
            //+ "node.current { size: 20px; fill-color: white, #8B939A; fill-mode: gradient-horizontal; }"
            //+ "node.available { size: 25px; fill-color: #20BF55, #01BAEF; stroke-mode: plain; stroke-color: #999; fill-mode: gradient-horizontal; shadow-mode: plain; shadow-width: 0px; shadow-color:#999; shadow-offset: 3px, -3px;}"
            + "graph { fill-mode: image-tiled; fill-image: url('src/main/resources/background3.png'); }";
}