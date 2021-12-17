package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver {
    protected LinkedHashMap<Vector2d, ArrayList<Animal>> animals = new LinkedHashMap<>();
    public ArrayList<Animal> animalsArr=new ArrayList<Animal>();
    protected LinkedHashMap<Vector2d, Grass> grasses = new LinkedHashMap<>();
    protected int width;
    protected int height;
    protected int jungleWidth;
    protected int jungleHeight;
    protected int plantEnergy;
    protected int moveEnergy;
    protected int startEnergy;
    public MapBoundary mapBoundary;

    public AbstractWorldMap(int width,int height,int jungleWidth,int jungleHeight){
        this.width=width;
        this.height=height;
        this.jungleWidth=jungleWidth;
        this.jungleHeight=jungleHeight;
        this.mapBoundary=new MapBoundary(this.width,this.height,this.jungleWidth,this.jungleHeight);
    }


    public boolean generateGrass(int grassN) {
        while( this.grasses.size()<this.width*this.height && grassN>0){
            int yR=(int)Math.floor(Math.random()*(this.height-1));
            int xR=(int)Math.floor(Math.random()*(this.width-1));
            Vector2d newGrassPosition=new Vector2d(xR,yR);
            Grass grass=new Grass(newGrassPosition);
            if (this.placeGrass(grass)){
                grassN--;
            }
        }
        if(this.grasses.size()==this.width*this.height && grassN>0){
            return false;
        }
        return true;
    }
    public boolean placeGrass(Grass grass) {
        if(!this.isOccupied(grass.position)){
            this.grasses.put(grass.position,grass);
            return true;
        }
        return false;
    }



//    public boolean generateAnimals(int animalsN) {
//        while( this.animalsArr.size()<this.width*this.height && animalsN>0){
//            int yR=(int)Math.floor(Math.random()*(this.height-1));
//            int xR=(int)Math.floor(Math.random()*(this.width-1));
//            Vector2d newAnimalPosition=new Vector2d(xR,yR);
//            Animal animal=new Animal(this,newAnimalPosition,this.startEnergy);
//            if (this.place(animal)){
//                animalsN--;
//            }
//        }
//        if(this.animalsArr.size()==this.width*this.height && animalsN>0){
//            return false;
//        }
//        return true;
//    }

//    public boolean place(Animal animal){
//            if(this.isOccupied(animal.position)){
//                return false;
//            }
//            this.animals.get(animal.position).add(animal);
//            return true;
//    }
//

//    public boolean isOccupied(Vector2d position) {
//        if(this.animals.containsKey(position))return true;
//        if(this.grasses.containsKey(position))return true;
//        return false;
//    }

//public Object objectAt(Vector2d position) {
//    ArrayList<Animal> animalsOn=this.animalsAt(position);
//
//    if(animalsOn!=null) {
//        if(animalsOn.size()>0){
//            Animal animal=animalsOn.get(0);
//            for (Animal checkAnimal:animalsOn) {
//                if(checkAnimal.energy>animal.energy){
//                    animal=checkAnimal;
//                }
//            }
//            return animal;
//        }
//    }
//    if(this.grasses.containsKey(position))return this.grasses.get(position);
//    return null;
//}

//public ArrayList<Animal> animalsAt(Vector2d position) {
//    return animals.get(position);
//}






    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
//            Animal curr=animals.get(oldPosition);
//            animals.get(newPosition);
//            animals.remove(oldPosition);
    }


    public void setPlantEnergy(int grassEnergy) {
        this.plantEnergy = grassEnergy;
    }

    public void setMoveEnergy(int moveEnergy) {
        this.moveEnergy = moveEnergy;
    }

    public void setStartEnergy(int startEnergy) {this.startEnergy = startEnergy;}

    public MapBoundary getMapBoundary() {
        return mapBoundary;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}