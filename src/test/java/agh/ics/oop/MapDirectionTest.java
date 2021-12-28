package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {

    @Test
    public void next(){
        assertEquals(MapDirection.NORTH.next(), MapDirection.NORTH_EAST);
        assertEquals(MapDirection.NORTH_EAST.next(), MapDirection.EAST);
        assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH_EAST);
        assertEquals(MapDirection.SOUTH_EAST.next(), MapDirection.SOUTH);
        assertEquals(MapDirection.SOUTH.next(), MapDirection.SOUTH_WEST);
        assertEquals(MapDirection.SOUTH_WEST.next(), MapDirection.WEST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTH_WEST);
        assertEquals(MapDirection.NORTH_WEST.next(), MapDirection.NORTH);
    }




}
