package agh.ics.oop;

import java.util.*;

public class RectangularMap extends AbstractWorldMap{

    public int width;
    public int height;


    public RectangularMap(int width, int height){
        this.width=width;
        this.height=height;
        super.lowerLeft=new Vector2d(0,0);
        super.upperRight=new Vector2d(width-1,height-1);
    }




    @Override
    public boolean canMoveTo(Vector2d position) {
        if ((position.x<this.width&&position.x>=0)&&(position.y<this.height&&position.y>=0)){
            if(!super.isOccupied(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if(this.canMoveTo(animal.initialPosition)){

            super.animals.put(animal.initialPosition,animal);
            Vector2d last=animal.initialPosition.substract(animal.direction.toUnitVector());
            super.animals.remove(last);
            return true;
        }
        return false;
    }

}