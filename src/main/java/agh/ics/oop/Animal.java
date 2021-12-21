package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Animal implements IMapElement{

    public Vector2d position;
    public MapDirection direction;
    public int energy;
    public ArrayList<Integer> genes;
    private IWorldMap map;
    private ArrayList<IPositionChangeObserver>  observers;
    public Animal(IWorldMap map, Vector2d position, int energy){

        this.map=map;
        this.observers=new ArrayList<>();
        this.addObserver((IPositionChangeObserver) map);
        this.position=position;
        this.energy=energy;
        this.direction=MapDirection.NORTH;
        this.genes=new ArrayList<Integer>();
        int randomDirection=new Random().nextInt(8);

        this.direction=this.direction.next(randomDirection);

        for (int i = 0; i < 32; i++) {
            this.genes.add(new Random().nextInt(8));

        }
        Collections.sort(this.genes);
    }


    public int getEnergy() {
        return energy;
    }

    public ArrayList<Integer> getGenes() {
        return genes;
    }

    private boolean isAt(Vector2d position2){
            return this.position.equals(position2);
    }

    public boolean move(){
        int ind=new Random().nextInt(32);
        int direct=this.genes.get(ind);
        if(direct==0){
            Vector2d nextIT=this.position.add(this.direction.toUnitVector());
            if (this.map.canMoveTo(nextIT)){
                this.positionChanged(this.position,nextIT);
                this.position=nextIT;
                return true;
            };
        }else if(direct==4){
            Vector2d prev=this.position.substract(this.direction.toUnitVector());
            if (this.map.canMoveTo(prev)){
                this.positionChanged(this.position,prev);
                this.position=prev;
                return true;
            };
        }else{
                this.direction=this.direction.next(direct);
        }
        return false;
    }

    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer:observers) {
            observer.positionChanged(oldPosition,newPosition);
        }
    }


//    public String toString(){
//        String odp=switch (this.direction){
//            case NORTH -> "^";
//            case EAST -> ">";
//            case WEST -> "<";
//            case SOUTH -> "v";
//            default -> "";
//        };
//        return odp;
//    }

//    public String getUrl(){
//        String odp=switch (this.direction){
//            case NORTH -> "src/main/resources/up.png";
//            case EAST -> "src/main/resources/left.png";
//            case WEST -> "src/main/resources/right.png";
//            case SOUTH -> "src/main/resources/down.png";
//            default -> "";
//        };
//        return odp;
//    }
}


