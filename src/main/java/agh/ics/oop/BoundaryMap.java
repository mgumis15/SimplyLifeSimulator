package agh.ics.oop;

import java.util.*;

public class BoundaryMap extends AbstractWorldMap{

    public BoundaryMap(int width, int height, int jungleRatio){
        super(width,height,jungleRatio);
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


}