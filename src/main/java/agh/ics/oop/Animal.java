package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection direction=MapDirection.NORTH;
    private Vector2d upperCorner = new Vector2d(4,4);
    private Vector2d lowerCorner = new Vector2d(0,0);

    public String toString(){
        return "orientacja: "+this.direction.toString()+", pozycja: "+this.position.toString();
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
                if ((nextIT.precedes(this.upperCorner) && nextIT.follows(this.lowerCorner))
                ||(nextIT.precedes(this.lowerCorner) && nextIT.follows(this.upperCorner))
                ){
                    this.position=nextIT;
                }
                return;


            case BACKWARD:
                Vector2d prev=this.position.substract(this.direction.toUnitVector());
                if ((prev.precedes(this.upperCorner) && prev.follows(this.lowerCorner))
                        || (prev.precedes(this.lowerCorner) && prev.follows(this.upperCorner))){
                    this.position=prev;
                };
                break;
            }
        }

}


