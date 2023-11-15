package zad.gui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import zad.employees.Employee;

public class DisplayEmployees {
    private static final Integer WIDTH = 660;
    private static final Integer HEIGHT = 360;

    private DisplayEmployees() {
    }

    public static void show(List<Employee> list) {
        Stage stage = new Stage();
        StackPane root = new StackPane();

        Scene scene = new Scene(root);
        scene.setFill(Color.ANTIQUEWHITE);

        stage.setScene(scene);
        stage.setTitle("Edytownie pracownika");
        stage.setResizable(true);
        stage.setAlwaysOnTop(true);
        stage.centerOnScreen();

        VBox v = new VBox(20);
        v.setPrefHeight(HEIGHT);
        v.setPrefWidth(WIDTH);
        v.setStyle(
                "-fx-border-style: solid; -fx-border-width: 2 2 2 2; -fx-border-color: gray; -fx-font-weight: bold; -fx-font-size: 15px;");

        Label title = new MyLabel("Znalezieni pracownicy", Pos.BASELINE_CENTER);
        title.setStyle("-fx-font-size: 25px");
        title.setPrefWidth(WIDTH);
        v.getChildren().add(title);

        TableView<Employee> table = EmployeesTableView.getTableView(WIDTH, 0.8 * HEIGHT);
        table.setItems(FXCollections.observableArrayList(list));

        HBox h = new HBox(10, new Label(), table, new Label());

        v.getChildren().add(h);

        v.getChildren().add(new Label());

        root.getChildren().add(v);

        stage.show();
    }
}
