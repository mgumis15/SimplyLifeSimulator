package agh.ics.oop;

import java.util.ArrayList;

public class World {



    public static void main(String[] args) {

        System.out.println("system wystartował");
        String[] strings={"f", "b", "r", "l"};
        MoveDirection[] directions = new OptionsParser().parse(strings);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();
        System.out.println(map.toString());
        System.out.println("system zakończył działanie");
    }

}
