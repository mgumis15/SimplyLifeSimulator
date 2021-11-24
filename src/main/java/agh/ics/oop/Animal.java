package agh.ics.oop;

public class Animal{

    public Vector2d initialPosition;
    public IWorldMap map;
    public MapDirection direction;
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map=map;
        this.initialPosition=initialPosition;
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

    private boolean isAt(Vector2d position2){
            return this.initialPosition.equals(position2);
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
                Vector2d nextIT=this.initialPosition.add(this.direction.toUnitVector());
                if (this.map.canMoveTo(nextIT)){
                    this.initialPosition=nextIT;
                    this.map.place(this);
                }
                return;

            case BACKWARD:
                Vector2d prev=this.initialPosition.substract(this.direction.toUnitVector());
                if (this.map.canMoveTo(prev)){
                    this.initialPosition=prev;
                    this.map.place(this);

                };
                return;
            }
        }

}


