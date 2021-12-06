package gui;

import agh.ics.oop.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
        for (int i = upperRight.y + 1; i >= lowerLeft.y - 1; i--) {
            if (i == upperRight.y + 1) {
                System.out.println(i);
            }
//            builder.append(String.format("%3d: ", i));
//            for (int j = lowerLeft.x; j <= upperRight.x + 1; j++) {
//                if (i < lowerLeft.y || i > upperRight.y) {
//                    builder.append(drawFrame(j <= upperRight.x));
//                } else {
//                    builder.append(CELL_SEGMENT);
//                    if (j <= upperRight.x) {
//                        builder.append(drawObject(new Vector2d(j, i)));
//                    }
//                }
//            }
        }
    }



    private void drawHeader(Vector2d lowerLeft, Vector2d upperRight) {
        System.out.println("XD");
        Text header=new Text(" y\\x ");
        this.grid.add(header,0,0);
        for (int j = lowerLeft.x; j < upperRight.x + 1; j++) {
            this.grid.add(new Text(String.valueOf(j)),j+1,0);
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth( lowerLeft.x);
            this.grid.getColumnConstraints().add((new ColumnConstraints()).setPercentWidth(lowerLeft.x));

        }
    }

//    private String drawObject(Vector2d currentPosition) {
//        String result = null;
//        if (this.map.isOccupied(currentPosition)) {
//
//            Object object = this.map.objectAt(currentPosition);
//
//            if (object != null) {
//                result = object.toString();
//            } else {
//                result = EMPTY_CELL;
//            }
//        } else {
//            result = EMPTY_CELL;
//        }
//        return result;
//    }
}
