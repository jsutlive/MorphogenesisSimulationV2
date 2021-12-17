package Model.Components;

import GUI.IColor;
import GUI.IRender;
import GUI.Painter;
import Model.EdgeMono;
import Physics.Rigidbodies.BasalEdge;
import Physics.Rigidbodies.Edge;
import Physics.Rigidbodies.Node;
import Utilities.Geometry.Vector2f;
import Utilities.Math.CustomMath;

import java.awt.*;

public class EdgeRenderer extends Component implements IRender, IColor {
    private Color color = Painter.DEFAULT_COLOR;
    private EdgeMono edge;
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void render() {
        boolean hasDrawn = false;
        Node[] nodes = edge.getNodes();
        Vector2f pos1 = nodes[0].getPosition();
        Vector2f pos2 = nodes[1].getPosition();

        Painter.drawLine(pos1.asInt(), pos2.asInt(), color);
        if(edge.getEdge() instanceof BasalEdge){
        Painter.drawEdgeNormal(edge.getEdge());
        }
    }

    @Override
    public void init() {
        edge = (EdgeMono) parent;
    }
}