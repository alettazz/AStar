import java.util.ArrayList;

public interface State {

    boolean isGoal();

    double findCost();

    void printState();

    boolean equals(State s);

    ArrayList<State> genSuccessors();
}