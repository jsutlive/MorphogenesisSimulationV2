package Utilities.Mock;

import Engine.Simulation;

public class SimulationMock extends Simulation {
    public int a,b;
    public SimulationMock(String _title) {
        super(_title);
    }

    private void Tick(){
        a+= 5;
        b+= 5;
    }

    private void Render(){

    }
}
