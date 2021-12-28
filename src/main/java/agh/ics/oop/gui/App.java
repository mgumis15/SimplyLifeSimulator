package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.beans.EventHandler;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App extends Application  {

    protected  BoundaryMap mapB ;
    protected NoBoundariesMap mapNB;
    public IEngine engineB;
    public IEngine engineNB;
    protected GridPane gridB;
    protected GridPane gridNB;
    protected GridMapVisualizer mapVisB;
    protected GridMapVisualizer mapVisNB;
    protected Thread engineThreadB;
    protected Thread engineThreadNB;
    protected DataVisualizer dataVisualizerB=new DataVisualizer();
    protected DataVisualizer dataVisualizerNB=new DataVisualizer();
    public void init(){
        try {

            System.out.println("system wystartował");
        }catch(IllegalArgumentException ex){
            System.out.println(ex.toString());
        }
    }
    @Override
    public void start(Stage primaryStage){

        System.out.println("Odpalamy grafę");
        Menu menu=new Menu();
        VBox menuBox=menu.getMenu();
        this.gridNB=new GridPane();
        this.gridB=new GridPane();
        this.gridB.setMaxWidth(500);
        this.gridNB.setMaxWidth(500);
        Button stopB=new Button("Pause");
        stopB.setOnAction(action ->{
            if(this.engineB.getRunState()){
                this.engineB.stop();
                stopB.setText("Continue");
            }else{
                this.engineB.conitnueRun();
                stopB.setText("Stop");
            }
        });
        Button stopNB=new Button("Pause");
        stopNB.setOnAction(action ->{
            if(this.engineNB.getRunState()){
                this.engineNB.stop();
                stopNB.setText("Continue");
            }else{
                this.engineNB.conitnueRun();
                stopNB.setText("Stop");
            }
        });
        Button showDomGenButtonB=new Button("Animals dom. gens");
        showDomGenButtonB.setOnAction(action ->{
            if(!this.engineB.getRunState()){
                try{
                    this.mapVisB.draw(true);
                } catch (FileNotFoundException e) {
                    System.out.println("Błąd rysowania");
                }
            }
        });
        Button showDomGenButtonNB=new Button("Animals dom. gens");
        showDomGenButtonNB.setOnAction(action ->{
            if(!this.engineNB.getRunState()){
                try{
                    this.mapVisNB.draw(true);
                } catch (FileNotFoundException e) {
                    System.out.println("Błąd rysowania");
                };
            }
        });
        Button saveDataB=new Button("Save");
        saveDataB.setOnAction(action ->{
            if(!this.engineB.getRunState()){
                try{
                    this.dataVisualizerB.dataContainer.saveData();
                }catch (IOException e){
                    System.out.println("Cannot save data");
                }
            }
        });
        Button saveDataNB=new Button("Save");
        saveDataNB.setOnAction(action ->{
            if(!this.engineNB.getRunState()){
                try{
                this.dataVisualizerNB.dataContainer.saveData();
                }catch (IOException e){
                    System.out.println("Cannot save data");
                }
            }
        });
        HBox buttonsB=new HBox(stopB,showDomGenButtonB,saveDataB);
        buttonsB.setSpacing(10);
        HBox buttonsNB=new HBox(stopNB,showDomGenButtonNB,saveDataNB);
        buttonsNB.setSpacing(10);
        Label gridBLabel=new Label("With boundaries map");
        Label gridNBLabel=new Label("Without boundaries map");



        VBox gridBBox=new VBox(gridBLabel,buttonsB,this.gridB,this.dataVisualizerB.getDataBox());
        VBox gridNBBox=new VBox(gridNBLabel,buttonsNB,this.gridNB,this.dataVisualizerNB.getDataBox());
        gridBBox.setSpacing(15);
        gridNBBox.setSpacing(15);
        HBox mapBox=new HBox(gridNBBox,gridBBox);
        mapBox.setSpacing(30);
        Scene sceneMain=new Scene(mapBox,1100,1100);
        menu.start.setOnAction(action->{
            this.mapNB=new NoBoundariesMap(menu.getMapWidthField(),menu.getMapHeightField(),menu.getMapJungleWidthField(),menu.getMapJungleHeightField(),menu.getMagicNB());
            this.mapB=new BoundaryMap(menu.getMapWidthField(),menu.getMapHeightField(),menu.getMapJungleWidthField(),menu.getMapJungleHeightField(),menu.getMagicB());
            this.mapNB.setPlantEnergy(menu.getPlantEnergyField());
            this.mapB.setPlantEnergy(menu.getPlantEnergyField());
            this.mapNB.setMoveEnergy(menu.getMoveEnergyField());
            this.mapB.setMoveEnergy(menu.getMoveEnergyField());
            this.mapNB.setStartEnergy(menu.getStartEnergyField());
            this.mapB.setStartEnergy(menu.getStartEnergyField());
            this.engineNB=new SimulationEngine( this.mapNB,menu.getMoveDelayField(),menu.getAnimalStartField(),menu.getGrassStartField());
            this.engineB=new SimulationEngine( this.mapB,menu.getMoveDelayField(),menu.getAnimalStartField(),menu.getGrassStartField());
            this.mapVisB=new GridMapVisualizer(this.mapB,this.gridB,this.engineB);
            this.mapVisNB=new GridMapVisualizer(this.mapNB,this.gridNB,this.engineNB);
            this.engineNB.addObserver(this);
            this.engineB.addObserver(this);
            this.engineThreadNB = new Thread((Runnable) this.engineNB);
            this.engineThreadB = new Thread((Runnable) this.engineB);
            this.dataVisualizerB.setMap(this.mapB);
            this.dataVisualizerNB.setMap(this.mapNB);
            this.engineThreadNB.start();
            this.engineThreadB.start();

            try{

                this.mapVisB.draw(false);
                this.mapVisNB.draw(false);
                this.dataVisualizerB.drawChart();
                this.dataVisualizerNB.drawChart();

            }catch (FileNotFoundException ex){
                System.out.println(ex.toString());
            }

            primaryStage.setScene(sceneMain);
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> {
                this.engineNB.endRun();
                this.engineB.endRun();
                Platform.exit();
                System.out.println("system zakończył działanie");
            });

        });


        Scene sceneMenu = new Scene(menuBox, 600, 700);

        primaryStage.setScene(sceneMenu);
        primaryStage.show();

    }
    public void newDay(){
        Platform.runLater(() -> {
                try{
                    if(this.engineB.getRunState()){
                    this.mapVisB.draw(false);
                    }
                    this.dataVisualizerB.updateData();
                    if(this.engineNB.getRunState()){
                    this.mapVisNB.draw(false);
                    }
                    this.dataVisualizerNB.updateData();
                } catch (FileNotFoundException e) {
                    System.out.println("Błąd rysowania");
                }
            });

    }

}
