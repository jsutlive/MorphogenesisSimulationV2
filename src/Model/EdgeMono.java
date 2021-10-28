package Model;

import Engine.Object.MonoBehavior;
import Engine.States.State;
import Model.Components.EdgeRenderer;
import Physics.Rigidbodies.ApicalEdge;
import Physics.Rigidbodies.Edge;
import Physics.Rigidbodies.Node;

import java.awt.*;

/**
 * EdgeMono creates a mock edge to behave as part of the system as a cell would.
 * Uses a specialized renderer.
 * Used for debugging purposes.
 */
public class EdgeMono extends MonoBehavior {

    private Edge edge;

    public EdgeMono(){}

    public void setEdge(Edge e){
        this.edge = e;
    }

    public Node[] getNodes(){
        return edge.getNodes();
    }

    @Override
    public void awake() throws InstantiationException, IllegalAccessException {
        EdgeRenderer renderer = new EdgeRenderer();
        this.addComponent(renderer);
        State.setFlagToRender(this);
    }

    public void setColor(Color color){
        EdgeRenderer rend = (EdgeRenderer) getComponent(EdgeRenderer.class);
        rend.setColor(color);
    }

    @Override
    public void update() {
        if(edge instanceof ApicalEdge) {
            edge.constrict(.15f, .25f);
        }
        edge.constrict(.05f, 1f);
        for (Node node: getNodes()) node.Move();
    }
}
