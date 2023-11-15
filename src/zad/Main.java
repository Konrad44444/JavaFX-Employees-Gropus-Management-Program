package zad;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import zad.employees.ClassContainer;
import zad.employees.ClassContainerImpl;
import zad.employees.ClassEmployeeImpl;
import zad.employees.Employee;
import zad.employees.EmployeeCondition;
import zad.gui.DisplayEmployees;
import zad.gui.EmployeesTableView;
import zad.gui.MyButton;
import zad.gui.MyLabel;
import zad.gui.ShowAlert;
import zad.handlers.AddEmployeeHandler;
import zad.handlers.AddGroupHandler;
import zad.handlers.EditEmployee;

public class Main extends Application {
    private static final int SIZE_X = 1080;
    private static final int SIZE_Y = 720;
    private static final int LABEL_SIZE = 100;
    private static final String TITLE = "Zadanie 3 - Konrad Warzecha";
    private static final String LABEL_STYLE = "-fx-border-style: solid; -fx-border-width: 2 2 2 2; -fx-border-color: gray; -fx-font-weight: bold;";
    private static final String DEFAULT_GROUP = "Pracownicy w grupie";

    private static ClassContainer container = new ClassContainerImpl();
    private static Employee selectedEmployee = null;
    private static String selectedGroup;
    private static BorderPane root = new BorderPane();

    public static void main(String[] args) {

        ClassEmployeeImpl grupa1 = new ClassEmployeeImpl("Nauczyciele", 5);
        Employee bulbek = new Employee("Bulbulezar", "Gąbka", EmployeeCondition.OBECNY, 1980, 3000.00);
        Employee jt = new Employee("Jan", "Tajemnik", EmployeeCondition.DELEGACJA, 1988, 3700.00);
        Employee ek = new Employee("Ewa", "Kowalska", EmployeeCondition.OBECNY, 1990, 4500.00);
        Employee pk = new Employee("Piotr", "Kowalewski", EmployeeCondition.CHORY, 1973, 3200.00);

        grupa1.addEmployee(bulbek);
        grupa1.addEmployee(jt);
        grupa1.addEmployee(ek);
        grupa1.addEmployee(pk);

        container.addClass(grupa1.getNazwaGrupy(), grupa1);
        container.addClass("Super grupa", 5);
        container.addClass("Średnia grupa", 4);
        container.addClass("Słaba grupa", 5);
        container.addClass("Słaba grupa", 10);

        container.addEmployeeToClass("Super grupa", jt);
        container.addEmployeeToClass("Super grupa", pk);
        container.addEmployeeToClass("Średnia grupa", ek);
        container.addEmployeeToClass("Średnia grupa", jt);
        container.addEmployeeToClass("Średnia grupa", pk);
        container.addEmployeeToClass("Słaba grupa", pk);

        launch(args);
    }

    // -----

