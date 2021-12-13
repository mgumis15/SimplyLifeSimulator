package agh.ics.oop;

public class Grass implements IMapElement{

    public Vector2d position;
    public Grass(Vector2d position){
        this.position=position;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String toString(){
        return "*";
    }
    public String getUrl(){
        return "src/main/resources/grass.png";
    }
}
