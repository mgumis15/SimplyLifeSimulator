package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class NoBoundariesMap extends AbstractWorldMap{

    public NoBoundariesMap(int width, int height, int jungleRatio){
        super(width,height,jungleRatio);
    }



    @Override
    public boolean place(Animal animal){
        if(super.isOccupied(animal.position)){
            throw new IllegalArgumentException(animal.position.toString()+" is not legal place to place animal");
        }
        super.animals.put(animal.position,animal);
        return true;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(super.isOccupied(position)) return false;
        return true;
    }

    public boolean placeGrass(Grass grass) {
        if(!this.isOccupiedGrass(grass.position)){
            this.grasses.put(grass.position,grass);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {

        if(super.isOccupied(position)) return true;
        return isOccupiedGrass(position);
    }

    public boolean isOccupiedGrass(Vector2d position){
        if(this.grasses.containsKey(position))return true;
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object=super.objectAt(position);
        if(object!=null) return object;

        if(this.grasses.containsKey(position))return this.grasses.get(position);
        return null;
    }
}
