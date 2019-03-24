package boardelements;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    //holding state of the board
    private int[][] board;

    private int height = 3;
    private int width = 3;

    // goal boardelements.Board
    private int[][] goal;

    //actual position of 0, the empty tile
    private TileCoordinate zeroPos;
    private int priority =0 ;


    public Board(int[][] board, TileCoordinate zeroPos) {
        this.board = board;
        this.zeroPos = zeroPos;
        this.height = board.length;
        this.width = board[0].length;
        this.goal = getGoalBoard(this.width, this.height); //generates the goalboard

    }

    public int[][] getGoalBoard(int width, int height) {
        int[][] goal = new int[width][height];

        int maxNo = width * height; //should always be 9

        for (int i = 0, n = 1; i < height; i++) {

            for (int j = 0; j < width; j++, n++) {
                goal[i][j] = (n < maxNo ? n : 0);
            }
        }

        return goal;
    }


    public boolean equals(Board obj) {
        int[] otherBoardFlatten = flatten(obj.board);
        return Arrays.equals(flatten(this.board), (otherBoardFlatten));
    }

    private int[] flatten(int[][] boardToFlatten) {
        int[] flatBoard = new int[boardToFlatten.length * boardToFlatten.length];
        int n = 0;

        for (int i = 0; i < boardToFlatten.length; i++) {
            for (int j = 0; j < boardToFlatten.length; j++) {
                flatBoard[n++] = boardToFlatten[i][j];
            }

        }
        return flatBoard;
    }


    public int HammingHeurisitcs(int moves) {
        //each value should be at index of value-1

        int[] currentFlattBoard = flatten(this.board);

        for (int i = 0; i < currentFlattBoard.length; i++) {
            if (currentFlattBoard[i] != i + 1) {
                moves++;
            }
        }
        return moves;
    }


    public int ManhattanHeuristics(int moves) {
        int numberOfTiles = this.height * this.width;
        int priority = moves;

        for (int i = 0; i < numberOfTiles; i++) {
            CoordComparator coordComparator = new CoordComparator(zeroPos);

            for (int j = 0; j < this.board.length; j++) {
                int boardX = this.board[j][i];
                int goalX = this.goal[j][i];

                if (boardX != -1) {
                    coordComparator.getBoard().setX(boardX);
                    coordComparator.getBoard().setY(j);
                }

                if (goalX != -1) {
                    coordComparator.getGoal().setX(goalX);
                    coordComparator.getGoal().setY(j);
                }

                priority += Math.abs(coordComparator.getBoard().getX() - coordComparator.getGoal().getX())
                        + Math.abs(coordComparator.getBoard().getY() - coordComparator.getGoal().getY());
            }

        }
        return priority;
    }

    //takes the param moves as the number of moves so far and returns the lowest result as boards priority
    public int getPriority(int moves) {
        int hamming = HammingHeurisitcs(moves);
        int manhattan = ManhattanHeuristics(moves);
        return hamming > manhattan ? manhattan : hamming;

    }

    public ArrayList<Board> getNeighborBoards() {
        ArrayList<Board> result = new ArrayList<>();

        for (int i = 1; i > -2; i -= 2) {
            //vizsyinteesen
            if (zeroPos.getX() + i >= 0 && zeroPos.getX() + i < width) {
                result.add(newBoardFroPos(board, new TileCoordinate(zeroPos.getX() + i, zeroPos.getY()), zeroPos));
            }

            //fuggolegesen
            if (zeroPos.getY() + i >= 0 && zeroPos.getY() + i < height) {
                result.add(newBoardFroPos(board, new TileCoordinate(zeroPos.getX(), zeroPos.getY() + i), zeroPos));
            }

        }
        return result;

    }

    private Board newBoardFroPos(int[][] ref, TileCoordinate pos, TileCoordinate oldPos) {
        ref[oldPos.getY()][oldPos.getX()] = ref[pos.getY()][pos.getX()];
        ref[pos.getY()][pos.getX()] = 0;

        return new Board(ref, pos);
    }


    @Override
    public String toString() {
        return "boardelements.Board{" +
                "board=" + Arrays.toString(board) +
                ", goal=" + Arrays.toString(goal) +
                ", zeroPos=" + zeroPos +
                '}';
    }

    public void setPriority(int priority) {
        this.priority =priority;
    }

    public int getPriorityState(){
        return  this.priority;
    }
}


