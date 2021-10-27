package agh.ics.oop;

public class Vector2d {
    public int x;
    public int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "("+x+","+y+")";
    }

    public boolean precedes(Vector2d other){
        if (other.x>=x && other.y>=y){
            return true;
        }else{
            return false;
        }
    }

    public boolean follows(Vector2d other){
        if (other.x<=x && other.y<=y){
            return true;
        }else{
            return false;
        }
    }

    public Vector2d upperRight(Vector2d other){
        int preX=x;
        int preY=y;

        if(x<other.x){
            preX= other.x;
        }
        if(y<other.y){
            preY=other.y;
        }
        return new Vector2d(preX,preY);
    }


    public Vector2d lowerLeft(Vector2d other){
        int preX=x;
        int preY=y;

        if(x>other.x){
            preX= other.x;
        }
        if(y>other.y){
            preY=other.y;
        }
        return new Vector2d(preX,preY);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(x+other.x,y+other.y);
    }

    public Vector2d substract(Vector2d other){
        return new Vector2d(x-other.x,y-other.y);
    }
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        if (x==that.x && y==that.y){
            return true;
        }else{
            return false;
        }
    }

    public Vector2d opposite(){
        return new Vector2d(y,x);
    }
}
