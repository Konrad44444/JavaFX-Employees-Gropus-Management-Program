package zad.employees;

import java.util.List;

public interface ClassEmployee {

    String getNazwaGrupy();

    Integer getMaxIlosc();

    List<Employee> getPracownicy();

    void addEmployee(Employee pracownik);

    void addSalary(Employee pracownik, double kwota);

    void removeEmployee(Employee pracownik);

    void changeCondition(Employee pracownik, EmployeeCondition stan);

    Employee search(String nazwisko);

    List<Employee> searchPartial(String nazwisko);

    int countByCondition(EmployeeCondition condition);

    void sumary();

    List<Employee> sortByLastname();

    List<Employee> sortBySalary();

    Employee max();

    int getListSize();

    double getFilling();

}