package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class App extends Application  {

    protected NoBoundariesMap mapNB;
    protected  BoundaryMap mapB ;
    protected Vector2d[] positions;
    public IEngine engineNB;
    public IEngine engineB;
    protected GridPane grid ;
    protected GridMapVisualizer mapVisualizer;
    public void init(){
        try {

            System.out.println("system wystartował");
            this.grid=new GridPane();
//            this.positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
//            this.engine = new SimulationEngine( this.map, this.positions);
//            this.engine.addObserver(this);
//            this.engine.setDelay(1000);

//            System.out.println(map.toString());
            System.out.println("system zakończył działanie");
        }catch(IllegalArgumentException ex){
            System.out.println(ex.toString());
        }
    }
    @Override
    public void start(Stage primaryStage){
        System.out.println("Odpalamy grafę");
        Menu menu=new Menu();
        VBox menuBox=menu.getMenu();

        menu.start.setOnAction(action->{
            this.mapNB=new NoBoundariesMap(menu.getMapWidthField(),menu.getMapHeightField(),menu.getMapJungleRatioField());
            this.mapB=new BoundaryMap(menu.getMapWidthField(),menu.getMapHeightField(),menu.getMapJungleRatioField());
            this.mapNB.setPlantEnergy(menu.getPlantEnergyField());
            this.mapB.setPlantEnergy(menu.getPlantEnergyField());
            this.mapNB.setMoveEnergy(menu.getMoveEnergyField());
            this.mapB.setMoveEnergy(menu.getMoveEnergyField());
            this.mapNB.setStartEnergy(menu.getStartEnergyField());
            this.mapB.setStartEnergy(menu.getStartEnergyField());
            this.engineNB=new SimulationEngine( this.mapNB,menu.getMoveDelayField(),menu.getAnimalStartField(),menu.getGrassStartField());
            this.engineB=new SimulationEngine( this.mapB,menu.getMoveDelayField(),menu.getAnimalStartField(),menu.getGrassStartField());

            Thread engineThreadNB = new Thread((Runnable) this.engineNB);
            Thread engineThreadB = new Thread((Runnable) this.engineB);
            engineThreadNB.start();
            engineThreadB.start();
        });






//
//        this.mapVisualizer=new GridMapVisualizer(this.map,this.grid);
//        try{
//
//        this.mapVisualizer.draw(this.map.mapBoundary.getLowerCorner(),this.map.mapBoundary.getUpperCorner());
//        }catch (FileNotFoundException ex){
//            System.out.println(ex.toString());
//        }



        Scene scene = new Scene(menuBox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

        public void positionChanged() {
//            Platform.runLater(() -> {
//                this.grid.getChildren().clear();
//                try{
//                    this.mapVisualizer.draw(this.map.mapBoundary.getLowerCorner(),this.map.mapBoundary.getUpperCorner());
//                    this.grid.setGridLinesVisible(true);
//                } catch (FileNotFoundException e) {
//                    System.out.println("Błąd rysowania");
//                }
//            });

    }
}
