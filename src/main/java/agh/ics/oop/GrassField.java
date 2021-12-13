package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class GrassField extends AbstractWorldMap{

    private int grassN;
    LinkedHashMap<Vector2d, Grass> grasses = new LinkedHashMap<>();


    public GrassField(int grassN){
        this.grassN=grassN;
        while (this.grassN>0){
            int yR=(int)Math.floor(Math.random()*(Math.sqrt(grassN*10)));
            int xR=(int)Math.floor(Math.random()*(Math.sqrt(grassN*10)));
            Vector2d newGrassPosition=new Vector2d(xR,yR);
                Grass grass=new Grass(newGrassPosition);
                if (this.placeGrass(grass)){
                    this.grassN--;
                }
        }
        this.grassN=grassN;
    }

    @Override
    public boolean place(Animal animal){
        if(super.isOccupied(animal.position)){
            throw new IllegalArgumentException(animal.position.toString()+" is not legal place to place animal");
        }
        super.animals.put(animal.position,animal);
        super.mapBoundary.addVector(animal.position);
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
            super.mapBoundary.addVector(grass.position);
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
