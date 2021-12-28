package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GridMapVisualizer {
    private IWorldMap map;
    private GridPane grid;
    private IEngine engine;
    private int scaleW;
    private int scaleH;

    public GridMapVisualizer(IWorldMap map, GridPane grid, IEngine engine) {
        this.map = map;
        this.grid=grid;
        this.engine=engine;
        this.scaleH=(int) (500/this.map.getHeight());
        this.scaleW=(int) (500/this.map.getWidth());
    }

    public void draw(boolean highlighte) throws FileNotFoundException {
        ArrayList<Integer> genes=new ArrayList<Integer>();
        if(highlighte){
            genes=this.map.getGensDominant();
        }
        this.clear();
        Vector2d lowerLeft=this.map.getMapBoundary().getLowerCorner();
        Vector2d upperRight=this.map.getMapBoundary().getUpperCorner();
        Vector2d jnglLowCorner=this.map.getMapBoundary().getJnglLowCorner();
        Vector2d jnglUppCorner=this.map.getMapBoundary().getJnglUppCorner();
        this.drawHeader( lowerLeft,  upperRight);
        for (int i = upperRight.y; i >= lowerLeft.y ; i--) {
            Text headerX=new Text(String.valueOf(i));
            this.grid.add(headerX,0,upperRight.y-i+1);
            this.grid.setHalignment(headerX, HPos.CENTER);
            for (int j = lowerLeft.x; j <= upperRight.x + 1; j++) {
                    if (j <= upperRight.x) {
                        VBox rect=drawObject(new Vector2d(j, i),genes);
                        VBox backG = new VBox();
                        backG.setPrefWidth(this.scaleW);
                        backG.setPrefHeight(this.scaleH);
                        if(i>jnglLowCorner.x&&i<jnglUppCorner.x&&j>jnglLowCorner.y&&j<jnglUppCorner.y){

                            backG.setStyle("-fx-background-color: #174d16;");
                        }else{

                            backG.setStyle("-fx-background-color: #c2fcd2;");
                        }
                        this.grid.add(backG,j-lowerLeft.x+ 1,upperRight.y-i+1);

                        if(rect!=null){
                            backG.getChildren().add(rect);
//                            this.grid.add(rect,j-lowerLeft.x+ 1,upperRight.y-i+1);
//                            this.grid.setHalignment(rect, HPos.CENTER);
                        }
                    }
            }

        }
    }
    public void clear(){
        this.grid.getChildren().clear();

        while(this.grid.getRowConstraints().size() > 0){
            this.grid.getRowConstraints().remove(0);
        }

        while(this.grid.getColumnConstraints().size() > 0){
            this.grid.getColumnConstraints().remove(0);
        }
    }
    private void drawHeader(Vector2d lowerLeft, Vector2d upperRight) {
        Text header=new Text(" y\\x ");
        this.grid.getColumnConstraints().add(new ColumnConstraints(25));
        this.grid.getRowConstraints().add(new RowConstraints(25));
        this.grid.add(header,0,0);
        this.grid.setHalignment(header, HPos.CENTER);
        for (int j = lowerLeft.x; j < upperRight.x +1; j++) {
            Text headerX=new Text(String.valueOf(j));
            this.grid.add(headerX,j-lowerLeft.x+1,0);
            this.grid.getColumnConstraints().add(new ColumnConstraints(this.scaleW-1));
            this.grid.getRowConstraints().add(new RowConstraints(this.scaleH-1));
            this.grid.setHalignment(headerX, HPos.CENTER);
        }
    }

    private VBox drawObject(Vector2d currentPosition,ArrayList<Integer> genes) throws FileNotFoundException {
        if (this.map.isOccupied(currentPosition)) {
            Object object = this.map.objectAt(currentPosition);
            if (object != null) {
                VBox rect = new VBox();

                rect.setPrefWidth(this.scaleW-1);
                rect.setPrefHeight(this.scaleH-1);
                if (object instanceof Animal) {

                    Circle cic=new Circle((this.scaleW-1)/2,(this.scaleH-1)/2,(this.scaleW-1)/2);
                    cic.setFill(Color.ORANGE);
                    int animE=((Animal) object).energy;
                    int startE=this.map.getStartEnergy();
                    if(this.map.getChosenAnimal()==object){
                        cic.setStroke(Color.RED);
                        cic.setStrokeWidth(2);
                        cic.setStrokeType(StrokeType.INSIDE);
                    }
                    if(genes.size()>0){
                        if(((Animal) object).genes.equals(genes)){
                            cic.setStroke(Color.rgb(0,255,255));
                            cic.setStrokeWidth(2);
                            cic.setStrokeType(StrokeType.INSIDE);
                        }
                    }
                    if(animE>=2*startE){
                        cic.setFill(Color.rgb(51,51,0));
                    }else if(animE>=startE && animE<2*startE){
                        cic.setFill(Color.rgb(102, 102, 51));
                    }else if(animE>=0.75*startE && animE<startE){
                        cic.setFill(Color.rgb(204, 204, 0));
                    }else if(animE>=0.5*startE && animE<0.75*startE){
                        cic.setFill(Color.rgb(255, 204, 0));
                    }else if(animE>=0.25&& animE<0.5*startE){
                        cic.setFill(Color.rgb(255, 255, 204));
                    }else if(animE>=0&& animE<0.25*startE){
                        cic.setFill(Color.rgb(255, 255, 204));
                    }

                    cic.setOnMouseClicked(event -> {
                        if(!this.engine.getRunState()){
                            this.map.choseAnimal((Animal) object);
                            try{
                                this.draw(false);
                            } catch (FileNotFoundException e) {
                                System.out.println("Błąd rysowania");
                            };

                        }

                    });
                    rect.getChildren().add(cic);
                }else{
                    rect.setStyle("-fx-background-color: #2dfc03;");
                }
                return rect;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
