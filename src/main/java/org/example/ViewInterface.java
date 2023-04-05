package org.example;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public interface ViewInterface {
    void createVisualGraph(HashMap<Integer, ArrayList<Node>> adjacencyList);
    void choicePanelButtonSetup(int maxAdj,  int[] currentAdjacent, int[] currentAdjacentWeights);
    void UpdateButtons(int maxAdj, int[] currentAdjacent, int[] currentAdjacentWeights);
    void updateNodes(int curNode, int dest, int[] adjacentNodes, ArrayList<Integer> visited);
    void updateLabels(int currNode, int destNode, int score, int totalScore, int par, int hole);
    void addLevelButtonHomeButtonAndInstructionButtonActionListeners(ActionListener selectLevel, ActionListener home, ActionListener instructions);
    void addChoiceButtonActionListeners(ActionListener selectNode);



}
