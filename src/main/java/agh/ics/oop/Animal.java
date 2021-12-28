package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Animal {

    public Vector2d position;
    public MapDirection direction;
    public int energy;
    public ArrayList<Integer> genes;
    public int lifeSpan=1;
    private IWorldMap map;
    private int deathDay=-1;
    public int childs=0;
    public boolean tracked=false;
    public Animal(IWorldMap map, Vector2d position, int energy){

        this.map=map;
        this.position=position;
        this.energy=energy;
        this.direction=MapDirection.NORTH;
        this.genes=new ArrayList<Integer>();
        int randomDirection=new Random().nextInt(8);
        for (int i = 0; i < randomDirection; i++) {

        this.direction=this.direction.next();
        }

        for (int i = 0; i < 32; i++) {
            this.genes.add(new Random().nextInt(8));

        }
        Collections.sort(this.genes);
    }


    public int getEnergy() {
        return energy;
    }

    public ArrayList<Integer> getGenes() {
        return genes;
    }

    private boolean isAt(Vector2d position2){
            return this.position.equals(position2);
    }

    public boolean move(){
        this.lifeSpan+=1;
        int ind=new Random().nextInt(32);
        int direct=this.genes.get(ind);
        if(direct==0){
            Vector2d nextIT=this.position.add(this.direction.toUnitVector());
            if (this.map.canMoveTo(nextIT)){
                this.position=nextIT;
                return true;
            };
        }else if(direct==4){
            Vector2d prev=this.position.substract(this.direction.toUnitVector());
            if (this.map.canMoveTo(prev)){

                this.position=prev;
                return true;
            };
        }else{
            for (int i = 0; i <direct ; i++) {
                this.direction=this.direction.next();
            }

        }
        return false;
    }

    public void setDeathDay(int deathDay) {
        this.deathDay = deathDay;
    }

    public int getDeathDay() {
        return deathDay;
    }

}


