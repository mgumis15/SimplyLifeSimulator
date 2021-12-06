package gui;

import agh.ics.oop.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.text.Text;

public class GridMapVisualizer {
    private IWorldMap map;
    private GridPane grid;


    public GridMapVisualizer(IWorldMap map, GridPane grid) {
        this.map = map;
        this.grid=grid;
    }

    public void draw(Vector2d lowerLeft, Vector2d upperRight) {

        this.drawHeader( lowerLeft,  upperRight);
        for (int i = upperRight.y; i >= lowerLeft.y ; i--) {
            Text headerX=new Text(String.valueOf(i));
            this.grid.add(headerX,0,upperRight.y-i+1);
            this.grid.setHalignment(headerX, HPos.CENTER);
            for (int j = lowerLeft.x; j <= upperRight.x + 1; j++) {
                    if (j <= upperRight.x) {
                        Text object=new Text(drawObject(new Vector2d(j, i)));
                        this.grid.add(object,j-lowerLeft.x+ 1,upperRight.y-i+1);
                        this.grid.setHalignment(object, HPos.CENTER);
                    }
            }

        }
    }

    private void drawHeader(Vector2d lowerLeft, Vector2d upperRight) {
        Text header=new Text(" y\\x ");
        this.grid.getColumnConstraints().add(new ColumnConstraints(25));
        this.grid.add(header,0,0);
        this.grid.setHalignment(header, HPos.CENTER);
        for (int j = lowerLeft.x; j < upperRight.x +1; j++) {
            Text headerX=new Text(String.valueOf(j));
            this.grid.add(headerX,j-lowerLeft.x+1,0);
            this.grid.getColumnConstraints().add(new ColumnConstraints(15));
            this.grid.setHalignment(headerX, HPos.CENTER);
        }
    }

    private String drawObject(Vector2d currentPosition) {
        String result = null;
        if (this.map.isOccupied(currentPosition)) {
            Object object = this.map.objectAt(currentPosition);
            if (object != null) {
                result = object.toString();
            } else {
                result = " ";
            }
        } else {
            result = " ";
        }
        return result;
    }
}
