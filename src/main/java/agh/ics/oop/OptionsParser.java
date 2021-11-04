package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {

    public MoveDirection[] parse(String[] strings){

//ZAPYTA O SWITCH CASE W FUNKCJI MAP
        int len= strings.length;
        MoveDirection[] directions= new MoveDirection[len];

        for (int i=0;i<len;i++) {
            switch (strings[i]){
                case "f":
                case "forward":
                    directions[i]=MoveDirection.FORWARD;
                    break;
                    case "b":
                case "backward":
                    directions[i]=MoveDirection.BACKWARD;
                    break;
                    case "r":
                case "right":
                    directions[i]=MoveDirection.RIGHT;
                    break;
                    case "l":
                case "left":
                    directions[i]=MoveDirection.LEFT;
                    break;
            }
        }
        return directions;
    }

}
