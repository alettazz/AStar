import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AStarSearch {

    public static void search(int[] board, boolean rand, int randomPushes, boolean solseq, boolean nvisited, boolean pcost, String heuristic) {
        SearchNode root = new SearchNode(new PuzzleState(board));
        Queue<SearchNode> q = new LinkedList<>();
        q.add(root);

        int searchCount = 1;

        while (!q.isEmpty()) {
            SearchNode tempNode = q.poll();

            if (!tempNode.getCurrentState().isGoal()) {
                ArrayList<State> tempSuccessors = tempNode.getCurrentState().genSuccessors();
                ArrayList<SearchNode> nodeSuccessors = new ArrayList<>();


                for (int i = 0; i < tempSuccessors.size(); i++) {
                    SearchNode checkedNode;
                    if (heuristic.equals("1")) {//heuristic not at place
                        checkedNode = new SearchNode(tempNode, tempSuccessors.get(i), tempNode.getCost() + tempSuccessors.get(i).findCost(), ((PuzzleState) tempSuccessors.get(i)).getNotAtPlace());
                    } else {
                        checkedNode = new SearchNode(tempNode, tempSuccessors.get(i), tempNode.getCost() + tempSuccessors.get(i).findCost(), ((PuzzleState) tempSuccessors.get(i)).getManhattanDist());
                    }

                    if (!checkRepeats(checkedNode)) {
                        nodeSuccessors.add(checkedNode);
                    }
                }

                if (nodeSuccessors.isEmpty()) {
                    continue;
                }

                SearchNode lowestNode = nodeSuccessors.get(0);

                for (int i = 0; i < nodeSuccessors.size(); i++) {
                    if (lowestNode.getFCost() > nodeSuccessors.get(i).getFCost()) {
                        lowestNode = nodeSuccessors.get(i);
                    }
                }
                for (int i = 0; i < nodeSuccessors.size(); i++) {
                    if (nodeSuccessors.get(i).getFCost() == (int) lowestNode.getFCost()) {
                        q.add(nodeSuccessors.get(i));
                    }
                }
                searchCount++;
            } else {
                Stack<SearchNode> solutionPath = new Stack<>();
                solutionPath.push(tempNode);
                tempNode = tempNode.getParent();

                while (tempNode.getParent() != null) {
                    solutionPath.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                solutionPath.push(tempNode);

                int loopSize = solutionPath.size();

                for (int i = 0; i < loopSize; i++) {
                    tempNode = solutionPath.pop();
                    if (solseq) {
                        tempNode.getCurrentState().printState();
                        System.out.println();
                    }
                    if (rand && i == randomPushes) {
                        tempNode.getCurrentState().printState();
                        System.out.println();

                    }

                }

                if (pcost) {
                    System.out.println("Cost (the number of pushes done): " + tempNode.getCost());
                }
                if (nvisited) {
                    System.out.println("The number of nodes visited: " + searchCount);
                }

                System.exit(0);
            }
        }
    }

    private static boolean checkRepeats(SearchNode n) {
        boolean retValue = false;
        SearchNode checkNode = n;

        while (n.getParent() != null && !retValue) {
            if (n.getParent().getCurrentState().equals(checkNode.getCurrentState())) {
                retValue = true;
            }
            n = n.getParent();
        }
        return retValue;
    }

}
