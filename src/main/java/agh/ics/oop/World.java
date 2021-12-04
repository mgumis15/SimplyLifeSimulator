package agh.ics.oop;

import java.util.ArrayList;

public class World {



    public static void main(String[] args) {
try {
    System.out.println("system wystartował");
    String[] strings = {"f", "b", "r", "l", "f", "f", "fow", "r", "rrr", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
    MoveDirection[] directions = new OptionsParser().parse(strings);
//     RectangularMap map = new RectangularMap(10, 5);
    GrassField map = new GrassField(10);
    Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(2, 2)};
    IEngine engine = new SimulationEngine(directions, map, positions);
    System.out.println(map.toString());
    engine.run();
    System.out.println("system zakończył działanie");
}catch(IllegalArgumentException ex){
    System.out.println(ex.toString());

}
}

}
