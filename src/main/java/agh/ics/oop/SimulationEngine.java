package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{

    public int placed;
    public SimulationEngine(MoveDirection[] directions,IWorldMap map,Vector2d[] positions){
        this.placed=0;

        for (Vector2d vect:positions) {
                if(map.place(new Animal(map,vect))){
                    this.placed+=1;
                }
        }
    }

    @Override
    public void run() {

    }
}
