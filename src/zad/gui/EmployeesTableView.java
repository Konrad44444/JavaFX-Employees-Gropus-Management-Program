package zad.gui;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import zad.employees.Employee;
import zad.employees.EmployeeCondition;

public class EmployeesTableView {

    private EmployeesTableView() {
    }

    /**
     * @param width  szerokość tabeli
     * @param height wysokość tabeli
     * @return pusta tabela z kolumnami przedstawiającymi pola klasy Employee
     */
    public static TableView<Employee> getTableView(double width, double height) {
        TableView<Employee> table = new TableView<>();

        TableColumn<Employee, String> nameColumn = new TableColumn<>("Imie");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));

        TableColumn<Employee, String> surnameColumn = new TableColumn<>("Nazwisko");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));

        TableColumn<Employee, EmployeeCondition> conditionColumn = new TableColumn<>("Stan pracownika");
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("stanPracownika"));

        TableColumn<Employee, Integer> ageColumn = new TableColumn<>("Rok urodzenia");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("rokUrodzenia"));

        TableColumn<Employee, Double> salaryColumn = new TableColumn<>("Wynagrodzenie");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("wynagrodzenie"));

        table.setPrefHeight(height);
        table.setPrefWidth(width);
        table.setStyle("-fx-font-size: 15px;");

        nameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.19));
        surnameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        ageColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        conditionColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        salaryColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));

        table.getColumns().addAll(nameColumn, surnameColumn, ageColumn, conditionColumn, salaryColumn);

        return table;
    }
}
