package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class NoBoundariesMap extends AbstractWorldMap{

    public NoBoundariesMap(int width,int height,int jungleWidth,int jungleHeight){
        super(width,height,jungleWidth,jungleHeight);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
            if(position.x>=super.width)
                position.x=0;
            if(position.x<0)
                position.x=width-1;
            if(position.y>=super.height)
                position.y=0;
            if(position.y<0)
                position.y=height-1;
            return true;
    }




}
