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
        this.view.addLevelButtonActionListeners(new selectLevel());
    }

    class selectLevel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JButton){
                int buttonID = Integer.parseInt(((JButton) e.getSource()).getName());
                model.setLevel(buttonID);
                model.createGraph();
                model.setRandomNodes();
                model.setCurrentAdjacent();
                model.setPar();
                model.setVisited(model.getCurrentNode());

                view.openLevelPanel();
                view.createVisualGraph(model.getAdjacencyList());
                view.choicePanelButtonSetup(model.getMaxAdjacents(), model.getCurrentAdjacent());
                view.updateNodes(model.getCurrentNode(), model.getDestNode(), model.getCurrentAdjacent(), model.getVisited());
                view.updateLabels(model.getCurrentNode(), model.getDestNode(), model.getScore(), model.getTotalScore(), model.getPar(), model.getHole());
                view.addChoiceButtonActionListeners(new selectNode());
                System.out.println("Par - " + model.getPar());
            }
        }
    }
    class selectNode implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int chosenNode = Integer.parseInt(((JButton) e.getSource()).getName());

            model.setWeight(chosenNode);
            model.setScore();
            model.setCurrentNode(chosenNode);
            model.setVisited(chosenNode);
            model.setCurrentAdjacent();

            if(chosenNode == model.getDestNode() && model.getHole() <= 3){
                model.setTotalScore();
                model.setRandomNodes();
                model.setCurrentAdjacent();
                model.setPar();
                model.setHole();
                model.clearVisited();
                view.resetNodes();
                view.updateLabels(model.getCurrentNode(), model.getDestNode(), model.getScore(), model.getTotalScore(), model.getPar(), model.getHole());
                view.updateNodes(model.getCurrentNode(), model.getDestNode(), model.getCurrentAdjacent(), model.getVisited());
                System.out.println("NEW GAME ----------------------------");
                System.out.println("Total Score -" + model.getTotalScore());
                System.out.println("Start Node -" + model.getCurrentNode());
                System.out.println("End Node -" + model.getDestNode());
                System.out.println("Par - " + model.getPar());

            }

            if (model.getHole() > 3) {
                model.setTotalScore();
                System.out.println("WINNER");
                System.out.println("Total Score -" + model.getTotalScore());
            } else{
                view.UpdateButtons(model.getMaxAdjacents(),model.getCurrentAdjacent());
                view.updateNodes(model.getCurrentNode(), model.getDestNode(), model.getCurrentAdjacent(), model.getVisited());
                view.updateLabels(model.getCurrentNode(), model.getDestNode(), model.getScore(), model.getTotalScore(), model.getPar(), model.getHole());

            }

        }

        }
}
