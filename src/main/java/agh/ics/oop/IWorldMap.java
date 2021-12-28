package agh.ics.oop;


import java.util.ArrayList;

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

    boolean newDayRise();

    int getStartEnergy();

    Integer getDays();

    Integer getAnimalsQuant();

    Integer getGrassQuant();

    Integer getEnergyMean();

    Integer getAnimalsLifespanMean();

    void choseAnimal(Animal animal);

    Animal getChosenAnimal();

    int getMagic();

    ArrayList<Integer> getGensDominant();

    Double getAverageChilds();

    int getChosenChildren();

    int getChosenDescendants();
}