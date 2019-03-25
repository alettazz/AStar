import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleState implements State {

    private final int[] GOAL = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
    private int notAtPlace = 0;
    private int manhatanDist = 0;
    private int[] currentBoard;

    public PuzzleState(int[] board) {
        currentBoard = board;
        setNotAtPlace();
        setManDist();
    }

    @Override
    public double findCost() {
        return 1;
    }


    private void setNotAtPlace() {
        for (int i = 0; i < currentBoard.length; i++) {
            if (currentBoard[i] != GOAL[i]) {
                notAtPlace++;
            }
        }
    }


    private void setManDist() {
        int index = -1;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                index++;
                int pos = (currentBoard[index] - 1);
                if (pos != -1) {
                    int horizontal = pos % 3;
                    int vertical = pos / 3;
                    manhatanDist += Math.abs(vertical - (y)) + Math.abs(horizontal - (x));
                }
            }
        }
    }

    public int getManhattanDist() {
        return this.manhatanDist;
    }

    private int getZero() {
        int zeroIndex = -1;
        for (int i = 0; i < 9; i++) {
            if (currentBoard[i] == 0)
                zeroIndex = i;
        }
        return zeroIndex;
    }


    public int getNotAtPlace() {
        return this.notAtPlace;
    }


    private int[] cpyBoard(int[] state) {
        int[] ret = new int[9];
        for (int i = 0; i < 9; i++) {
            ret[i] = state[i];
        }
        return ret;
    }


    @Override
    public ArrayList<State> genSuccessors() {
        ArrayList<State> successorsList = new ArrayList<>();
        int zero = getZero();

        if (zero != 2 && zero != 5 && zero != 8) {
            swap(zero + 1, zero, successorsList);
        }
        if (zero != 0 && zero != 3 && zero != 6) {
            swap(zero - 1, zero, successorsList);
        }
        if (zero != 0 && zero != 1 && zero != 2) {
            swap(zero - 3, zero, successorsList);
        }
        if (zero != 6 && zero != 7 && zero != 8) {
            swap(zero + 3, zero, successorsList);
        }
        return successorsList;
    }

    private void swap(int d1, int d2, ArrayList<State> s) {
        int[] cpy = cpyBoard(currentBoard);
        int temp = cpy[d1];
        cpy[d1] = currentBoard[d2];
        cpy[d2] = temp;
        s.add((new PuzzleState(cpy)));
    }

    @Override
    public boolean isGoal() {
        return Arrays.equals(currentBoard, GOAL);
    }

    @Override
    public void printState() {
        System.out.println(currentBoard[0] + " | " + currentBoard[1] + " | "+ currentBoard[2]);
        System.out.println("----------");
        System.out.println(currentBoard[3] + " | " + currentBoard[4] + " | "+ currentBoard[5]);
        System.out.println("----------");
        System.out.println(currentBoard[6] + " | " + currentBoard[7] + " | "+ currentBoard[8]);

    }


    @Override
    public boolean equals(State s) {
        return Arrays.equals(currentBoard, ((PuzzleState) s).getCurrentBoard());

    }

    public int[] getCurrentBoard() {
        return this.currentBoard;
    }


}
