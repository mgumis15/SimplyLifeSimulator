package agh.ics.oop;

import java.util.ArrayList;

public class Animal implements IMapElement{

    public Vector2d position;
    public IWorldMap map;
    public MapDirection direction;
    private ArrayList<IPositionChangeObserver>  observers;
    public Animal(IWorldMap map, Vector2d position){
        this.map=map;
        this.observers=new ArrayList<>();
        this.addObserver((IPositionChangeObserver) map);
        this.position=position;
        this.direction=MapDirection.NORTH;
    }



    public String toString(){
        String odp=switch (this.direction){
            case NORTH -> "^";
            case EAST -> ">";
            case WEST -> "<";
            case SOUTH -> "v";
            default -> "";
        };
        return odp;
    }

    public String getUrl(){
        String odp=switch (this.direction){
            case NORTH -> "src/main/resources/up.png";
            case EAST -> "src/main/resources/left.png";
            case WEST -> "src/main/resources/right.png";
            case SOUTH -> "src/main/resources/down.png";
            default -> "";
        };
        return odp;
    }

    private boolean isAt(Vector2d position2){
            return this.position.equals(position2);
    }

    public void move(MoveDirection direct){
        switch(direct) {
            case RIGHT:
                this.direction=this.direction.next();
                break;
            case LEFT:
                this.direction=this.direction.previous();
                break;

            case FORWARD:
                Vector2d nextIT=this.position.add(this.direction.toUnitVector());
                if (this.map.canMoveTo(nextIT)){
                    this.positionChanged(this.position,nextIT);
                    this.position=nextIT;
                }
                return;

            case BACKWARD:
                Vector2d prev=this.position.substract(this.direction.toUnitVector());
                if (this.map.canMoveTo(prev)){
                    this.positionChanged(this.position,prev);
                    this.position=prev;

                };
                return;
            }
        }
    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer:observers) {
            observer.positionChanged(oldPosition,newPosition);
        }
    }
}


