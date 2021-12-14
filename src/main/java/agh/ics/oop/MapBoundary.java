package agh.ics.oop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MapBoundary {
    public Vector2d lowerCorner,upperCorner,jnglLowCorner,jnglUppCorner;

    public MapBoundary(int width,int height,int jungleRatio){
        int jnglWidth=(int) Math.floor(width*(jungleRatio/100));
        int jnglHeight=(int) Math.floor(height*(jungleRatio/100));

        this.lowerCorner=new Vector2d(0,0);
        this.upperCorner=new Vector2d(width-1,height-1);
        this.jnglLowCorner=new Vector2d((int) Math.floor((width-jnglWidth)/2),(int) Math.floor((height-jnglHeight)/2));
        this.jnglUppCorner=new Vector2d((int) Math.floor((width-jnglWidth)/2)+jnglWidth,(int) Math.floor((height-jnglHeight)/2)+jnglHeight);

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
