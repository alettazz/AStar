package solverelements;

import boardelements.Board;

import java.util.ArrayList;

public class SolverSolution {

    private ArrayList<SolverState> states;
    private int moves;
    private boolean solvable;


    public SolverSolution() {
    }

    public SolverSolution(ArrayList<SolverState> states, int moves, boolean solvable) {
        this.states = states;
        this.moves = moves;
        this.solvable = solvable;
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
