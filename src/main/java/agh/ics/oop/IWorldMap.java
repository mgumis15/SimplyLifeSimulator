package agh.ics.oop;


public interface IWorldMap {

    boolean canMoveTo(Vector2d position);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d currentPosition);

    boolean generateStartGrasses(int grassOnStart);

    MapBoundary getMapBoundary();

    int getHeight();

    int getWidth();

    boolean generateDailyGrasses();

    void moveAllAnimals();

    boolean generateStartAnimals(int animalsOnStart);

    void newDayRise();
}