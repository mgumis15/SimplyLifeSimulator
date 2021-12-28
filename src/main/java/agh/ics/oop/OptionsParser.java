package agh.ics.oop;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

public class OptionsParser {
    public ArrayList<Integer> args=new ArrayList<Integer>();
    public boolean magicB;
    public boolean magicNB;
    public void parse(List<String> parameters){
        if(parameters.size()!=12){
            throw new IllegalArgumentException("Illegal amount of arguments");
        }
        for (int i = 0; i < 10; i++) {
            try {
                this.args.add(Integer.valueOf(parameters.get(i)));
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("Illegal type of number argument");
            }
        }
        if(Objects.equals(parameters.get(11), "true")){
            this.magicB=true;
        }else if(Objects.equals(parameters.get(11), "false")){
            this.magicB=false;
        }else{
            throw new IllegalArgumentException("Illegal type of boolean argument");
        }
        if(Objects.equals(parameters.get(10), "true")){
            this.magicNB=true;
        }else if(Objects.equals(parameters.get(10), "false")){
            this.magicNB=false;
        }else{
            throw new IllegalArgumentException("Illegal type of boolean argument");
        }


    }
    public int getMapWidthField() {
        return this.args.get(0);
    }
    public int getMapHeightField() {
        return this.args.get(1);
    }
    public int getMapJungleWidthField() {return this.args.get(2); }

    public int getMapJungleHeightField() {return this.args.get(3); }

    public int getStartEnergyField() {
        return this.args.get(4);
    }
    public int getPlantEnergyField() {return this.args.get(5); }

    public int getMoveEnergyField() {
        return this.args.get(6);
    }
    public int getAnimalStartField() {return this.args.get(7); }

    public int getGrassStartField() {return this.args.get(8); }

    public int getMoveDelayField() {
        return this.args.get(9);
    }

    public boolean getMagicB() {
        return this.magicB;
    }

    public boolean getMagicNB() {
        return this.magicNB;
    }
}
