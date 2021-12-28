package agh.ics.oop;

import java.util.*;


abstract class AbstractWorldMap implements IWorldMap {
    protected LinkedHashMap<Vector2d, ArrayList<Animal>> animalsOnPosition = new LinkedHashMap<>();

    public ArrayList<Animal> animals=new ArrayList<Animal>();
    protected LinkedHashMap<Vector2d, Grass> grasses = new LinkedHashMap<>();
    protected int width;
    protected int height;
    protected int jungleWidth;
    protected int jungleHeight;
    protected int plantEnergy;
    protected int moveEnergy;
    protected int startEnergy;
    protected int energySum;
    protected int deadAnimalsQuant;
    protected int deadAnimalsSumLifeSpan;
    protected Animal chosenAnimal;
    public MapBoundary mapBoundary;
    protected int days=0;
    protected int magic=-1;
    protected int allChilds=0;
    protected int trackedChildren=0;
    protected int trackedDescendants=0;

    public AbstractWorldMap(int width,int height,int jungleWidth,int jungleHeight,boolean magic){
        this.width=width;
        this.height=height;
        this.jungleWidth=jungleWidth;
        this.jungleHeight=jungleHeight;
        this.mapBoundary=new MapBoundary(this.width,this.height,this.jungleWidth,this.jungleHeight);
        this.chosenAnimal=null;
        if(magic)this.magic=3;
    }



    public boolean newDayRise(){

    this.days+=1;
    this.removeDeadAnimals();
    if(this.animals.size() <= 5 && this.magic>0){
        this.magicGenerationAnimals();
        this.magic--;
    }
    this.moveAllAnimals();
    this.animalsEatGrass();
    this.animalsReproducton();
    this.generateDailyGrasses();
        return this.animals.size() <= 0;
    }

    public void removeDeadAnimals(){
        Iterator<Animal> i = this.animals.iterator();
        while(i.hasNext()) {

            Animal animal=i.next();
            if(animal.getEnergy()<this.moveEnergy){
                this.allChilds-=(int) Math.floor(animal.childs/2);
                animal.setDeathDay(this.days);
                this.deadAnimalsSumLifeSpan+=animal.lifeSpan;
                this.deadAnimalsQuant+=1;
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
                int countIdentical=1;
                for (int i = 1; i < this.animalsOnPosition.get(key).size(); i++) {
                    if(this.animalsOnPosition.get(key).get(i-1).energy==this.animalsOnPosition.get(key).get(i).energy)countIdentical++;
                    else break;
                }
                for (int i = 0; i < countIdentical; i++) {
                    this.animalsOnPosition.get(key).get(i).energy+=(int) Math.floor((double)this.plantEnergy/countIdentical);
                }
                this.grasses.remove(key);
            }
        }
    }
    public void animalsReproducton(){
        Set<Vector2d> keys=this.animalsOnPosition.keySet();
        for (Vector2d key : keys) {

            if(this.animalsOnPosition.get(key).size()>=2){
                this.animalsOnPosition.get(key).sort((o1, o2) -> Integer.compare(o2.energy, o1.energy));
                if(this.animalsOnPosition.get(key).get(0).energy<this.startEnergy/2)return;

                int countIdenFirst=1;
                for (int i = 1; i < this.animalsOnPosition.get(key).size(); i++) {
                    if(this.animalsOnPosition.get(key).get(i-1).energy==this.animalsOnPosition.get(key).get(i).energy)countIdenFirst++;
                    else break;
                }

                if(countIdenFirst==1){
                    if(this.animalsOnPosition.get(key).get(1).energy<this.startEnergy/2)return;
                    int countIdenSecond=1;
                    for (int i = 2; i < this.animalsOnPosition.get(key).size(); i++) {
                        if(this.animalsOnPosition.get(key).get(i-1).energy==this.animalsOnPosition.get(key).get(i).energy)countIdenSecond++;
                        else break;
                    }
                    if(countIdenSecond==1){
                        this.bornAnimal(this.animalsOnPosition.get(key).get(0),this.animalsOnPosition.get(key).get(1));
                    }else{
                        int index2=new Random().nextInt(countIdenSecond)+1;
                        this.bornAnimal(this.animalsOnPosition.get(key).get(0),this.animalsOnPosition.get(key).get(index2));
                    }
                }else{
                    int index1=new Random().nextInt(countIdenFirst);
                    int index2=new Random().nextInt(countIdenFirst);
                    while(index2==index1){
                        index2=new Random().nextInt(countIdenFirst);
                    }
                    this.bornAnimal(this.animalsOnPosition.get(key).get(index1),this.animalsOnPosition.get(key).get(index2));
                }
            }
        }
    }


