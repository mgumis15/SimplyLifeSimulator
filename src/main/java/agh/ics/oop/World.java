package agh.ics.oop;

public class World {



    public static void main(String[] args) {


        System.out.println("system wystartował");
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine enegine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println("system zakończył działanie");
    }

}
