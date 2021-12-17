package agh.ics.oop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MapBoundary {
    public Vector2d lowerCorner,upperCorner,jnglLowCorner,jnglUppCorner;

    public MapBoundary(int width,int height,int jungleWidth,int jungleHeight){

        this.lowerCorner=new Vector2d(0,0);
        this.upperCorner=new Vector2d(width-1,height-1);
        this.jnglLowCorner=new Vector2d((int) Math.floor((width-jungleWidth)/2),(int) Math.floor((height-jungleHeight)/2));
        this.jnglUppCorner=new Vector2d((int) Math.floor((width-jungleWidth)/2)+jungleWidth,(int) Math.floor((height-jungleHeight)/2)+jungleHeight);

    }


    public Vector2d getUpperCorner() {
        return upperCorner;
    }
    public Vector2d getLowerCorner() {
        return lowerCorner;
    }

    public Vector2d getJnglUppCorner() {
        return jnglUppCorner;
    }
    public Vector2d getJnglLowCorner() {
        return jnglLowCorner;
    }
}
