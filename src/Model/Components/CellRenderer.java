package Model.Components;

import GUI.IColor;
import GUI.IRender;
import GUI.Painter;
import Model.Cell;
import Physics.Rigidbodies.Edge;
import Physics.Rigidbodies.Node;

import java.awt.*;

public class CellRenderer extends Component implements IRender, IColor
{
    Cell cell;
    private Color color = Painter.DEFAULT_COLOR;

    @Override
    public void init() {
        cell = (Cell) parent;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        // set color for all nodes in the cell
        for(Node node : cell.getNodes() )
        {
            if(node instanceof IColor)
            {
                ((IColor) node).setColor(color);
            }
        }
        // set color for all edges in the cell
        for(Edge edge: cell.getEdges())
        {
            if(edge instanceof  IColor)
            {
                ((IColor) edge).setColor(color);
            }
        }

    }

    @Override
    public void render()
    {
        //cell.drawNormals();
        Painter.drawCell(cell);
    }
}
