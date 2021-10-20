package Physics.Rigidbodies;

/**
 * Apical edges form the outer boundary of the cell that is facing towards the epithelium.
 */
public class ApicalEdge extends Edge{
    public ApicalEdge(Node a, Node b)
    {
        MakeNewEdge(a,b);
        elasticConstant = .02f;
    }
    public static ApicalEdge clone(ApicalEdge edge){
        return new ApicalEdge(edge.getNodes()[0], edge.getNodes()[1]);
    }
}
