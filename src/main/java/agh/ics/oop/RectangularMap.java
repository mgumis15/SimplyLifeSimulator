package agh.ics.oop;

import java.util.*;

public class RectangularMap implements IWorldMap{

    public int width;
    public int height;
    public ArrayList<ArrayList<Animal>> map;
    public Vector2d lowerLeft;
    public Vector2d upperRight;


    public RectangularMap(int width, int height){
        this.width=width;
        this.height=height;
        this.map=new ArrayList<ArrayList<Animal>>(height);
        this.lowerLeft=new Vector2d(0,0);
        this.upperRight=new Vector2d(width-1,height-1);

        for (int i=0; i<height;i++){
            this.map.add(new ArrayList<>(width));
        }
    }



    public String toString(){
        MapVisualizer mapVisualizer=new MapVisualizer(this);

        System.out.println("A2");
        return mapVisualizer.draw(this.lowerLeft,this.upperRight);

    }




    @Override
    public boolean canMoveTo(Vector2d position) {
        if ((position.x<=this.width&&position.x>=0)&&(position.y<=this.height&&position.y>=0)){
            if(!this.isOccupied(position)){
            return true;
            }
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if(this.canMoveTo(animal.initialPosition)){
                this.map.get(animal.initialPosition.y).set(animal.initialPosition.x,animal);
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        System.out.println(position.toString());
        System.out.println(this.map.get(0).get(0).toString());
            if (this.map.get(position.y).get(position.x)!=null){

                return true;
            }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.map.get(position.y).get(position.x);
    }
}
