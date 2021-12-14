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
    public TextField mapWidthField;
    public TextField mapHeightField;
    public TextField mapJungleRatioField;
    public TextField startEnergyField;
    public TextField plantEnergyField;
    public TextField moveEnergyField;
    public TextField animalStartField;
    public TextField grassStartField;
    public TextField moveDelayField;
    public Button start;

    public Menu(){
        Text mapTitle = new Text("Map properties");
        mapTitle.setTextAlignment(TextAlignment.CENTER);

        Text mapWidthLabel = new Text("Width: ");
        this.mapWidthField=new TextField();
        this.mapWidthField.setText("100");
        HBox mapWidthBox=new HBox(mapWidthLabel,this.mapWidthField);
        mapWidthBox.setSpacing(7);
        mapWidthBox.setAlignment(Pos.CENTER);

        Text mapHeightLabel = new Text("Height: ");
        this.mapHeightField=new TextField();
        this.mapHeightField.setText("100");
        HBox mapHeightBox=new HBox(mapHeightLabel,this.mapHeightField);
        mapHeightBox.setSpacing(7);
        mapHeightBox.setAlignment(Pos.CENTER);

        Text mapJungleRatioLabel = new Text("Jungle ratio (1-100): ");
        this.mapJungleRatioField=new TextField();
        this.mapJungleRatioField.setText("7");
        HBox mapJungleRatioBox=new HBox(mapJungleRatioLabel,this.mapJungleRatioField);
        mapJungleRatioBox.setSpacing(7);
        mapJungleRatioBox.setAlignment(Pos.CENTER);

        VBox mapStatsBox=new VBox(mapTitle,mapWidthBox,mapHeightBox,mapJungleRatioBox);
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
        this.animalStartField.setText("7");
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
        this.moveDelayField.setText("200");
        HBox moveDelayBox=new HBox(moveDelayLabel,this.moveDelayField);
        moveDelayBox.setSpacing(7);
        moveDelayBox.setAlignment(Pos.CENTER);

        VBox moveDelayStatsBox=new VBox(timeTitle,moveDelayBox);
        moveDelayStatsBox.setSpacing(15);
        moveDelayStatsBox.setAlignment(Pos.CENTER);

        this.start=new Button("Start");
        this.start.setOnAction(event->{
            System.out.println("STARTUJEMY");
        });


        this.menu=new VBox(mapStatsBox,energyStatsBox,spawnStatsBox,moveDelayStatsBox,this.start);
        this.menu.setSpacing(20);
        this.menu.setAlignment(Pos.CENTER);
    }

    public VBox getMenu() {
        return menu;
    }
}
