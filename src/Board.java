import boardelements.TileCoordinate;

import java.util.Arrays;

public class Board {

    //holding state of the board
    private int[][] board;

    private int height = 3;
    private int width = 3;

    // goal Board
    private int[][] goal;

    //actual position of 0, the empty tile
    private TileCoordinate zeroPos;


    public Board(int[][] board, TileCoordinate zeroPos) {
        this.board = board;
        this.zeroPos = zeroPos;
        this.height = board.length;
        this.width = board[0].length;
        this.goal = getGoalBoard(this.width, this.height); //generates the goalboard

    }

    private int[][] getGoalBoard(int width, int height) {
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

    @Override
    public String toString() {
        return "Board{" +
                "board=" + Arrays.toString(board) +
                ", goal=" + Arrays.toString(goal) +
                ", zeroPos=" + zeroPos +
                '}';
    }
}


