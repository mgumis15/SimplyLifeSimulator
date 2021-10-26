package agh.ics.oop;

public class World {



    public static void main(String[] args) {


        System.out.println("system wystartował");
        String[] str=args;
        Direction[] direction=new Direction[str.length];
        for (int i=0;i<direction.length;i++) {
           switch (str[i]) {
                case "f" :
                    direction[i]=Direction.FORWARD;
                    break;
               case "b":
                   direction[i]=Direction.BACKWARD;
                   break;
               case "r" :
                   direction[i]=Direction.RIGHT;
                   break;
               case "l" :
                   direction[i]=Direction.LEFT;
                   break;
               default :
                   direction[i]=Direction.NONE;
                   break;
            }
        }
        System.out.println(direction);
        run(direction);


        System.out.println("system zakończył działanie");
    }

    public static void run(Direction[] direction){
        System.out.println("Start");

        for(int i=0;i<direction.length;i++){
            String message=switch(direction[i]){
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD  -> "zwierzak idzie do tyłu";
                case RIGHT  -> "wierzak skręca w prawo";
                case LEFT  -> "zwierzak skręca w lewo";
                case NONE ->"";
            };
            System.out.println(message);

        }
        System.out.println("Stop");
    }
}
