package Model;

import Physics.Forces.Force;
import Physics.Forces.Gradient;
import Physics.Rigidbodies.*;

import java.awt.*;

/**
 * An Apical Constricting Cell undergoes the following forces:
 *
 * Active:
 * Apical constriction
 *
 * Passive:
 * Elasticity
 * Osmosis
 */
public class ApicalConstrictingCell extends Cell
{
    float apicalConstrictingConstant = .75f;
    float apicalConstrictingRatio = .01f;

    public ApicalConstrictingCell()
    {
        internalConstant = .035f;
        elasticConstant = .05f;
    }

    /**
     * update physics on Apical Constricting Cells
     * overrides the update method as described in Cells
     */
    @Override
    public void update() {
        setNodePositions();
        for(Edge edge: edges)
        {
            if(edge instanceof LateralEdge) {
                Force.elastic(edge, .05f);
            }
            else if (edge instanceof BasalEdge)
            {
                Force.elastic(edge, 0.07f);
            }
            else if(edge instanceof ApicalEdge)
            {
                Force.elastic(edge, elasticConstant);

                //If an apical gradient is defined, we use this for apical constriction, else we use the default constants
                if(Model.apicalGradient != null){
                    Gradient gr = Model.apicalGradient;
                    Force.constrict(edge,  gr.getConstants()[getRingLocation() - 1],
                            gr.getRatios()[gr.getRatios().length - getRingLocation()]);

                }else {
                    Force.constrict(edge, apicalConstrictingConstant, apicalConstrictingRatio);
                }
            }
            //adjustCorners();
        }
        for(Edge edge: internalEdges) Force.elastic(edge, internalConstant);
        Force.restore(this, osmosisConstant) ;

    }

    public void constrictApicalEdge()
    {
        for(Edge edge:edges){
            if(edge instanceof ApicalEdge) Force.constrict(edge, apicalConstrictingConstant, apicalConstrictingRatio);
        }
    }
}
