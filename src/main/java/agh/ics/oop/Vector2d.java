package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public int x;
    public int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.x,this.y);
    }

    public String toString(){
        return "("+this.x+","+this.y+")";
    }

    public boolean precedes(Vector2d other){
        if (other.x>=this.x && other.y>=this.y){
            return true;
        }else{
            return false;
        }
    }

    public boolean follows(Vector2d other){
        if (other.x<=this.x && other.y<=this.y){
            return true;
        }else{
            return false;
        }
    }

    public Vector2d upperRight(Vector2d other){
        int preX=this.x;
        int preY=this.y;

        if(this.x<other.x){
            preX= other.x;
        }
        if(this.y<other.y){
            preY=other.y;
        }
        return new Vector2d(preX,preY);
    }


    public Vector2d lowerLeft(Vector2d other){
        int preX=this.x;
        int preY=this.y;

        if(this.x>other.x){
            preX= other.x;
        }
        if(this.y>other.y){
            preY=other.y;
        }
        return new Vector2d(preX,preY);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x,this.y+other.y);
    }

    public Vector2d substract(Vector2d other){
        return new Vector2d(this.x-other.x,this.y-other.y);
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        if (this.x==that.x && this.y==that.y){
            return true;
        }else{
            return false;
        }
    }

    public Vector2d opposite(){
        return new Vector2d(this.y,this.x);
    }
}
