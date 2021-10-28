package Model.Schema;

import Engine.Object.MonoBehavior;
import Engine.States.State;
import Model.EdgeMono;
import Physics.Rigidbodies.ApicalEdge;
import Physics.Rigidbodies.BasicEdge;
import Physics.Rigidbodies.Node;

import java.util.ArrayList;
import java.util.List;

public class FourCellsAsEdgeMonos implements IPrototype{

    @Override
    public List<MonoBehavior> Generate() throws InstantiationException, IllegalAccessException {
        List<MonoBehavior> mono = new ArrayList<>();
        // Cell 1
        Node a = new Node (300, 300);
        Node b  = new Node( 400, 300);
        Node c = new Node(400, 200);
        Node d = new Node( 400, 100);
        Node e = new Node(300, 100);
        Node f = new Node(300, 200);

        // Cell 2
        Node g = new Node(500, 300);
        Node h = new Node(500, 200);
        Node i = new Node(500, 100);

        // Cell 3
        Node j = new Node(600, 300);
        Node k = new Node(600, 200);
        Node l = new Node(600, 100);

        // Cell 4
        Node m = new Node(700, 300);
        Node n = new Node(700, 200);
        Node o = new Node(700, 100);

        //Cell 1 edges
        EdgeMono A = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono B = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono C = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono D = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono E = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono F = (EdgeMono) State.create(EdgeMono.class);

        A.setEdge(new ApicalEdge(a,b));
        B.setEdge(new BasicEdge(b,c));
        C.setEdge(new BasicEdge(c,d));
        D.setEdge(new BasicEdge(d,e));
        E.setEdge(new BasicEdge(e,f));
        F.setEdge(new BasicEdge(f,a));

        // Cell 2 edges
        EdgeMono G = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono H = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono I = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono J = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono K = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono L = (EdgeMono) State.create(EdgeMono.class);

        G.setEdge(new ApicalEdge(b,g));
        H.setEdge(new BasicEdge(g,h));
        I.setEdge(new BasicEdge(h,i));
        J.setEdge(new BasicEdge(i,d));
        K.setEdge(new BasicEdge(d,c));
        L.setEdge(new BasicEdge(c,b));

        // Cell 3 edges
        EdgeMono M = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono N = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono O = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono P = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono Q = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono R = (EdgeMono) State.create(EdgeMono.class);

        M.setEdge(new ApicalEdge(g,j));
        N.setEdge(new BasicEdge(j,k));
        O.setEdge(new BasicEdge(k,l));
        P.setEdge(new BasicEdge(l,i));
        Q.setEdge(new BasicEdge(i,h));
        R.setEdge(new BasicEdge(h,g));

        // Cell 4 edges
        EdgeMono S = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono T = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono U = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono V = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono W = (EdgeMono) State.create(EdgeMono.class);
        EdgeMono X = (EdgeMono) State.create(EdgeMono.class);

        S.setEdge(new ApicalEdge(j,m));
        T.setEdge(new BasicEdge(m,n));
        U.setEdge(new BasicEdge(n,o));
        V.setEdge(new BasicEdge(o,l));
        W.setEdge(new BasicEdge(l,k));
        X.setEdge(new BasicEdge(k,j));


        return mono;
    }


}
