package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{

    public ArrayList<Animal> animals;
    public MoveDirection[] directions;
    public IWorldMap map;
    public SimulationEngine(MoveDirection[] directions,IWorldMap map,Vector2d[] positions){
        this.animals=new ArrayList<Animal>();
        this.directions=directions;
        this.map=map;
        for (Vector2d vect:positions) {
            Animal newAnimal=new Animal(this.map,vect);

                this.map.place(newAnimal);
                this.animals.add(newAnimal);


        }
    }

    @Override
    public void run() {
        int x=0;
        int i=0;
        for (MoveDirection dir:directions){
            x=i%this.animals.size();
            i+=1;
            this.animals.get(x).move(dir);
            System.out.println(this.map.toString());
        }
    }
}
