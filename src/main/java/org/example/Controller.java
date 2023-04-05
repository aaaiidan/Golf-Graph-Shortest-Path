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
        this.view.addLevelButtonHomeButtonAndInstructionButtonActionListeners(new selectLevel(), new home(), new instructions());

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
                model.setCurrentAdjacentWeights();
                model.setPar();
                model.setVisited(model.getCurrentNode());

                view.openLevelPanel();
                view.createVisualGraph(model.getAdjacencyList());
                view.choicePanelButtonSetup(model.getMaxAdjacent(), model.getCurrentAdjacent(), model.getCurrentAdjacentWeights());
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
            model.setCurrentAdjacentWeights();

            if(chosenNode == model.getDestNode() && model.getHole() <= model.getNoOfHoles()){
                model.clearVisited();
                model.setTotalScore();
                model.setRandomNodes();
                model.setCurrentAdjacent();
                model.setCurrentAdjacentWeights();
                model.setPar();
                model.setHole();
                model.setVisited(model.getCurrentNode());
            }
            if (model.getHole() > model.getNoOfHoles()) {
                model.clearVisited();
                view.leaveLevelPanel();
                view.displayWinner(model.getTotalScore());
            } else{
                view.UpdateButtons(model.getMaxAdjacent(),model.getCurrentAdjacent(), model.getCurrentAdjacentWeights());
                view.updateNodes(model.getCurrentNode(), model.getDestNode(), model.getCurrentAdjacent(), model.getVisited());
                view.updateLabels(model.getCurrentNode(), model.getDestNode(), model.getScore(), model.getTotalScore(), model.getPar(), model.getHole());

            }

        }
    }

    class home implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.returnHome();
        }
    }
    class instructions implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.displayInstructions();
        }
    }
}
