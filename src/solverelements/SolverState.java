package solverelements;

import boardelements.Board;

public class SolverState {
    private Board board;
    private int moves;
    private SolverState previous;


    public SolverState() {
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public SolverState getPrevious() {
        return previous;
    }

    public void setPrevious(SolverState previous) {
        this.previous = previous;
    }
}
