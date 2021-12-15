package Model.Schema;

import Engine.Object.MonoBehavior;

/**
 * Toy model for debugging purposes
 */
public class Schema extends MonoBehavior {

    //IPrototype proto = new IteratedCellsAsEdgeMonos();
    IPrototype proto = new SegmentedLine();

    @Override
    public void awake() throws InstantiationException, IllegalAccessException {
        proto.Generate();
    }

    @Override
    public void update() {

    }


}
