package agh.ics.oop;



public class BoundaryMap extends AbstractWorldMap{

    public BoundaryMap(int width,int height,int jungleWidth,int jungleHeight,boolean magic){
        super(width,height,jungleWidth,jungleHeight,magic);
    }




    @Override
    public boolean canMoveTo(Vector2d position) {
        if ((position.x<this.width&&position.x>=0)&&(position.y<this.height&&position.y>=0)){
                return true;
        }
        return false;
    }


}