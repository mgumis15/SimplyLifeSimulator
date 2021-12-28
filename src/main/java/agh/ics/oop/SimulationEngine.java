package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationEngine implements IEngine,Runnable{

    public IWorldMap map;
    private ArrayList<App>  observers;
    private boolean runState=true;
    private AtomicBoolean killThread=new AtomicBoolean(false);
    private int moveDelay=0;
    public SimulationEngine(IWorldMap map,int moveDelay,int animalsOnStart,int grassOnStart){
        this.observers=new ArrayList<App>();
        this.moveDelay=moveDelay;
        this.map=map;

        if(!this.map.generateStartAnimals(animalsOnStart)){
            System.out.println("Can't place all animals");
            this.killThread.set(true);
        }
        this.map.generateStartGrasses(grassOnStart);

    }
    public void addObserver(App observer){

        this.observers.add(observer);
    }

    public boolean getRunState() {
        return this.runState;
    }

    public void stop(){
        this.runState=false;
    }
    public void conitnueRun(){
        this.runState=true;
    }
    public void endRun(){
        this.killThread.set(true);
    }
    @Override
    public void run() {
        while (!this.killThread.get()){

                try{
                    if(this.runState){
                   if(this.map.newDayRise()){
                    this.killThread.set(true);
                   }
                    }
                    Thread.sleep(this.moveDelay);
                } catch (InterruptedException e) {
                    System.out.println("Błąd w usypianiu");
                }
                for (App app:this.observers){
                    app.newDay();
                }
        }
    }
}
