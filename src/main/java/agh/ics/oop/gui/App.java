package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class App extends Application  {

    protected GrassField map;
//    protected  RectangularMap map ;
    protected Vector2d[] positions;
    public IEngine engine;
    protected GridPane grid ;
    protected GridMapVisualizer mapVisualizer;
    public void init(){
        try {
            System.out.println("system wystartował");
            this.grid=new GridPane();
//          this.map= new RectangularMap(10, 5);
            this.map = new GrassField(10);
            this.positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
            this.engine = new SimulationEngine( this.map, this.positions);
            this.engine.addObserver(this);
            this.engine.setDelay(1000);

//            System.out.println(map.toString());
            System.out.println("system zakończył działanie");
        }catch(IllegalArgumentException ex){
            System.out.println(ex.toString());
        }
    }
    @Override
    public void start(Stage primaryStage){
        System.out.println("Odpalamy grafę");
        Text label = new Text("Argumenty: ");

        label.setTextAlignment(TextAlignment.CENTER);
        TextField args=new TextField();
        Button start=new Button("Start");
        start.setOnAction(action->{
            MoveDirection[] directions=new OptionsParser().parse(args.getText().split(" "));
            this.engine.setDirections(directions);
            Thread engineThread = new Thread((Runnable) this.engine);
            engineThread.start();
        });
        VBox box=new VBox(label,args,start,this.grid);
        box.setSpacing(10);





        this.mapVisualizer=new GridMapVisualizer(this.map,this.grid);
        try{

        this.mapVisualizer.draw(this.map.mapBoundary.getLowerCorner(),this.map.mapBoundary.getUpperCorner());
        }catch (FileNotFoundException ex){
            System.out.println(ex.toString());
        }



        Scene scene = new Scene(box, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

        public void positionChanged() {
            Platform.runLater(() -> {
                this.grid.getChildren().clear();
                try{
                    this.mapVisualizer.draw(this.map.mapBoundary.getLowerCorner(),this.map.mapBoundary.getUpperCorner());
                    this.grid.setGridLinesVisible(true);
                } catch (FileNotFoundException e) {
                    System.out.println("Błąd rysowania");
                }
            });

    }
}
