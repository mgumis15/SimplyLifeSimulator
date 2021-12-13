package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationEngineTest {
    @Test
    public void run(){
        String[] strings={"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(strings);
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4),new Vector2d(2,2) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        assertEquals(engine.animals.size(),2);
        engine.run();

        assertEquals(engine.animals.get(0).position,new Vector2d(2,0));
        assertEquals(engine.animals.get(1).position,new Vector2d(3,4));

    }
}
