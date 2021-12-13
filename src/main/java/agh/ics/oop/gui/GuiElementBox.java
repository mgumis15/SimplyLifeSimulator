package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import agh.ics.oop.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    protected Image image;
    protected ImageView imageView;
    protected Label etykieta;
    public VBox box;
    public GuiElementBox(IMapElement src, Vector2d position) throws FileNotFoundException {
        this.image=new Image(new FileInputStream(src.getUrl()));
        this.imageView=new ImageView(this.image);
        this.imageView.setFitWidth(20);
        this.imageView.setFitHeight(20);
        this.etykieta=new Label(position.toString());
        this.box=new VBox(this.etykieta,this.imageView);
        this.box.setAlignment(Pos.BASELINE_CENTER);
    }
    public VBox getBox(){
        return this.box;
    }
}
