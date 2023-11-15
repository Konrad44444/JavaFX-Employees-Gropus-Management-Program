package zad.handlers;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import zad.Main;
import zad.employees.Employee;
import zad.employees.EmployeeCondition;
import zad.gui.MyButton;
import zad.gui.MyLabel;

public class EditEmployee {
    private static final Integer WIDTH = 600;
    private static final Integer HEIGHT = 360;

    private Employee employee;

    public EditEmployee(Employee e) {
        this.employee = e;
    }

    public void edit() {
        Stage stage = new Stage();
        StackPane root = new StackPane();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.ANTIQUEWHITE);

        stage.setScene(scene);
        stage.setTitle("Edytownie pracownika");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.centerOnScreen();

        if (employee == null) {
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Nie wybrano pracownika");
            a.show();
        } else {
            Label nLabel = new MyLabel("Imię", Pos.BASELINE_CENTER);

            TextField nameField = new TextField();
            nameField.setText(employee.getImie());

            HBox nameBox = new HBox(15);

            nLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
            nLabel.prefWidthProperty().bind(nameBox.widthProperty().multiply(0.25));
            nameField.prefWidthProperty().bind(nameBox.widthProperty().multiply(0.7));

            nameBox.getChildren().addAll(nLabel, nameField, new Label());

            // -----
            Label sLabel = new MyLabel("Nazwisko", Pos.BASELINE_CENTER);
            TextField surnameField = new TextField();
            surnameField.setText(employee.getNazwisko());

            HBox surnameBox = new HBox(15);

            sLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
            sLabel.prefWidthProperty().bind(surnameBox.widthProperty().multiply(0.25));
            surnameField.prefWidthProperty().bind(surnameBox.widthProperty().multiply(0.7));

            surnameBox.getChildren().addAll(sLabel, surnameField, new Label());

            // -----
            Label aLabel = new MyLabel("Rok urodzenia", Pos.BASELINE_CENTER);
            TextField ageField = new TextField();
            ageField.setText(employee.getRokUrodzenia().toString());

            HBox ageBox = new HBox(15);

            aLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
            aLabel.prefWidthProperty().bind(ageBox.widthProperty().multiply(0.25));
            ageField.prefWidthProperty().bind(ageBox.widthProperty().multiply(0.7));

            ageBox.getChildren().addAll(aLabel, ageField, new Label());

            // -----
            Label salaryLabel = new MyLabel("Wynagrodzenie", Pos.BASELINE_CENTER);
            TextField salaryField = new TextField();
            salaryField.setText(employee.getWynagrodzenie().toString());

            HBox salaryBox = new HBox(15);

            salaryLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
            salaryLabel.prefWidthProperty().bind(salaryBox.widthProperty().multiply(0.25));
            salaryField.prefWidthProperty().bind(salaryBox.widthProperty().multiply(0.7));

            salaryBox.getChildren().addAll(salaryLabel, salaryField, new Label());

            // -----
            Label cLabel = new MyLabel("Stan pracownika", Pos.BASELINE_CENTER);
            ComboBox<EmployeeCondition> conditions = new ComboBox<>();
            conditions.setItems(FXCollections.observableArrayList(EmployeeCondition.values()));
            conditions.setValue(employee.getStanPracownika());

            HBox cBox = new HBox(15);

            cLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
            cLabel.prefWidthProperty().bind(cBox.widthProperty().multiply(0.25));
            conditions.prefWidthProperty().bind(cBox.widthProperty().multiply(0.7));

            cBox.getChildren().addAll(cLabel, conditions, new Label());

            // -----

            Button addBtn = new MyButton("Zapisz");
            Button closeBtn = new MyButton("Anuluj");

            HBox buttons = new HBox(40, addBtn, closeBtn);
            buttons.setPrefWidth(WIDTH);
            buttons.setAlignment(Pos.BASELINE_CENTER);

            addBtn.setOnMouseClicked(e -> {
                employee.setImie(nameField.getText());
                employee.setNazwisko(surnameField.getText());
                employee.setRokUrodzenia(Integer.valueOf(ageField.getText()));
                employee.setWynagrodzenie(Double.valueOf(salaryField.getText()));
                employee.setStanPracownika(conditions.getValue());

                Main.refreshCenter();
                stage.close();
            });

            closeBtn.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    stage.close();
                }
            });

            Label title = new MyLabel("Edytownie pracownika - " + employee.getImie() + " " + employee.getNazwisko(),
                    Pos.BASELINE_CENTER);
            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            title.setPrefWidth(WIDTH);

            VBox v = new VBox(20, title, nameBox, surnameBox, ageBox, salaryBox, cBox, buttons);
            v.setStyle("-fx-border-style: solid; -fx-border-width: 2 2 2 2; -fx-border-color: gray;");

            root.getChildren().add(v);

            stage.show();
        }

    }

}
