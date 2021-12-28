package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoundaryMapTest {
    @Test
    public void canMoveTo(){

        BoundaryMap map = new BoundaryMap(15,15,5,5,false);
        Vector2d v1=new Vector2d(0,0);
        Vector2d v2=new Vector2d(0,-1);
        assertTrue(map.canMoveTo(v1));
        assertFalse(map.canMoveTo(v2));


    }

    @Test
    public void isOccupied(){

        BoundaryMap map = new BoundaryMap(15,15,5,5,false);
        Vector2d v1=new Vector2d(0,0);
        Vector2d v2=new Vector2d(2,0);
        Animal animal = new Animal(map,v1,10);
        map.placeAnimal(animal);
        assertTrue(map.isOccupied(v1));
        assertFalse(map.isOccupied(v2));
    }

    @Test
    public void ObjectAt(){

        BoundaryMap map = new BoundaryMap(15,15,5,5,false);
        Vector2d v1=new Vector2d(0,0);
        Vector2d v2=new Vector2d(2,0);
        Animal animal = new Animal(map,v1,10);
        map.placeAnimal(animal);
        assertEquals(map.objectAt(v1),animal);
        assertEquals(map.objectAt(v2),null);
    }

}
