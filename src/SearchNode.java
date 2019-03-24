public class SearchNode
{

    private State currentState;
    private SearchNode parent;
    private double cost;
    private double hCost;
    private double fCost;


    public SearchNode(State s)
    {
        currentState = s;
        parent = null;
        cost = 0;
        hCost = 0;
        fCost = 0;
    }


    public SearchNode(SearchNode prev, State s, double c, double h)
    {
        parent = prev;
        currentState = s;
        cost = c;
        hCost = h;
        fCost = cost + hCost;
    }


    public State getCurrentState()
    {
        return this.currentState;
    }

    public SearchNode getParent()
    {
        return this.parent;
    }

    public double getFCost()
    {
        return this.fCost;
    }

    public double getCost()
    {
        return this.cost;
    }

}
