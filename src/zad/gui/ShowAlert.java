package zad.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShowAlert {

    private ShowAlert() {
    }

    public static void show(String info, AlertType type) {
        Alert a = new Alert(type);
        a.setHeaderText(info);
        a.show();
    }
}
