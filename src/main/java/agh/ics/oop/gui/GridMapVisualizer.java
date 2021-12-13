package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import agh.ics.oop.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class GridMapVisualizer {
    private IWorldMap map;
    private GridPane grid;


    public GridMapVisualizer(IWorldMap map, GridPane grid) {
        this.map = map;
        this.grid=grid;

    }

    public void draw(Vector2d lowerLeft, Vector2d upperRight) throws FileNotFoundException {
        this.drawHeader( lowerLeft,  upperRight);
        for (int i = upperRight.y; i >= lowerLeft.y ; i--) {
            Text headerX=new Text(String.valueOf(i));
            this.grid.add(headerX,0,upperRight.y-i+1);
            this.grid.setHalignment(headerX, HPos.CENTER);
            for (int j = lowerLeft.x; j <= upperRight.x + 1; j++) {
                    if (j <= upperRight.x) {
                        VBox image=drawObject(new Vector2d(j, i));

                        if(image!=null){
                            this.grid.add(image,j-lowerLeft.x+ 1,upperRight.y-i+1);
                            this.grid.setHalignment(image, HPos.CENTER);
                        }else{
                            this.grid.add(new Text(" "),j-lowerLeft.x+ 1,upperRight.y-i+1);
                        }
                    }
            }

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
            this.grid.getColumnConstraints().add(new ColumnConstraints(25));
            this.grid.getRowConstraints().add(new RowConstraints(25));
            this.grid.setHalignment(headerX, HPos.CENTER);
        }
    }

    private VBox drawObject(Vector2d currentPosition) throws FileNotFoundException {
        if (this.map.isOccupied(currentPosition)) {
            Object object = this.map.objectAt(currentPosition);
            if (object != null) {
                GuiElementBox elBox=new GuiElementBox((IMapElement) object,currentPosition);
                return elBox.getBox();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
