import boardelements.Board;
import boardelements.TileCoordinate;
import solverelements.PriorityQItem;
import solverelements.SolverSolution;
import solverelements.SolverState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Runner {
    private static Board startB;
    private static Board goalB;

    public Runner(Board initial) {
        this.startB = initial;
        this.goalB = new Board(initial.getGoalBoard(3, 3), new TileCoordinate(1, 1));
    }

    public static void main(String[] args) {


        int[][] initial = new int[3][3];
        for (int i = 0, n = 1 ; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initial[i][j] = n++;
            }
        }
        initial[2][1]=0;
        initial[2][2]=8;

        Board iBoard = new Board(initial, new TileCoordinate(2, 1));
        Runner runner = new Runner(iBoard);
        System.out.println(runner.solve().getMoves());
    }

    public static boolean hasBoardBeenUsed(Board board, ArrayList<SolverState> queue) {
        boolean used = false;
        for (SolverState solverState : queue) {
            if (!solverState.getBoard().equals(board)) {
                used = false;
                return used;
            }
        }
        return used;

    }

    public static solverelements.SolverSolution solve() {
        SolverState state = new SolverState();
        state.setBoard(startB);
        state.setMoves(0);
        state.setPrevious(null);
        ArrayList<SolverState> history = new ArrayList<SolverState>();
        history.add(state);

        while (!state.getBoard().equals(goalB)) {
            state = getNextMove(state, history);
            history.add(state);
        }
        return new SolverSolution(history, state.getMoves(), true);
    }

    public static SolverState getNextMove(SolverState state, ArrayList<SolverState> history) {
        ArrayList<Board> neighbors = new ArrayList<>();
        for (Board neighborBoard : state.getBoard().getNeighborBoards()) {
            if (!hasBoardBeenUsed(neighborBoard, history)) {
                neighbors.add(neighborBoard);
            }
        }
        ArrayList<PriorityQItem> priority = createPriorityQueue(neighbors, state.getMoves());
        if (priority.size() < 1) {
            System.out.println("NotsolvableError");
        }
        return new SolverState(priority.get(0).getBoard(), state.getMoves() + 1, state);
    }

    public static ArrayList<PriorityQItem> createPriorityQueue(ArrayList<Board> boards, int moves) {
        int priority;
        Board boardA;
        ArrayList<Board> boardsToBeSorted = new ArrayList<>();
        ArrayList<PriorityQItem> priorityQItems = new ArrayList<>();

        for (Board board : boards) {
            priority = board.getPriority(moves);
            boardA = board;
            // boardA.setPriority(priority);
            //boardsToBeSorted.add(boardA);
            priorityQItems.add(new PriorityQItem(priority, boardA));
        }

        Collections.sort(priorityQItems, new Comparator<PriorityQItem>() {
            @Override
            public int compare(PriorityQItem o1, PriorityQItem o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });


        return priorityQItems;

    }


}

