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
            if(this.isOccupied(animal.position)){
                throw new IllegalArgumentException(animal.position.toString()+" is not legal place to place animal");
            }
            this.animals.put(animal.position,animal);
            this.mapBoundary.addVector(animal.position);
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