package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver {
    LinkedHashMap<Vector2d, Animal> animals = new LinkedHashMap<>();

    public MapBoundary mapBoundary=new MapBoundary();
    public String toString(){
        MapVisualizer mapVisualizer=new MapVisualizer(this);
        return mapVisualizer.draw(this.mapBoundary.getLowerCorner(), this.mapBoundary.getUpperCorner());
    }

    public boolean place(Animal animal){
            if(this.isOccupied(animal.initialPosition)){
                throw new IllegalArgumentException(animal.initialPosition.toString()+" is not legal place to place animal");
            }
            this.animals.put(animal.initialPosition,animal);
            this.mapBoundary.addVector(animal.initialPosition);
            return true;
    }

    public boolean isOccupied(Vector2d position) {
        if(this.animals.containsKey(position))return true;
        return false;
    }

    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
            Animal curr=animals.get(oldPosition);
            animals.put(newPosition,curr);
            animals.remove(oldPosition);
            this.mapBoundary.positionChanged(oldPosition,newPosition);
    }

}