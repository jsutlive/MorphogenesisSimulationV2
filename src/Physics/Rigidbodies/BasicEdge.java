package Physics.Rigidbodies;

public class BasicEdge extends Edge{
    public BasicEdge(Node a, Node b)
    {
        elasticConstant = .05f;
        MakeNewEdge(a, b);
    }
    public BasicEdge(){}
    public static BasicEdge clone(BasicEdge edge){
        return new BasicEdge(edge.getNodes()[0], edge.getNodes()[1]);
    }
}
