package zad.handlers;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import zad.Main;
import zad.employees.ClassContainer;
import zad.gui.MyButton;
import zad.gui.MyLabel;

public class AddGroupHandler implements EventHandler<MouseEvent> {
    private static final Integer WIDTH = 600;
    private static final Integer HEIGHT = 360;

    private ClassContainer container;

    public AddGroupHandler(ClassContainer container) {
        this.container = container;
    }

    @Override
    public void handle(MouseEvent arg0) {
        Stage stage = new Stage();
        StackPane root = new StackPane();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.ANTIQUEWHITE);

        stage.setScene(scene);
        stage.setTitle("Edytownie pracownika");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.centerOnScreen();

        Label nLabel = new MyLabel("Nazwa grupy", Pos.BASELINE_CENTER);

        TextField nameField = new TextField();
        nameField.setPromptText("Nazwa grupy");

        HBox nameBox = new HBox(15);

        nLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        nLabel.prefWidthProperty().bind(nameBox.widthProperty().multiply(0.25));
        nameField.prefWidthProperty().bind(nameBox.widthProperty().multiply(0.7));

        nameBox.getChildren().addAll(nLabel, nameField, new Label());

        // -----
        Label capLabel = new MyLabel("Pojemność", Pos.BASELINE_CENTER);
        TextField capField = new TextField();
        capField.setPromptText("Pojemność");

        HBox capBox = new HBox(15);

        capLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        capLabel.prefWidthProperty().bind(capBox.widthProperty().multiply(0.25));
        capField.prefWidthProperty().bind(capBox.widthProperty().multiply(0.7));

        capBox.getChildren().addAll(capLabel, capField, new Label());

        Button addBtn = new MyButton("Zapisz");
        Button closeBtn = new MyButton("Anuluj");

        HBox buttons = new HBox(40, addBtn, closeBtn);
        buttons.setPrefWidth(WIDTH);
        buttons.setAlignment(Pos.BASELINE_CENTER);

        addBtn.setOnMouseClicked(e -> {
            boolean nameGood = true;
            boolean capGood = true;

            if (nameField.getText().equals("")) {
                nameField.setStyle("-fx-border-color: red;");
                nameGood = false;
            }

            if (capField.getText().equals("") || Integer.valueOf(capField.getText()) < 0) {
                capField.setStyle("-fx-border-color: red;");
                capGood = false;
            }

            if (nameGood && capGood) {
                String name = nameField.getText();
                Integer capacity = Integer.valueOf(capField.getText());

                container.addClass(name, capacity);

                Main.refreshLeft();
                stage.close();
            }
        });

        closeBtn.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                stage.close();
            }
        });

        Label title = new MyLabel("Dodawanie grupy pracowniczej ",
                Pos.BASELINE_CENTER);
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        title.setPrefWidth(WIDTH);

        VBox v = new VBox(20, title, nameBox, capBox, buttons);
        v.setStyle("-fx-border-style: solid; -fx-border-width: 2 2 2 2; -fx-border-color: gray;");

        root.getChildren().add(v);

        stage.show();
    }

}
