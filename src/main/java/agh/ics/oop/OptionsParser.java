package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {

    public MoveDirection[] parse(String[] strings){


        int len= strings.length;
        int x=0;

        for (int i=0;i<len;i++) {

                switch (strings[i]) {
                    case "f":
                    case "forward":
                    case "b":
                    case "backward":
                    case "r":
                    case "right":
                    case "l":
                    case "left":
                        break;
                    default:
                        x = x + 1;
                        throw new IllegalArgumentException(strings[i].toString() + " is not legal move specification");
                }

        }
        MoveDirection[] output=new MoveDirection[len-x];
        int j=0;
        for (int i=0;i<len;i++) {
            switch (strings[i]){
                case "f":
                case "forward":
                    output[j]=MoveDirection.FORWARD;
                    j++;
                    break;
                case "b":
                case "backward":
                    output[j]=MoveDirection.BACKWARD;
                    j++;
                    break;
                case "r":
                case "right":
                    output[j]=MoveDirection.RIGHT;
                    j++;
                    break;
                case "l":
                case "left":
                    output[j]=MoveDirection.LEFT;
                    j++;
                    break;
                default:
                    break;
            }

        }
        return output;
    }

}
