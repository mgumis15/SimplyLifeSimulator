package agh.ics.oop.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class DataContainer {
    protected ArrayList<String[]> container= new ArrayList<>();

    public void addData(int days,int animalsQuantity, int grassQuantity,int energyMean,int animalsLifeSpan,Double childs) {
        String[] data={String.valueOf(days),String.valueOf(animalsQuantity),String.valueOf(grassQuantity),String.valueOf(energyMean),String.valueOf(animalsLifeSpan),String.valueOf((double) Math.round(childs*100)/100)};
        this.container.add(data);
    }
    public void saveData() throws IOException {

        Date date = new Date();
        FileWriter writer=new FileWriter(date.getTime() +".csv");
        writer.append("Day");
        writer.append(",");
        writer.append("Animals Quantity");
        writer.append(",");
        writer.append("Grass Quantity");
        writer.append(",");
        writer.append("Avg. energy");
        writer.append(",");
        writer.append("Avg. Life Span");
        writer.append(",");
        writer.append("Avg. childs");
        writer.append("\n");
        for (String[] data:this.container) {
            writer.append(String.join(",    ",data));
            writer.append("\n");
        }
        writer.append(String.join(",    ",this.getAverage()));
        writer.flush();
        writer.close();
    }
    public String[] getAverage(){
        int animalsQuantity=0;
        int grassQuantity=0;
        int energyMean=0;
        int animalsLifeSpan=0;
        double childs=0.0;
        int size=this.container.size();
        for (String[] data:this.container) {
            animalsQuantity+=Integer.parseInt(data[1]);
            grassQuantity+=Integer.parseInt(data[2]);
            energyMean+=Integer.parseInt(data[3]);
            animalsLifeSpan+=Integer.parseInt(data[4]);
            childs+=Double.parseDouble(data[5]);
        }
        return new String[]{"Average:",String.valueOf(animalsQuantity/size),String.valueOf(grassQuantity/size),String.valueOf(energyMean/size),String.valueOf(animalsLifeSpan/size),String.valueOf((double) Math.round(childs/size*100)/100)};
    }
}
