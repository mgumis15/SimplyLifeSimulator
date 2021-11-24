package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;

public class GrassField implements IWorldMap{

    private int grassN;
    private Vector2d lowerLeft;
    private Vector2d upperRight;
    private ArrayList<Grass> grasses;
    private ArrayList<Animal> animals;

    public GrassField(int grassN){
        this.grassN=grassN;
        this.grasses=new ArrayList<>();
        this.animals=new ArrayList<>();
        while (this.grassN>0){
        int yR=(int)Math.floor(Math.random()*(Math.sqrt(grassN*10)));
        int xR=(int)Math.floor(Math.random()*(Math.sqrt(grassN*10)));
        Vector2d newGrassPosition=new Vector2d(xR,yR);
            Grass grass=new Grass(newGrassPosition);
            if (this.placeGrass(grass)){
                if(this.grassN==grassN){
                    this.lowerLeft=new Vector2d(xR,yR);
                    this.upperRight=new Vector2d(xR,yR);
                }else{

                    this.upperRight=this.upperRight.upperRight(newGrassPosition);
                    this.lowerLeft=this.lowerLeft.lowerLeft(newGrassPosition);
                }
                this.grassN--;
            }
        }
        this.grassN=grassN;
    }


    public String toString(){

        System.out.println(this.upperRight.toString());
        System.out.println(this.lowerLeft.toString());
        MapVisualizer mapVisualizer=new MapVisualizer(this);
        return mapVisualizer.draw(this.lowerLeft,this.upperRight);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        for (Animal animal:this.animals) {
            if (animal.initialPosition.x==position.x&&animal.initialPosition.y==position.y){
                return false;
            }
        }
        this.upperRight=this.upperRight.upperRight(position);
        this.lowerLeft=this.lowerLeft.lowerLeft(position);
        return true;
    }


    @Override
    public boolean place(Animal animal){
        for (Animal placedAnimal:this.animals) {
            if (placedAnimal.initialPosition.x==animal.initialPosition.x
                    &&placedAnimal.initialPosition.y==animal.initialPosition.y){
                return false;
            }
        }

            this.animals.add(animal);
            this.upperRight=this.upperRight.upperRight(animal.initialPosition);
            this.lowerLeft=this.lowerLeft.lowerLeft(animal.initialPosition);
            return true;

    }

    public boolean placeGrass(Grass grass) {
        if(!this.isOccupiedGrass(grass.position)){
            this.grasses.add(grass);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal:this.animals) {
            if (animal.initialPosition.x==position.x&&animal.initialPosition.y==position.y){
                return true;
            }
        }
        return isOccupiedGrass(position);
    }


    public boolean isOccupiedGrass(Vector2d position){
        for (Grass grass:this.grasses) {
            if (grass.position.x==position.x&&grass.position.y==position.y){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal:this.animals) {
            if (animal.initialPosition.x==position.x&&animal.initialPosition.y==position.y){
                return animal;
            }
        }
        for (Grass grass:this.grasses) {
            if (grass.position.x==position.x&&grass.position.y==position.y){
                return grass;
            }
        }
        return null;
    }
}
