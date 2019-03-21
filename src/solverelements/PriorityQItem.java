package solverelements;

import boardelements.Board;

public class PriorityQItem {
    private int priority;
    private Board board;

    public PriorityQItem() {
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
