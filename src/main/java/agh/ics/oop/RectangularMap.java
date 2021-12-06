package agh.ics.oop;

import java.util.*;

public class RectangularMap extends AbstractWorldMap{

    public int width;
    public int height;


    public RectangularMap(int width, int height){
        this.width=width;
        this.height=height;
        Vector2d lowerCorner=new Vector2d(0,0);
        Vector2d upperCorner=new Vector2d(width-1,height-1);
        super.mapBoundary.ssX.add(lowerCorner);
        super.mapBoundary.ssX.add(upperCorner);
        super.mapBoundary.ssY.add(lowerCorner);
        super.mapBoundary.ssY.add(upperCorner);
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