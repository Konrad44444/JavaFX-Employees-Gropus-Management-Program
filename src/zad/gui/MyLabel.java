package zad.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class MyLabel extends Label {
    public MyLabel(String text, Pos alignment) {
        super(text);
        setAlignment(alignment);
    }
}
