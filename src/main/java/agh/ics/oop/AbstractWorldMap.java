package agh.ics.oop;

import java.util.ArrayList;

abstract class AbstractWorldMap implements IWorldMap {
    protected ArrayList<Animal> animals = new ArrayList<>();
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    public String toString(){
        MapVisualizer mapVisualizer=new MapVisualizer(this);
        return mapVisualizer.draw(this.lowerLeft,this.upperRight);

    }



    public boolean place(Animal animal){
        if(this.isOccupied(animal.initialPosition)) return false;

        this.animals.add(animal);
        this.upperRight=this.upperRight.upperRight(animal.initialPosition);
        this.lowerLeft=this.lowerLeft.lowerLeft(animal.initialPosition);
        return true;

    }


    public boolean isOccupied(Vector2d position) {

        for (Animal animal:this.animals) {
            if (animal.initialPosition.x==position.x&&animal.initialPosition.y==position.y){
                return true;
            }
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        for (Animal animal:this.animals) {
            if (animal.initialPosition.x==position.x&&animal.initialPosition.y==position.y){
                return animal;
            }
        }
        return null;
    }


}