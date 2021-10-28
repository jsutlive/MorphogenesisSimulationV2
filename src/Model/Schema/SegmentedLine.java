package Model.Schema;

import Engine.Object.MonoBehavior;
import Engine.States.State;
import Model.EdgeMono;
import Physics.Rigidbodies.ApicalEdge;
import Physics.Rigidbodies.BasicEdge;
import Physics.Rigidbodies.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SegmentedLine implements IPrototype{
    public List<MonoBehavior> Generate() throws InstantiationException, IllegalAccessException {
        List<MonoBehavior> mono = new ArrayList<>();
        Node a = new Node (200, 300);
        Node b = new Node(300,300);
        Node c = new Node(400,300);
        Node d = new Node(500,300);
        Node e = new Node(600, 300);
        EdgeMono A = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono B = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono C = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono D = (EdgeMono) State.create(EdgeMono.class);

        A.setEdge(new ApicalEdge(a,b));
        A.setColor(Color.BLUE);

        B.setEdge(new ApicalEdge(b,c));
        B.setColor(Color.red);

        C.setEdge(new ApicalEdge(c,d));
        C.setColor(Color.GREEN);

        D.setEdge(new ApicalEdge(d,e));
        D.setColor(Color.MAGENTA);
        return mono;
    }
}
