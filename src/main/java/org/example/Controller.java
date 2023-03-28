package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model  = model;
        this.view = view;
        this.view.addActionListeners(new selectLevel());
    }

    class selectLevel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JButton){
                int buttonID = Integer.parseInt(((JButton) e.getSource()).getName());
                model.setLevel(buttonID);
                model.createGraph();
                view.removeStartPanel();
                view.addGamePanels();
                view.setGraphGamePanel(model.getLevel(), model.getAdjacencyList());
                model.setRandomNodes();
                System.out.println(model.getCurrentNode());
                System.out.println(model.getDestNode());
                model.setCurrentAdjacents();
            }
        }
    }

}
