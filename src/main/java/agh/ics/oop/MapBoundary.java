package agh.ics.oop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver{
    protected SortedSet<Vector2d> ssX=new TreeSet<Vector2d>(new Comparator<Vector2d>() {
        @Override
        public int compare(Vector2d o1, Vector2d o2) {
            if(Integer.compare(o1.x,o2.x)==0){
                if (Integer.compare(o1.y,o2.y)==0){
                    if(Objects.equals(o1,o2)) return 0;
                    else return -1;
                }else return Integer.compare(o1.y,o2.y);
            }else return Integer.compare(o1.x,o2.x);
        }
    });
    protected SortedSet<Vector2d> ssY=new TreeSet<Vector2d>(new Comparator<Vector2d>() {

        @Override
        public int compare(Vector2d o1, Vector2d o2) {
            if(Integer.compare(o1.y,o2.y)==0){
                if (Integer.compare(o1.x,o2.x)==0){
                    if(o1.equals(o2)) return 0;
                    else return -1;
                }else return Integer.compare(o1.x,o2.x);
            }else return Integer.compare(o1.y,o2.y);
        }
    });
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.removeVector(oldPosition);
        this.addVector(newPosition);
    }
    public void addVector(Vector2d vect){
        this.ssX.add(vect);
        this.ssY.add(vect);
    }
    public void removeVector(Vector2d vect){
        this.ssX.remove(vect);
        this.ssY.remove(vect);
    }
    public Vector2d getLowerCorner(){
        return new Vector2d(this.ssX.first().x,this.ssY.first().y);
    }
    public Vector2d getUpperCorner(){
        return new Vector2d(this.ssX.last().x,this.ssY.last().y);
    }
}
