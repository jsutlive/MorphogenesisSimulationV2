package Physics.Rigidbodies;

public class BasalEdge extends Edge{
    public BasalEdge(Node a, Node b)
    {
        MakeNewEdge(a,b);
        elasticConstant = .25f;
    }
    public static BasalEdge clone(BasalEdge edge){
        return new BasalEdge(edge.getNodes()[0], edge.getNodes()[1]);
    }
}
