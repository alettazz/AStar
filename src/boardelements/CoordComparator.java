package boardelements;

public class CoordComparator {
    private TileCoordinate board;
    private TileCoordinate goal;

    public CoordComparator() {
    }

    public CoordComparator(TileCoordinate board, TileCoordinate goal) {
        this.board = board;
        this.goal = goal;
    }

    public TileCoordinate getBoard() {
        return board;
    }

    public void setBoard(TileCoordinate board) {
        this.board = board;
    }

    public TileCoordinate getGoal() {
        return goal;
    }

    public void setGoal(TileCoordinate goal) {
        this.goal = goal;
    }
}
