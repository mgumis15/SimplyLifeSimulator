package agh.ics.oop;

import java.util.*;

public class RectangularMap implements IWorldMap{

    public int width;
    public int height;
    public ArrayList<Animal> map;
    public Vector2d lowerLeft;
    public Vector2d upperRight;


    public RectangularMap(int width, int height){
        this.width=width;
        this.height=height;
        this.map=new ArrayList<Animal>();
        this.lowerLeft=new Vector2d(0,0);
        this.upperRight=new Vector2d(width-1,height-1);
    }



    public String toString(){
        MapVisualizer mapVisualizer=new MapVisualizer(this);
        return mapVisualizer.draw(this.lowerLeft,this.upperRight);

    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if ((position.x<this.width&&position.x>=0)&&(position.y<this.height&&position.y>=0)){
            if(!this.isOccupied(position)){
            return true;
            }
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if(this.canMoveTo(animal.initialPosition)){
                this.map.add(animal);
                return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {

        for (Animal animal:this.map) {
            if (animal.initialPosition.x==position.x&&animal.initialPosition.y==position.y){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {

        for (Animal animal:this.map) {
            if (animal.initialPosition.x==position.x&&animal.initialPosition.y==position.y){
                return animal;
            }
        }
        return null;
    }
}
