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
    private Graph graph;
    private SwingViewer viewer;
    private ViewPanel view;
    private JFrame frame;
    private JPanel startPanel;
    private JPanel graphPanelDisplay;
    private JPanel choicePanel;
    private JPanel infoPanel;
    private JPanel winnerPanel;
    private JLabel title;
    private JLabel currNodeLabel;
    private JLabel destNodeLabel;
    private JLabel scoreLabel;
    private JLabel totalScoreLabel;
    private JLabel parLabel;
    private JLabel holeLabel;
    private JLabel winnerLabel;
    private JLabel totalScoreWinnerLabel;
    private JTextArea instructionsText;
    private JButton[] levelButton;
    private JButton[] choiceButton;
    private JButton homeButton;
    private JButton homeButton2;
    private JButton instructionsButton;
    private Border border;
    private Font infoFont;
    private Color infoColour;

    public View(){
        //JFrame
        frame = new JFrame("Golf Graph");

        //All JPanels
        startPanel = new JPanel();
        graphPanelDisplay = new JPanel();
        choicePanel = new JPanel();
        infoPanel = new JPanel();
        winnerPanel = new JPanel();

        //All JLabels
        title = new JLabel("Golf Graph!");
        currNodeLabel = new JLabel();
        destNodeLabel = new JLabel();
        scoreLabel = new JLabel("Score: 0");
        totalScoreLabel = new JLabel("Total Score: 0");
        parLabel = new JLabel();
        holeLabel = new JLabel("HOLE 1");
        winnerLabel = new JLabel("WINNER!!!");
        totalScoreWinnerLabel = new JLabel();

        //TextArea
        instructionsText = new JTextArea(instructions);

        //buttons
        levelButton = new JButton[3];
        homeButton = new JButton("Home");
        instructionsButton = new JButton("Instructions");
        homeButton2 = new JButton("Home");

        //font and borders
        border = BorderFactory.createLineBorder(Color.black);
        infoFont = new Font("Futura", Font.PLAIN , 25);
        infoColour = Color.white;

        //setting up frame
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
        frame.add(winnerPanel);

        //setting up startPanel
        startPanel.setVisible(true);
        startPanel.add(title);
        startPanel.add(instructionsButton);
        startPanel.add(instructionsText);
        startPanel.add(homeButton2);
        startPanel.setLayout(null);
        startPanel.setBackground(new Color(135,206, 235));
        startPanel.setBounds(0, 0, 1200, 800);

        for (int i = 0; i < levelButton.length; i++){
            levelButton[i] = new JButton();
            levelButton[i].setText("Level - " + (i + 1));
            levelButton[i].setName(Integer.toString(i+1));
        }
        int yAxis = 150;
        for (JButton levelButton : levelButton) {
            startPanel.add(levelButton);
            levelButton.setBounds(475, yAxis, 250, 100);
            levelButton.setFont(new Font("Futura", Font.BOLD , 50));
            yAxis += 150;
        }
        homeButton2.setBounds(350, yAxis, 500, 100);
        homeButton2.setFont(new Font("Futura", Font.BOLD , 50));
        homeButton2.setVisible(false);

        instructionsButton.setBounds(350, yAxis, 500, 100);
        instructionsButton.setFont(new Font("Futura", Font.BOLD , 50));

        instructionsText.setVisible(false);
        instructionsText.setLineWrap(true);
        instructionsText.setWrapStyleWord(true);
        instructionsText.setEditable(false);
        instructionsText.setBounds(100, 125, 1000, 425);
        instructionsText.setFont(new Font("Futura", Font.BOLD , 30));
        instructionsText.setBackground(new Color(135,206, 235));

        title.setBounds(460, 0, 500, 100);
        title.setFont(new Font("Futura", Font.BOLD , 50));

        //setting up choicePanel
        choicePanel.setLayout(new GridLayout(2, 2, 5, 5));
        choicePanel.setVisible(false);
        choicePanel.setBounds(0, 610, 600, 160);
        choicePanel.setBackground(new Color(32,90, 77));

        //setting up infoPanel
        infoPanel.setVisible(false);
        infoPanel.setBounds(600, 610, 600, 190);
        infoPanel.setBackground(new Color(32,90, 77));
        infoPanel.setLayout(null);
        infoPanel.add(currNodeLabel);
        infoPanel.add(destNodeLabel);
        infoPanel.add(scoreLabel);
        infoPanel.add(totalScoreLabel);
        infoPanel.add(parLabel);
        infoPanel.add(holeLabel);

        currNodeLabel.setBounds(25,30,250,40);
        destNodeLabel.setBounds(25,60,250,40);
        parLabel.setBounds(475,30,250,40);
        scoreLabel.setBounds(450,60,250,40);
        totalScoreLabel.setBounds(390,90,250,40);
        holeLabel.setBounds(250, 0, 250, 30);

        currNodeLabel.setFont(infoFont);
        destNodeLabel.setFont(infoFont);
        scoreLabel.setFont(infoFont);
        totalScoreLabel.setFont(infoFont);
        parLabel.setFont(infoFont);
        holeLabel.setFont(infoFont);

        currNodeLabel.setForeground(infoColour);
        destNodeLabel.setForeground(infoColour);
        scoreLabel.setForeground(infoColour);
        totalScoreLabel.setForeground(infoColour);
        parLabel.setForeground(infoColour);
        holeLabel.setForeground(infoColour);

        //setting up winnerPanel
        winnerPanel.setLayout(null);
        winnerPanel.setVisible(false);
        winnerPanel.setBounds(0, 0, 1200, 800);
        winnerPanel.setBackground(new Color(135,206, 235));
        winnerPanel.add(homeButton);
        winnerPanel.add(winnerLabel);
        winnerPanel.add(totalScoreWinnerLabel);

        winnerLabel.setFont(new Font("Futura", Font.BOLD , 100));
        winnerLabel.setForeground(new Color(241, 134, 129));
        winnerLabel.setBounds(360, 100, 500, 100);

        totalScoreWinnerLabel.setFont(new Font("Futura", Font.BOLD , 50));
        totalScoreWinnerLabel.setForeground(new Color(241, 134, 129));
        totalScoreWinnerLabel.setBounds(430, 250, 500, 100);

        homeButton.setBounds(350, 500, 500, 100);
        homeButton.setFont(new Font("Futura", Font.BOLD , 50));
    }

    public void createVisualGraph(HashMap adjacencyList) {
        ArrayList<Node> node;

        //setting up visual graph
        graph = new SingleGraph("Graph");
        graph.setAttribute("ui.stylesheet", stylesheet);
        viewer = new SwingViewer(graph, SwingViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        view = (ViewPanel) viewer.addDefaultView(false);
        view.setVisible(true);
        view.setBounds(5, 5, 1175, 600);
        view.setBorder(border);
        view.setLayout(null);

        frame.add(view);

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
    }
    public void choicePanelButtonSetup(int maxAdj,  int[] currentAdjacent, int[] currentAdjacentWeights) {
        choiceButton = new JButton[maxAdj];

        for (int i = 0; i < maxAdj; i++){
            choiceButton[i] = new JButton();
            choiceButton[i].setMargin(new Insets(50, 50, 50, 50));
            choiceButton[i].setBorder(null);
            choicePanel.add(choiceButton[i]);
        }
        for (int i = 0; i < maxAdj; i++){
            if(i < currentAdjacent.length){
                choiceButton[i].setText(currentAdjacent[i] + " - (" + currentAdjacentWeights[i] + ")");
                choiceButton[i].setName(String.valueOf(currentAdjacent[i]));
            } else {
                choiceButton[i].setText("X");
                choiceButton[i].setEnabled(false);
            }
        }
    }

    public void openLevelPanel(){
        startPanel.setVisible(false);
        //view.setVisible(true);
        choicePanel.setVisible(true);
        infoPanel.setVisible(true);
    }

    public void UpdateButtons(int maxAdj, int[] currentAdjacent, int[] currentAdjacentWeights) {
        for (int i = 0; i < maxAdj; i++){
            if(i < currentAdjacent.length){
                choiceButton[i].setText(currentAdjacent[i] + " - (" + currentAdjacentWeights[i] + ")");
                choiceButton[i].setName(String.valueOf(currentAdjacent[i]));
                choiceButton[i].setEnabled(true);
            } else {
                choiceButton[i].setText("X");
                choiceButton[i].setEnabled(false);
            }
        }
    }

    public void updateNodes(int curNode, int dest, int[] adjacentNodes, ArrayList<Integer> visited) {

        //reset all nodes that are not adjacent to red
        for(org.graphstream.graph.Node node : graph) {
            node.removeAttribute("ui.class");
        }

        graph.edges().forEach(edge -> {
            if (!visited.contains(Integer.parseInt(edge.getNode0().toString())) ||  !visited.contains(Integer.parseInt(edge.getNode1().toString()))){
                edge.removeAttribute("ui.class");
            }
        });


    //change all adjacent nodes to blue
        for(int i = 0; i < adjacentNodes.length; i++) {
            graph.getNode(Integer.toString(adjacentNodes[i])).setAttribute("ui.class", "available");
            if (graph.getEdge(curNode + "-" + adjacentNodes[i]) != null) {
                graph.getEdge(curNode + "-" + adjacentNodes[i]).setAttribute("ui.class", "available");
            } else {
                graph.getEdge(adjacentNodes[i] + "-" + curNode).setAttribute("ui.class", "available");
            }
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

        graph.getNode(Integer.toString(dest)).setAttribute("ui.class", "goal");

    }

    public void updateLabels(int currNode, int destNode, int score, int totalScore, int par, int hole){
        currNodeLabel.setText("Golf ball Node: " + currNode);
        destNodeLabel.setText("Hole Node: " + destNode);
        scoreLabel.setText("Score: " + score);
        totalScoreLabel.setText("Total Score: " + totalScore);
        parLabel.setText("Par: " + par);
        holeLabel.setText("Hole: " + hole);

    }

    public void leaveLevelPanel(){
        view.setVisible(false);
        choicePanel.setVisible(false);
        infoPanel.setVisible(false);
    }
    public void displayWinner(int totalScore){
        winnerPanel.setVisible(true);
        homeButton.setVisible(true);
        totalScoreWinnerLabel.setText("Total Score: " + totalScore);
    }

    public void returnHome(){
        winnerPanel.setVisible(false);
        homeButton.setVisible(false);
        homeButton2.setVisible(false);
        instructionsText.setVisible(false);
        instructionsButton.setVisible(true);
        startPanel.setVisible(true);

        if (choiceButton != null){
            for (JButton button : choiceButton) {
                choicePanel.remove(button);
            }
        }

        for(JButton level : levelButton){
            level.setVisible(true);
        }
    }

    public void displayInstructions(){
        for(JButton level : levelButton){
            level.setVisible(false);
        }
        instructionsButton.setVisible(false);
        instructionsText.setVisible(true);
        homeButton2.setVisible(true);
    }

    public void addLevelButtonHomeButtonAndInstructionButtonActionListeners(ActionListener selectLevel, ActionListener home, ActionListener instructions){
        for(JButton level: levelButton) {
            level.addActionListener(selectLevel);
        }
        homeButton.addActionListener(home);
        homeButton2.addActionListener(home);
        instructionsButton.addActionListener(instructions);
    }

    public void addChoiceButtonActionListeners(ActionListener selectNode){
        for(JButton button: choiceButton){
            button.addActionListener(selectNode);
        }
    }

    private String instructions = "The player starts at the white golf ball and by selecting available nodes with the buttons provided, "
            + "the player must reach the end node (hole/flag) with the shortest possible distance.\n \n"
            + "The lower the the players score is, the better. A perfect game would result in a total score of 0. Unavailable nodes will "
            + "appear as red whereas available nodes will appear blue."
            + "Score better by selecting the path with smallest total weight. \n \n"
            + "There are 3 levels, with each level being more complex than the previous.";
    private String stylesheet = ""
            + "edge { size: 4px; text-size: 25px; text-color: #3f301d; text-style: bold; fill-color: #a8ba9a; }"
            + "edge.visited { fill-color: #FC0; fill-mode: plain; shadow-mode: plain; shadow-width: 3px; shadow-color: #4c3d00; shadow-offset: 0px; }"
            + "edge.available { fill-color: blue; }"
            + "node { size: 25px; text-color: white; text-size: 20px; text-alignment: center; fill-color: red; fill-mode: plain; stroke-mode: plain; stroke-color: black; }" //Unavailable Node
            + "node.current { text-color: black; fill-color: white;} " // shadow-mode: plain; shadow-width: 3px; shadow-color: #FC0; shadow-offset: 0px;
            + "node.available { fill-color: blue;}"
            + "node.visited { fill-color: #FC0; }"
            + "node.goal { size: 40px; text-color: black; text-style: bold; text-size: 33px; fill-mode: image-scaled; fill-image: url('src/main/resources/flag.png');}"
            + "graph { fill-mode: image-tiled; fill-image: url('src/main/resources/background3.png'); }";

}
