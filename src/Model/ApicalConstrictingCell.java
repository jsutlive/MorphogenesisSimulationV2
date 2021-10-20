package Model;

import Physics.Rigidbodies.ApicalEdge;
import Physics.Rigidbodies.BasalEdge;
import Physics.Rigidbodies.Edge;
import Physics.Rigidbodies.Node;

import java.awt.*;
import java.util.List;

public class ApicalConstrictingCell extends Cell
{
    public ApicalConstrictingCell()
    {
        internalConstant = .1f;
    }

    @Override
    public void setColor(Color color){
        super.setColor(color);
        for(Edge edge: edges){
            if(edge instanceof ApicalEdge) edge.setColor(Color.RED);
            if(edge instanceof BasalEdge) edge.setColor(Color.BLUE);
        }
    }
    @Override
    public void update() {
        //System.out.println(edges.size());

        for(Edge edge: edges)
        {
            if(edge instanceof ApicalEdge)
            {
                //edge.constrict(constant * (1 - getRingLocation()/40), ratio);
                edge.constrict(constant , ratio);
                //System.out.println(edge.getInitialLength());
            }
            else
            {
                edge.constrict(edge.getElasticConstant(), elasticRatio);
            }
        }
        for(Edge edge: internalEdges) edge.constrict(internalConstant, elasticRatio);
        for(Node node: nodes)
        {
            node.Move();
        }
    }
}
