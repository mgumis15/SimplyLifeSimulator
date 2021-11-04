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
                System.out.println("Jestem w Right");
                this.direction=this.direction.next();
                System.out.println(this.direction.toString());
                break;
            case LEFT:
                this.direction=this.direction.previous();
                break;

            case FORWARD:
                System.out.println("Jestem w FORWARD "+this.position.toString() );
                Vector2d nextIT=this.position.add(this.direction.toUnitVector());
                System.out.println(nextIT.toString());
                if ((nextIT.precedes(this.upperCorner) && nextIT.follows(this.lowerCorner))
                ||(nextIT.precedes(this.lowerCorner) && nextIT.follows(this.upperCorner))
                ){
                    System.out.println("Przypisuje wartość");
                    this.position=nextIT;
                    System.out.println("XD: "+this.position.toString());
                }
                return;


            case BACKWARD:
                System.out.println("Jestem w BACKWARD "+this.position.toString() );
                Vector2d prev=this.position.substract(this.direction.toUnitVector());
                if ((prev.precedes(this.upperCorner) && prev.follows(this.lowerCorner))
                        || (prev.precedes(this.lowerCorner) && prev.follows(this.upperCorner))){
                    this.position=prev;
                };
                break;
            }
        }

}


