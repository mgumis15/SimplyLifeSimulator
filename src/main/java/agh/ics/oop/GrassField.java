package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;

public class GrassField extends AbstractWorldMap{

    private int grassN;
    private ArrayList<Grass> grasses;

    public GrassField(int grassN){
        this.grassN=grassN;
        this.grasses=new ArrayList<>();
        while (this.grassN>0){
        int yR=(int)Math.floor(Math.random()*(Math.sqrt(grassN*10)));
        int xR=(int)Math.floor(Math.random()*(Math.sqrt(grassN*10)));
        Vector2d newGrassPosition=new Vector2d(xR,yR);
            Grass grass=new Grass(newGrassPosition);
            if (this.placeGrass(grass)){
                if(this.grassN==grassN){
                    super.lowerLeft=new Vector2d(xR,yR);
                    super.upperRight=new Vector2d(xR,yR);
                }else{

                    super.upperRight=super.upperRight.upperRight(newGrassPosition);
                    super.lowerLeft=super.lowerLeft.lowerLeft(newGrassPosition);
                }
                this.grassN--;
            }
        }
        this.grassN=grassN;
    }



    @Override
    public boolean canMoveTo(Vector2d position) {
        if(super.isOccupied(position)) return false;

        super.upperRight=super.upperRight.upperRight(position);
        super.lowerLeft=super.lowerLeft.lowerLeft(position);
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
        if(super.isOccupied(position)) return true;
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
        Object object=super.objectAt(position);
        if(object!=null) return object;

        for (Grass grass:this.grasses) {
            if (grass.position.x==position.x&&grass.position.y==position.y){
                return grass;
            }
        }
        return null;
    }
}