    public void bornAnimal(Animal parent1,Animal parent2){
        if(this.chosenAnimal==parent1 || this.chosenAnimal==parent2)this.trackedChildren+=1;

            this.allChilds+=1;
            parent1.childs+=1;
            parent2.childs+=1;
            int sumEnergy=parent1.energy+parent2.energy;
            boolean side=new Random().nextBoolean();
            boolean firstStronger;
            double percentage;
            if(parent1.energy>parent2.energy){
                percentage=(double)parent1.energy/(sumEnergy);
                firstStronger=true;
            }
            else {
                percentage=(double )parent2.energy/(sumEnergy);
                firstStronger=false;
            }
            int slicePoint=(int) Math.ceil(percentage*32)-1;
            Vector2d pos=new Vector2d(parent1.position.x,parent1.position.y);
            Animal child=new Animal(this,pos,(int) Math.floor(parent1.energy/4)+ (int) Math.floor(parent2.energy/4));
            parent1.energy-=(int) Math.floor(parent1.energy/4);
            parent2.energy-=(int) Math.floor(parent2.energy/4);
        this.placeAnimal(child);
        if(parent1.tracked || parent2.tracked || this.chosenAnimal==parent1 || this.chosenAnimal==parent2){
            child.tracked=true;
            this.trackedDescendants+=1;
        }
        for (int i = 0; i < 32; i++) {
            if(side){
              if(i<=slicePoint){
                  if (firstStronger){
                      child.genes.set(i,parent1.genes.get(i));
                  }else{
                      child.genes.set(i,parent2.genes.get(i));
                  }
              }else{
                  if (firstStronger){
                      child.genes.set(i,parent2.genes.get(i));
                  }else{
                      child.genes.set(i,parent1.genes.get(i));
                  }
              }
            }else{
                if(i>=32-slicePoint){
                    if (firstStronger){
                        child.genes.set(i,parent1.genes.get(i));
                    }else{
                        child.genes.set(i,parent2.genes.get(i));
                    }
                }else{
                    if (firstStronger){
                        child.genes.set(i,parent2.genes.get(i));
                    }else{
                        child.genes.set(i,parent1.genes.get(i));
                    }
                }
            }
        }
        Collections.sort(child.genes);
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
    public void magicGenerationAnimals(){
        int toGenerate=5;
        int alive=this.animals.size();
        if(alive>0){
            while(toGenerate>0){
                int yR=(int)Math.floor(Math.random()*(this.height));
                int xR=(int)Math.floor(Math.random()*(this.width));
                Vector2d newAnimalPosition=new Vector2d(xR,yR);
                if (!this.isOccupied(newAnimalPosition)){
                    Animal animal=new Animal(this,newAnimalPosition,this.startEnergy);
                    int randomAlive=new Random().nextInt(alive);
                    for (int i = 0; i < 32; i++) {
                        animal.genes.set(i,this.animals.get(randomAlive).genes.get(i));
                    }
                    this.placeAnimal(animal);
                    toGenerate--;
                }
            }
        }else{
            while(toGenerate>0){
                int yR=(int)Math.floor(Math.random()*(this.height));
                int xR=(int)Math.floor(Math.random()*(this.width));
                Vector2d newAnimalPosition=new Vector2d(xR,yR);
                if (!this.isOccupied(newAnimalPosition)){
                    Animal animal=new Animal(this,newAnimalPosition,this.startEnergy);
                    this.placeAnimal(animal);
                    toGenerate--;
                }
            }
        }
    }

    public void placeGrass(Grass grass) {
        this.grasses.put(grass.position,grass);
    }

    public void placeAnimal(Animal animal) {
        if(this.animalsOnPosition.containsKey(animal.position)){
                this.animalsOnPosition.get(animal.position).add(animal);
        }else{
            ArrayList<Animal> newCell=new ArrayList<Animal>();
            newCell.add(animal);
            this.animalsOnPosition.put(animal.position,newCell);
        }
        this.animals.add(animal);
    }

    public boolean isOccupied(Vector2d position) {
        if(this.grasses.containsKey(position))return true;
        if(this.animalsOnPosition.containsKey(position))return true;

        return false;
    }
    public void moveAllAnimals(){
        this.energySum=0;
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
            this.energySum+=animal.energy;
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

    public int getStartEnergy() {
        return startEnergy;
    }

    public Integer getDays() {
        return days;
    }

    public Integer getAnimalsQuant() {
        return this.animals.size();
    }
    public Integer getGrassQuant() {
        return this.grasses.size();
    }
    public Integer getAnimalsLifespanMean() {
        if(this.deadAnimalsQuant>0){
        return this.deadAnimalsSumLifeSpan/this.deadAnimalsQuant;
        }
        return 0;
    }
    public Integer getEnergyMean() {
        if(this.animals.size()>0) return this.energySum/this.animals.size();
        else return 0;
    }
    public void choseAnimal(Animal newChosen){
        this.chosenAnimal=newChosen;
        for (Animal animal:this.animals) {
            animal.tracked=false;
        }
        this.trackedDescendants=0;
        this.trackedChildren=0;
    };
    public Animal getChosenAnimal() {
        return chosenAnimal;
    }

    public int getMagic() {
        return magic;
    }
    public ArrayList<Integer> getGensDominant(){
        int maxC=0;
        int count;
        ArrayList<Integer> bestGenes=new ArrayList<Integer>();
            for (int i = 0; i <this.animals.size() ; i++) {
               count=1;
                for (int j = i+1; j <this.animals.size() ; j++) {
                        if(this.animals.get(i).genes.equals(this.animals.get(j).genes)){
                            count+=1;
                        }
                }
                if (count>maxC){
                    bestGenes=this.animals.get(i).genes;
                }
            }
        return bestGenes;
    }
    public Double getAverageChilds(){
        if(this.animals.size()>0){
            return (double) this.allChilds/this.animals.size();
        }else{
            return 0.0;
        }
    }
    public int getChosenChildren(){
        return this.trackedChildren;
    }
    public int getChosenDescendants(){
        return this.trackedDescendants;
    }
}