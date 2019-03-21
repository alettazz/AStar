package solverelements;

import boardelements.Board;

import java.util.ArrayList;

public class SolverSolution {

    private ArrayList<SolverState> states;
    private int moves;
    private boolean solvable;


    public SolverSolution() {
    }

    public ArrayList<SolverState> getStates() {
        return states;
    }

    public void setStates(ArrayList<SolverState> states) {
        this.states = states;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public void setSolvable(boolean solvable) {
        this.solvable = solvable;
    }
}
