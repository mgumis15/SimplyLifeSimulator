package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import agh.ics.oop.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class GridMapVisualizer {
    private IWorldMap map;
    private GridPane grid;
    private int scaleW;
    private int scaleH;

    public GridMapVisualizer(IWorldMap map, GridPane grid) {
        this.map = map;
        this.grid=grid;
        this.scaleH=(int) (500/this.map.getHeight());
        this.scaleW=(int) (500/this.map.getWidth());
    }

    public void draw() throws FileNotFoundException {
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
                        VBox rect=drawObject(new Vector2d(j, i));

                        if(rect!=null){
                            this.grid.add(rect,j-lowerLeft.x+ 1,upperRight.y-i+1);
                            this.grid.setHalignment(rect, HPos.CENTER);
                        }else{
                            VBox backG = new VBox();
                            backG.setPrefWidth(this.scaleW);
                            backG.setPrefHeight(this.scaleH);
                            if(i>jnglLowCorner.x&&i<jnglUppCorner.x&&j>jnglLowCorner.y&&j<jnglUppCorner.y){

                            backG.setStyle("-fx-background-color: #174d16;");
                            }else{

                            backG.setStyle("-fx-background-color: #fcba03;");
                            }
                            this.grid.add(backG,j-lowerLeft.x+ 1,upperRight.y-i+1);
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

    private VBox drawObject(Vector2d currentPosition) throws FileNotFoundException {
        if (this.map.isOccupied(currentPosition)) {
            Object object = this.map.objectAt(currentPosition);
            if (object != null) {
                VBox rect = new VBox();
                rect.setPrefWidth(this.scaleW-1);
                rect.setPrefHeight(this.scaleH-1);
                if (object instanceof Animal) {
                    rect.setStyle("-fx-background-color: #fc6b03;");
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
