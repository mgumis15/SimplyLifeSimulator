package agh.ics.oop;

import java.util.ArrayList;

public class GrassField implements IWorldMap{

    private int grassN;
    private int[] corners={-1,-1,-1,-1};
    private ArrayList<Grass> mapGrass;
    private ArrayList<Animal> mapAnimals;
    public GrassField(int grassN){
        this.grassN=grassN;
        this.mapGrass=new ArrayList<>();
        this.mapAnimals=new ArrayList<>();
        while (this.grassN>0){
        int yR=(int)Math.floor(Math.random()*(Math.sqrt(grassN*10)));
        int xR=(int)Math.floor(Math.random()*(Math.sqrt(grassN*10)));
            Grass grass=new Grass(new Vector2d(xR,yR));
            if (this.placeGrass(grass)){
                if(corners[0]==-1){
                    corners[0]=xR;
                    corners[1]=yR;
                    corners[2]=xR;
                    corners[3]=yR;
                }else{
                    if(corners[0]>xR) corners[0]=xR;
                    if(corners[1]>yR) corners[1]=yR;
                    if(corners[2]<xR) corners[2]=xR;
                    if(corners[3]<yR) corners[3]=yR;
                }
                this.grassN--;
            }
        }
        this.grassN=grassN;
    }


    public String toString(){
            int lowX,lowY,upX,upY;
            lowX=lowY=0;
            upX=
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
    if(position.x>=0&&position.y>=0) {
        if (!this.isOccupied(position)) {
            return true;
        }
    }
        return false;
    }


    @Override
    public boolean place(Animal animal){
        if(this.canMoveTo(animal.initialPosition)){
            this.mapAnimals.add(animal);
            return true;
        }
        return false;

    }
    public boolean placeGrass(Grass grass) {
        if(this.isOccupiedGrass(grass.position)){
            this.mapGrass.add(grass);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal:this.mapAnimals) {
            if (animal.initialPosition.x==position.x&&animal.initialPosition.y==position.y){
                return true;
            }
        }
        return false;
    }
    public boolean isOccupiedGrass(Vector2d position){
        for (Grass grass:this.mapGrass) {
            if (grass.position.x==position.x&&grass.position.y==position.y){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal:this.mapAnimals) {
            if (animal.initialPosition.x==position.x&&animal.initialPosition.y==position.y){
                return animal;
            }
        }
        for (Grass grass:this.mapGrass) {
            if (grass.position.x==position.x&&grass.position.y==position.y){
                return grass;
            }
        }
        return null;
    }
}
