package Model.Schema;

import Engine.Object.MonoBehavior;
import Physics.Rigidbodies.Node;
import Utilities.Geometry.Vector2f;
import Utilities.Model.Builder;

import java.util.ArrayList;
import java.util.List;

public class IteratedCellsAsEdgeMonos implements IPrototype {

    @Override
    public List<MonoBehavior> Generate() throws InstantiationException, IllegalAccessException {
        List<Node> nodesA = new ArrayList<>();
        List<Node> nodesB = new ArrayList<>();
        List<Node> cellNodes = new ArrayList<>();
        for(int i = 0; i < 2; i++)
        {
            float x = (i * 100) + 300;
            for(int j = 0; j < 5; j++)
            {
                float y = (j *100) + 200;
                Node n = new Node(new Vector2f(x,y));
                nodesA.add(n);
            }
            if(i > 0)
            {
                cellNodes.addAll(nodesA);
                cellNodes.addAll(nodesB);
                Builder.buildCellAsEdgeMono(cellNodes);
            }
        }
        nodesB = FlipNodeSet(nodesA);
        return null;
    }

    private List<Node> FlipNodeSet(List<Node> nodes)
    {
        List<Node> nodesFlipped = new ArrayList<>();
        for(int i = nodes.size()-1; i >= 0; i--)
        {
            nodesFlipped.add(nodes.get(i));
        }
        return nodesFlipped;
    }
}
