import java.util.ArrayList;
/**
 * Created by @alettazz.
 * Date: 3/21/2019
 */
public interface State {

    boolean isGoal();

    double findCost();

    void printState();

    boolean equals(State s);

    ArrayList<State> genSuccessors();
}