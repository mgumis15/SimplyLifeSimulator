package agh.ics.oop.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Menu {
    public VBox menu;
    protected TextField mapWidthField;
    protected TextField mapHeightField;
    protected TextField mapJungleWidthField;
    protected TextField mapJungleHeightField;
    protected TextField startEnergyField;
    protected TextField plantEnergyField;
    protected TextField moveEnergyField;
    protected TextField animalStartField;
    protected TextField grassStartField;
    protected TextField moveDelayField;
    public Button start;

    public Menu(){
        Text mapTitle = new Text("Map properties");
        mapTitle.setTextAlignment(TextAlignment.CENTER);

        Text mapWidthLabel = new Text("Width: ");
        this.mapWidthField=new TextField();
        this.mapWidthField.setText("10");
        HBox mapWidthBox=new HBox(mapWidthLabel,this.mapWidthField);
        mapWidthBox.setSpacing(7);
        mapWidthBox.setAlignment(Pos.CENTER);

        Text mapHeightLabel = new Text("Height: ");
        this.mapHeightField=new TextField();
        this.mapHeightField.setText("10");
        HBox mapHeightBox=new HBox(mapHeightLabel,this.mapHeightField);
        mapHeightBox.setSpacing(7);
        mapHeightBox.setAlignment(Pos.CENTER);



        Text mapJungleWidthLabel = new Text("Jungle width: ");
        this.mapJungleWidthField=new TextField();
        this.mapJungleWidthField.setText("3");
        HBox mapJungleWidthBox=new HBox(mapJungleWidthLabel,this.mapJungleWidthField);
        mapJungleWidthBox.setSpacing(7);
        mapJungleWidthBox.setAlignment(Pos.CENTER);

        Text mapJungleHeightLabel = new Text("Jungle height: ");
        this.mapJungleHeightField=new TextField();
        this.mapJungleHeightField.setText("3");
        HBox mapJungleHeightBox=new HBox(mapJungleHeightLabel,this.mapJungleHeightField);
        mapJungleHeightBox.setSpacing(7);
        mapJungleHeightBox.setAlignment(Pos.CENTER);

        VBox mapStatsBox=new VBox(mapTitle,mapWidthBox,mapHeightBox,mapJungleWidthBox,mapJungleHeightBox);
        mapStatsBox.setSpacing(15);
        mapStatsBox.setAlignment(Pos.CENTER);

        Text energyTitle = new Text("Energy properties:");
        energyTitle.setTextAlignment(TextAlignment.CENTER);

        Text startEnergyLabel = new Text("Animal start energy: ");
        this.startEnergyField=new TextField();
        this.startEnergyField.setText("40");
        HBox startEnergyBox=new HBox(startEnergyLabel,this.startEnergyField);
        startEnergyBox.setSpacing(7);
        startEnergyBox.setAlignment(Pos.CENTER);

        Text plantEnergyLabel = new Text("Plant energy: ");
        this.plantEnergyField=new TextField();
        this.plantEnergyField.setText("4");
        HBox plantEnergyBox=new HBox(plantEnergyLabel,this.plantEnergyField);
        plantEnergyBox.setSpacing(7);
        plantEnergyBox.setAlignment(Pos.CENTER);

        Text moveEnergyLabel = new Text("Move energy cost: ");
        this.moveEnergyField=new TextField();
        this.moveEnergyField.setText("1");
        HBox moveEnergyBox=new HBox(moveEnergyLabel,this.moveEnergyField);
        moveEnergyBox.setSpacing(7);
        moveEnergyBox.setAlignment(Pos.CENTER);

        VBox energyStatsBox=new VBox(energyTitle,startEnergyBox,plantEnergyBox,moveEnergyBox);
        energyStatsBox.setSpacing(15);
        energyStatsBox.setAlignment(Pos.CENTER);

        Text spawnTitle = new Text("Spawn properties:");
        spawnTitle.setTextAlignment(TextAlignment.CENTER);

        Text animalStartLabel = new Text("Animals at start: ");
        this.animalStartField=new TextField();
        this.animalStartField.setText("5");
        HBox animalStartBox=new HBox(animalStartLabel,this.animalStartField);
        animalStartBox.setSpacing(7);
        animalStartBox.setAlignment(Pos.CENTER);

        Text grassStartLabel = new Text("Grasses at start: ");
        this.grassStartField=new TextField();
        this.grassStartField.setText("15");
        HBox grassStartBox=new HBox(grassStartLabel,this.grassStartField);
        grassStartBox.setSpacing(7);
        grassStartBox.setAlignment(Pos.CENTER);

        VBox spawnStatsBox=new VBox(spawnTitle,animalStartBox,grassStartBox);
        spawnStatsBox.setSpacing(15);
        spawnStatsBox.setAlignment(Pos.CENTER);

        Text timeTitle = new Text("Refresh time:");
        timeTitle.setTextAlignment(TextAlignment.CENTER);

        Text moveDelayLabel = new Text("Delay (ms): ");
        this.moveDelayField=new TextField();
        this.moveDelayField.setText("300");
        HBox moveDelayBox=new HBox(moveDelayLabel,this.moveDelayField);
        moveDelayBox.setSpacing(7);
        moveDelayBox.setAlignment(Pos.CENTER);

        VBox moveDelayStatsBox=new VBox(timeTitle,moveDelayBox);
        moveDelayStatsBox.setSpacing(15);
        moveDelayStatsBox.setAlignment(Pos.CENTER);

        this.start=new Button("Start");



        this.menu=new VBox(mapStatsBox,energyStatsBox,spawnStatsBox,moveDelayStatsBox,this.start);
        this.menu.setSpacing(20);
        this.menu.setAlignment(Pos.CENTER);
    }

    public VBox getMenu() {
        return menu;
    }

    public int getAnimalStartField() {return Integer.parseInt(animalStartField.getText());}

    public int getGrassStartField() {return Integer.parseInt(grassStartField.getText());}

    public int getMapHeightField() {
        return Integer.parseInt(mapHeightField.getText());
    }

    public int getMapJungleHeightField() {return Integer.parseInt(mapJungleHeightField.getText());}

    public int getMapJungleWidthField() {return Integer.parseInt(mapJungleWidthField.getText());}

    public int getMapWidthField() {
        return Integer.parseInt(mapWidthField.getText());
    }

    public int getMoveDelayField() {
        return Integer.parseInt(moveDelayField.getText());
    }

    public int getMoveEnergyField() {
        return Integer.parseInt(moveEnergyField.getText());
    }

    public int getPlantEnergyField() {return Integer.parseInt(plantEnergyField.getText());}

    public int getStartEnergyField() {
        return Integer.parseInt(startEnergyField.getText());
    }
}
