package gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class App extends Application {
    protected MoveDirection[] directions;
//    protected GrassField map;
    protected  RectangularMap map ;
    protected Vector2d[] positions;
    protected IEngine engine;
    public void init(){
        try {
            System.out.println("system wystartował");
            this.directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
          this.map= new RectangularMap(10, 5);
//            this.map = new GrassField(10);
            this.positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
            this.engine = new SimulationEngine(this.directions, this.map, this.positions);
            System.out.println(map.toString());
            engine.run();
            System.out.println("system zakończył działanie");
        }catch(IllegalArgumentException ex){
            System.out.println(ex.toString());
        }
    }
    @Override
    public void start(Stage primaryStage){
        System.out.println("Odpalamy grafę");
        Label label = new Label("Zwierzak");

        GridPane grid = new GridPane();

        GridMapVisualizer mapVisualizer=new GridMapVisualizer(this.map,grid);
        try{

        mapVisualizer.draw(this.map.mapBoundary.getLowerCorner(),this.map.mapBoundary.getUpperCorner());
        }catch (FileNotFoundException ex){
            System.out.println(ex.toString());
        }


        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void draw(Vector2d lowerLeft, Vector2d upperRight){

    }
}
