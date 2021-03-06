package Physics;

import Engine.Object.MonoBehavior;
import Engine.Object.Tag;
import Physics.Bodies.Cell.Cell;
import Physics.Bodies.Cell.CellGroup;
import Physics.Forces.Force;

import java.util.HashSet;

public class PhysicsSystem extends MonoBehavior
{
    private HashSet<Force> forces = new HashSet<>();

    public HashSet<Force> getForces()
    {
        return forces;
    }

    public void addForce(Force force)
    {
        forces.add(force);
    }

    public void addForce(Force force, CellGroup group)
    {
        forces.add(force);
        force.attach(group);
        force.start();
    }

    public void addForce(Force force, Cell cell)
    {
        forces.add(force);
        force.attach(cell);
        force.start();
    }

    public void removeForce(Force force)
    {
        forces.remove(force);
    }

    @Override
    public void awake() {
        this.addTag(Tag.PHYSICS);
    }

    public void update()
    {
        for(Force force: forces)
        {
            force.update();
        }
    }

    @Override
    public void destroy() {

    }

    public <T extends Force> Force getComponent(Class<T> forceClass)
    {
        for (Force force:forces) {
            if(forceClass.isAssignableFrom(force.getClass())) return force;
        }
        return null;
    }


    public PhysicsSystem(){}
}
