package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver {
    LinkedHashMap<Vector2d, Animal> animals = new LinkedHashMap<>();

    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    public String toString(){
        MapVisualizer mapVisualizer=new MapVisualizer(this);
        return mapVisualizer.draw(this.lowerLeft,this.upperRight);

    }



    public boolean place(Animal animal){
        if(this.isOccupied(animal.initialPosition)) return false;

        this.animals.put(animal.initialPosition,animal);
        this.upperRight=this.upperRight.upperRight(animal.initialPosition);
        this.lowerLeft=this.lowerLeft.lowerLeft(animal.initialPosition);
        return true;

    }


    public boolean isOccupied(Vector2d position) {
        if(animals.get(position)!=null)return true;
        return false;
    }

    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
            Animal curr=animals.get(oldPosition);
            animals.put(newPosition,curr);
            animals.remove(oldPosition);

    }

}