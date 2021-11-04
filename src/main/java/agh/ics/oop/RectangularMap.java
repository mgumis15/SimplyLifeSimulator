package agh.ics.oop;

public class RectangularMap implements IWorldMap{

    public int width;
    public int height;

    public RectangularMap(int width, int height){
        this.width=width;
        this.height=height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if ((position.x<=this.width&&position.x>=0)&&(position.y<=this.height&&position.y>=0)&&!this.isOccupied(position)){
            return true;
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if(this.canMoveTo(animal.initialPosition)){

        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {

        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
