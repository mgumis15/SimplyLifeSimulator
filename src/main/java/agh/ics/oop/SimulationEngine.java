package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.ArrayList;

public class SimulationEngine implements IEngine,Runnable{

    public ArrayList<Animal> animals;
    public MoveDirection[] directions;
    public IWorldMap map;
    private ArrayList<App>  observers;
    private int moveDelay=0;
    public SimulationEngine(IWorldMap map,Vector2d[] positions){
        this.animals=new ArrayList<Animal>();
        this.observers=new ArrayList<App>();

        this.map=map;
        for (Vector2d vect:positions) {
            Animal newAnimal=new Animal(this.map,vect);

                this.map.place(newAnimal);
                this.animals.add(newAnimal);


        }
    }
    public void addObserver(App observer){
        System.out.println("DODANED");
        this.observers.add(observer);
    }
    public void setDirections(MoveDirection[] directions){
        this.directions=directions;
    }

    public void setDelay(int delay){
        this.moveDelay=delay;
    }
    @Override
    public void run() {
        int size=this.animals.size();
        int i=0;
        for (MoveDirection dir:directions){
            i+=1;
            try{
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e) {
                System.out.println("Błąd w usypianiu");
            }
            this.animals.get(i%size).move(dir);
            for (App app:this.observers){
                app.positionChanged();
            }

        }
    }



}
