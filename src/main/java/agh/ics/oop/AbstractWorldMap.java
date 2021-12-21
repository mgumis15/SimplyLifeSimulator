package agh.ics.oop;

import java.util.*;
import java.util.stream.Collectors;

abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver {
    protected LinkedHashMap<Vector2d, ArrayList<Animal>> animalsOnPosition = new LinkedHashMap<>();
//    protected LinkedHashMap<Vector2d, SortedSet<Animal>> animalsOnPosition = new LinkedHashMap<>();
//    SortedSet<Animal> newCell=new TreeSet<Animal>(new Comparator<Animal>() {
//        @Override
//        public int compare(Animal o1, Animal o2) {
//            return Integer.compare(o1.energy, o2.energy);
//        }
//    });

    public ArrayList<Animal> animals=new ArrayList<Animal>();
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



    public void newDayRise(){
    System.out.println("New day ---------");
    this.removeDeadAnimals();
    this.moveAllAnimals();
    this.animalsEatGrass();
    this.generateDailyGrasses();
    }

    public void removeDeadAnimals(){
        Iterator<Animal> i = this.animals.iterator();
        while(i.hasNext()) {
            Animal animal=i.next();
            if(animal.getEnergy()<this.moveEnergy){
                this.animalsOnPosition.get(animal.position).remove(animal);
                if(this.animalsOnPosition.get(animal.position).size()==0){
                    this.animalsOnPosition.remove(animal.position);
                }
                i.remove();
            }
        }
    }
    public void animalsEatGrass(){
        Set<Vector2d> keys=this.animalsOnPosition.keySet();
        for (Vector2d key : keys) {
            this.animalsOnPosition.get(key).sort((o1, o2) -> Integer.compare(o2.energy, o1.energy));
            if(this.grasses.containsKey(key)){
                int count=1;
                for (int i = 1; i < this.animalsOnPosition.get(key).size(); i++) {
                    if(this.animalsOnPosition.get(key).get(i-1).energy==this.animalsOnPosition.get(key).get(i).energy)count++;
                    else break;
                }
                for (int i = 0; i < count; i++) {
                    this.animalsOnPosition.get(key).get(i).energy+=Math.floor(this.plantEnergy/count);
                }
                this.grasses.remove(key);
            }
        }
    }




    public boolean generateStartGrasses(int grassN) {
        while( this.grasses.size()<this.width*this.height && grassN>0){
            int yR=(int)Math.floor(Math.random()*(this.height));
            int xR=(int)Math.floor(Math.random()*(this.width));
            Vector2d newGrassPosition=new Vector2d(xR,yR);

            if (!this.isOccupied(newGrassPosition)){
                Grass grass=new Grass(newGrassPosition);
                this.placeGrass(grass);
                grassN--;
            }
        }
        if(this.grasses.size()==this.width*this.height && grassN>0){
            return false;
        }
        return true;
    }



    public boolean generateStartAnimals(int animalN) {
        while( this.animals.size()<this.width*this.height && animalN>0){
            int yR=(int)Math.floor(Math.random()*(this.height));
            int xR=(int)Math.floor(Math.random()*(this.width));
            Vector2d newAnimalPosition=new Vector2d(xR,yR);
            if (!this.isOccupied(newAnimalPosition)){
                Animal animal=new Animal(this,newAnimalPosition,this.startEnergy);
                this.placeAnimal(animal);
                animalN--;
            }
        }
        if(this.animals.size()==this.width*this.height && animalN>0){
            return false;
        }
        return true;
    }

    public void placeGrass(Grass grass) {
        this.grasses.put(grass.position,grass);
    }

    public void placeAnimal(Animal animal) {
        ArrayList<Animal> newCell=new ArrayList<Animal>();
        newCell.add(animal);
        this.animalsOnPosition.put(animal.position,newCell);
        this.animals.add(animal);
    }

    public boolean isOccupied(Vector2d position) {
        if(this.grasses.containsKey(position))return true;
        if(this.animalsOnPosition.containsKey(position))return true;

        return false;
    }
    public void moveAllAnimals(){
        for (Animal animal:this.animals) {
            animal.energy-=this.moveEnergy;
            Vector2d oldPosition=animal.position;
            if(animal.move()){
                this.animalsOnPosition.get(oldPosition).remove(animal);

                if(this.animalsOnPosition.get(oldPosition).size()==0){
                    this.animalsOnPosition.remove(oldPosition);
                }
                if(this.animalsOnPosition.containsKey(animal.position)){
                    this.animalsOnPosition.get(animal.position).add(animal);
                }else{
                    ArrayList<Animal> newCell=new ArrayList<Animal>();
                    newCell.add(animal);
                    this.animalsOnPosition.put(animal.position,newCell);
                }
            }
        }
    }


    public boolean generateDailyGrasses() {
        int grassN=this.width*this.height*2;
        boolean grassIn=false;
        boolean grassOut=false;
        while( grassN>0 && (!grassIn||!grassOut)){
            int yR=(int)Math.floor(Math.random()*(this.height));
            int xR=(int)Math.floor(Math.random()*(this.width));
            Vector2d newGrassPosition=new Vector2d(xR,yR);
            Grass grass=new Grass(newGrassPosition);
            if (!this.isOccupied(newGrassPosition)){
                if(
                        this.mapBoundary.jnglLowCorner.x<xR &&
                        this.mapBoundary.jnglUppCorner.x>xR &&
                        this.mapBoundary.jnglLowCorner.y<yR &&
                        this.mapBoundary.jnglUppCorner.y>yR &&
                        !grassIn
                ){
                    grassIn=true;
                    this.placeGrass(grass);
                    continue;
                }
                if(
                        ((this.mapBoundary.jnglLowCorner.x>=xR ||
                        this.mapBoundary.jnglUppCorner.x<=xR) ||
                        (this.mapBoundary.jnglLowCorner.y>=yR ||
                        this.mapBoundary.jnglUppCorner.y<=yR)) &&
                        !grassOut
                ){
                    grassOut=true;
                    this.placeGrass(grass);
                    continue;
                }
            }
            grassN--;
        }
        if(grassIn||grassOut){
            return true;
        }
        return false;
    }





//    public boolean generateAnimals(int animalsN) {
//        while( this.animals.size()<this.width*this.height && animalsN>0){
//            int yR=(int)Math.floor(Math.random()*(this.height-1));
//            int xR=(int)Math.floor(Math.random()*(this.width-1));
//            Vector2d newAnimalPosition=new Vector2d(xR,yR);
//            Animal animal=new Animal(this,newAnimalPosition,this.startEnergy);
//            if (this.place(animal)){
//                animalsN--;
//            }
//        }
//        if(this.animals.size()==this.width*this.height && animalsN>0){
//            return false;
//        }
//        return true;
//    }

//    public boolean place(Animal animal){
//            if(this.isOccupied(animal.position)){
//                return false;
//            }
//            this.animalsOnPosition.get(animal.position).add(animal);
//            return true;
//    }
//

//    public boolean isOccupied(Vector2d position) {
//        if(this.animalsOnPosition.containsKey(position))return true;
//        if(this.grasses.containsKey(position))return true;
//        return false;
//    }

public Object objectAt(Vector2d position) {
    if(this.animalsOnPosition.containsKey(position)){
        if (this.animalsOnPosition.get(position).size()>0){
        return this.animalsOnPosition.get(position).get(0);
        }
    }
    if(this.grasses.containsKey(position))return this.grasses.get(position);
    return null;
}

public ArrayList<Animal> animalsAt(Vector2d position) {
    return animalsOnPosition.get(position);
}






    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
//            Animal curr=animalsOnPosition.get(oldPosition);
//            animalsOnPosition.get(newPosition);
//            animalsOnPosition.remove(oldPosition);
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