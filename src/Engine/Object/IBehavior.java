package Engine.Object;

public interface IBehavior
{
    /**
     * Called by the state for each object upon its creation.
     */
    void awake() throws InstantiationException, IllegalAccessException;

    /**
     * This method is called a single time before the physics loop begins
     */
    void start() throws InstantiationException, IllegalAccessException;

    /**
     * This method is called once per frame to update the physics
     */
    void update();


}
