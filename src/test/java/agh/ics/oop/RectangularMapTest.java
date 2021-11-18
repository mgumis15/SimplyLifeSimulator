package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    public void canMoveTo(){

        RectangularMap map = new RectangularMap(5, 5);
        Vector2d v1=new Vector2d(0,0);
        Vector2d v2=new Vector2d(0,0);
        Vector2d v3=new Vector2d(0,-1);
        Animal animal = new Animal(map,v1);
        assertTrue(map.canMoveTo(v1));
        map.place(animal);
        assertFalse(map.canMoveTo(v2));
        assertFalse(map.canMoveTo(v3));


    }

    @Test
    public void isOccupied(){

        RectangularMap map = new RectangularMap(5, 5);
        Vector2d v1=new Vector2d(0,0);
        Vector2d v2=new Vector2d(0,0);
        Vector2d v3=new Vector2d(3,3);
        Animal animal = new Animal(map,v1);
        map.place(animal);
        assertTrue(map.isOccupied(v2));
        assertFalse(map.isOccupied(v3));
    }

    @Test
    public void ObjectAt(){

        RectangularMap map = new RectangularMap(5, 5);
        Vector2d v1=new Vector2d(0,0);
        Vector2d v2=new Vector2d(2,0);
        Animal animal = new Animal(map,v1);
        map.place(animal);
        assertEquals(map.objectAt(v1),animal);
        assertEquals(map.objectAt(v2),null);
    }

}
