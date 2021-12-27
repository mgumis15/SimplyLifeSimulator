package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IWorldMap;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DataVisualizer {
    protected IWorldMap map;
    protected LineChart lineChart;
    protected XYChart.Series dataAnimals;
    protected XYChart.Series dataGrass;
    protected XYChart.Series dataEnergy;
    protected XYChart.Series dataLifeSpan;
    protected XYChart.Series dataChilds;
    protected VBox dataBox=new VBox();
    protected VBox statsBox=new VBox();
    protected Text childsText=new Text();
    protected Text descendantsText =new Text();
    protected Text deathText=new Text();
    protected Text genesText=new Text();
    protected Text magicGenerations=new Text();
    protected Text genesDominant=new Text();

    public DataVisualizer(){
        this.statsBox.getChildren().addAll(this.genesText,this.childsText,this.descendantsText,this.deathText);
    }

    public void setMap(IWorldMap map) {
        this.map = map;
    }
    public VBox getDataBox(){
        return this.dataBox;
    }


    public void drawChart(){
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Day");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantity");

        this.lineChart = new LineChart(xAxis, yAxis);
        this.lineChart.setCreateSymbols(false);

        this.dataAnimals = new XYChart.Series();
        this.dataAnimals.setName("animals");
        this.dataGrass = new XYChart.Series();
        this.dataGrass.setName("grass");
        this.dataEnergy = new XYChart.Series();
        this.dataEnergy.setName("energy avg.");
        this.dataLifeSpan = new XYChart.Series();
        this.dataLifeSpan.setName("life span avg.");
        this.dataChilds = new XYChart.Series();
        this.dataChilds.setName("childs avg.");

        this.lineChart.getData().add(this.dataAnimals);
        this.lineChart.getData().add(this.dataGrass);
        this.lineChart.getData().add(this.dataEnergy);
        this.lineChart.getData().add(this.dataLifeSpan);
        this.lineChart.getData().add(this.dataChilds);
        this.dataBox.getChildren().add(this.magicGenerations);
        this.dataBox.getChildren().add(this.genesDominant);
        this.dataBox.getChildren().add(this.lineChart);
        this.dataBox.getChildren().add(this.statsBox);


    }
    public void updateData(){
        this.dataAnimals.getData().add(new XYChart.Data( this.map.getDays(), this.map.getAnimalsQuant()));
        this.dataGrass.getData().add(new XYChart.Data( this.map.getDays(), this.map.getGrassQuant()));
        this.dataEnergy.getData().add(new XYChart.Data( this.map.getDays(), this.map.getEnergyMean()));
        this.dataLifeSpan.getData().add(new XYChart.Data( this.map.getDays(), this.map.getAnimalsLifespanMean()));
        this.dataChilds.getData().add(new XYChart.Data( this.map.getDays(), this.map.getAverageChilds()));
        this.drawAnimalStats();
        if(this.map.getMagic()>=0){

        this.magicGenerations.setText("Magic generatios: "+this.map.getMagic());
        }else{
            this.magicGenerations.setText("Magic generatios: NO");
        }
        if(this.map.getGensDominant().size()>0){
            this.genesDominant.setText("Genes Dominant: "+this.map.getGensDominant().toString());
        }else{
            this.genesDominant.setText("Genes Dominant: None");
        }
    }
    public void drawAnimalStats(){
        Animal animal=this.map.getChosenAnimal();
        if(animal!=null){
            this.genesText.setText("Genom: "+animal.genes.toString());
            this.childsText.setText("Childs: "+animal.genes.toString());
            this.descendantsText.setText("Descendants: "+animal.genes.toString());
            if(animal.getDeathDay()>0){
            this.deathText.setText("Death: "+animal.getDeathDay());
            }else{
            this.deathText.setText("Death: NO, it's alive");

            }
        }



    }
}
