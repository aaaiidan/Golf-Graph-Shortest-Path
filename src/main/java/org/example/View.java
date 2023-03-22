package org.example;

import org.graphstream.graph.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

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

    public void setLevelPanel(int level, int maxAdj) {
        Graph g = new SingleGraph("Tutorial 1");

        g.addNode("A");
        g.addNode("B");
        g.addNode("C");
        g.addEdge("AB", "A", "B");
        g.addEdge("BC", "B", "C");
        g.addEdge("CA", "C", "A");

        SwingViewer viewer = new SwingViewer(g, SwingViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        ViewPanel view = (ViewPanel) viewer.addDefaultView(false);

        frame.add(view);
        view.setVisible(true);
        view.setBounds(5, 5, 1175, 600);
        //graphPanelDisplay.setBackground(Color.blue);
        view.setBorder(border);
        setChoicePanel(1);
        setInfoPanel();

    }

    public void setChoicePanel(int maxAdj) {
        choicePanel.setVisible(true);
        choicePanel.setBounds(0, 610, 600, 190);
        choicePanel.setBackground(Color.red);
/*
        choiceButton = new JButton[maxAdj];

        for (int i = 0; i < maxAdj; i++){
            choicePanel.add(choiceButton[i]);
        }

 */
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

    public void setUpGraphDisplay(){


    }
}