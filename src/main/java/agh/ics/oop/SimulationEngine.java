package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.ArrayList;

public class SimulationEngine implements IEngine,Runnable{

    public ArrayList<Animal> animals;
    public IWorldMap map;
    private ArrayList<App>  observers;
    private int moveDelay=0;
    public SimulationEngine(IWorldMap map,int moveDelay,int animalsOnStart,int grassOnStart){
        this.animals=new ArrayList<Animal>();
        this.observers=new ArrayList<App>();

        this.map=map;
       this.map.generateGrass(grassOnStart);
        for (int i = 0; i < animalsOnStart; i++) {

            Animal newAnimal=new Animal(this.map);

            this.map.place(newAnimal);
            this.animals.add(newAnimal);
        }
    }
    public void addObserver(App observer){
        System.out.println("DODANED");
        this.observers.add(observer);
    }

    public void setDelay(int delay){
        this.moveDelay=delay;
    }

    @Override
    public void run() {
        for (Animal animal:animals){
            try{
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e) {
                System.out.println("Błąd w usypianiu");
            }
            animal.move();
            for (App app:this.observers){
                app.positionChanged();
            }
        }
    }



}