    @Override
    public void start(Stage primaryStage) {
        selectedGroup = DEFAULT_GROUP;

        Node topView = getTopView();
        Node centerView = getCenterView();
        Node leftView = getLeftView();
        Node bottomView = getBottomView();

        root.setTop(topView);
        root.setLeft(leftView);
        root.setCenter(centerView);
        root.setBottom(bottomView);

        Scene scene = new Scene(root, SIZE_X, SIZE_Y);
        scene.setFill(Color.ANTIQUEWHITE);

        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private static Node getTopView() {
        var label = new MyLabel("Grupy pracownicze", Pos.BASELINE_CENTER);

        label.setPrefWidth(SIZE_X);
        label.setPrefHeight(LABEL_SIZE);
        label.setStyle(LABEL_STYLE + "-fx-font-size: 35px;");

        return label;
    }

    private static Node getLeftView() {

        Label label = new MyLabel("Grupy", Pos.BASELINE_CENTER);
        label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        label.setPrefWidth(2 * LABEL_SIZE);

        ListView<String> listView = new ListView<>();
        List<String> groups = container.getGroupsNames();
        System.out.println(groups);
        listView.setItems(FXCollections.observableArrayList(groups));

        listView.setStyle("-fx-font-size: 20px;");
        listView.setPrefWidth(2 * LABEL_SIZE);
        listView.setPrefHeight(0.72 * (SIZE_Y - 1.5 * LABEL_SIZE));

        listView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                selectedGroup = listView.getSelectionModel().getSelectedItem();

                System.out.println(selectedGroup);

                root.setCenter(null);
                root.setCenter(getCenterView());

            }
        });

        Button addGroupButton = new MyButton("Dodaj grupę");
        addGroupButton.setPrefWidth(1.75 * LABEL_SIZE);

        addGroupButton.setOnMouseClicked(new AddGroupHandler(container));

        Button deleteGroupButton = new MyButton("Usuń grupę");
        deleteGroupButton.setPrefWidth(1.75 * LABEL_SIZE);

        deleteGroupButton.setOnMouseClicked(e -> {
            if (selectedGroup.equals(DEFAULT_GROUP)) {
                ShowAlert.show("Nie wybrano grupy", AlertType.ERROR);
            } else {
                container.removeClass(selectedGroup);
                selectedGroup = DEFAULT_GROUP;
                refreshLeft();
                refreshCenter();

                ShowAlert.show("Grupa usunięta", AlertType.INFORMATION);
            }
        });

        VBox buttons = new VBox(10, addGroupButton, deleteGroupButton);
        buttons.setPrefWidth(2 * LABEL_SIZE);
        buttons.setAlignment(Pos.BASELINE_CENTER);

        VBox v = new VBox(6.5, label, listView, buttons);
        v.setPrefWidth(2 * LABEL_SIZE);
        v.setPrefHeight(SIZE_Y - 1.5 * LABEL_SIZE);
        v.setStyle(LABEL_STYLE);

        return v;
    }

    private static Node getCenterView() {
        selectedEmployee = null;

        TableView<Employee> table = EmployeesTableView.getTableView(SIZE_X - 2 * LABEL_SIZE, SIZE_Y - 3.5 * LABEL_SIZE);

        var label = new MyLabel(selectedGroup, Pos.BASELINE_CENTER);

        label.setPrefWidth(SIZE_X - 2 * LABEL_SIZE);
        label.setStyle("-fx-font-size: 25px;");

        var percentageLabel = new MyLabel("", Pos.BASELINE_CENTER);

        percentageLabel.setPrefWidth(SIZE_X - 2 * LABEL_SIZE);
        percentageLabel.setStyle("-fx-font-size: 20px;");

        if (!selectedGroup.equals(DEFAULT_GROUP)) {
            double percentage = container.getClassEmployeeByName(selectedGroup).getFilling();

            percentageLabel = new MyLabel("Poziom zapełnienia " + percentage + "%",
                    Pos.BASELINE_CENTER);
            percentageLabel.setPrefWidth(SIZE_X - 2 * LABEL_SIZE);
            percentageLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: green;");

            if (percentage >= 70.0 && percentage < 100.0) {
                percentageLabel.setStyle("-fx-text-fill: orange; -fx-font-size: 15px;");
            } else if (percentage == 100) {
                percentageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 15px;");
            }

            List<Employee> employees = container.getEmployeesFromClass(selectedGroup);
            table.getItems().addAll(employees);

            table.setRowFactory(t -> {
                TableRow<Employee> row = new TableRow<>();

                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                        selectedEmployee = row.getItem();

                        System.out.println(selectedEmployee.getNazwisko());

                    } else if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY) {
                        selectedEmployee = row.getItem();
                        EditEmployee editEmployee = new EditEmployee(selectedEmployee);
                        editEmployee.edit();
                    }
                });

                return row;
            });
        }

        Button addEmployeeButton = new MyButton("Dodaj pracownika");
        addEmployeeButton.setOnMouseClicked(new AddEmployeeHandler(container, selectedGroup));

        Button editEmployeeButton = new MyButton("Edytuj pracownika");
        editEmployeeButton.setOnMouseClicked(e -> {
            EditEmployee editEmployee = new EditEmployee(selectedEmployee);
            editEmployee.edit();
        });

        Button removeEmployeeButton = new MyButton("Usuń pracownika");
        removeEmployeeButton.setOnMouseClicked(e -> {
            if (selectedEmployee == null) {

                ShowAlert.show("Nie wybrano pracownika", AlertType.ERROR);

            } else {
                container.getClassEmployeeByName(selectedGroup).removeEmployee(selectedEmployee);
                refreshCenter();

                ShowAlert.show("Pracownik usunięcy", AlertType.INFORMATION);
            }

        });

        addEmployeeButton.setPrefWidth(2 * LABEL_SIZE);
        removeEmployeeButton.setPrefWidth(2 * LABEL_SIZE);

        TextField find = new TextField();
        find.setPromptText("Wyszukaj pracowników");
        find.setStyle("-fx-font-size: 17.5px;");
        find.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.ENTER) {

                if (selectedGroup.equals(DEFAULT_GROUP)) {

                    ShowAlert.show("Nie wybrano grupy", AlertType.ERROR);

                } else {

                    List<Employee> l = container.getClassEmployeeByName(selectedGroup).searchPartial(find.getText());

                    if (l.isEmpty()) {
                        ShowAlert.show("Nie znaleziono pracowników", AlertType.ERROR);
                    } else {
                        DisplayEmployees.show(l);
                        find.clear();
                        root.requestFocus();
                    }

                }

            }

        });

        HBox h = new HBox(10, addEmployeeButton, editEmployeeButton, removeEmployeeButton, find);
        h.setAlignment(Pos.BASELINE_CENTER);

        VBox v = new VBox(20, label, percentageLabel, table, h);
        v.setStyle(LABEL_STYLE);

        return v;
    }

    private static Node getBottomView() {
        var label = new MyLabel("Konrad Warzecha, 2023", Pos.BASELINE_CENTER);

        label.setPrefWidth(SIZE_X);
        label.setPrefHeight(LABEL_SIZE / 2);
        label.setStyle(LABEL_STYLE + "-fx-font-size: 15px;");

        return label;
    }

    public static void refreshCenter() {
        root.setCenter(null);
        root.setCenter(getCenterView());
    }

    public static void refreshLeft() {
        root.setLeft(null);
        root.setLeft(getLeftView());
        selectedGroup = DEFAULT_GROUP;
        refreshCenter();
    }

}
