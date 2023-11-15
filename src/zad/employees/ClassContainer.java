package zad.employees;

import java.util.List;

public interface ClassContainer {

    void addClass(String name, int capacity);

    void removeClass(String name);

    List<ClassEmployee> findEmpty();

    void summary();

    // --- Moje metody
    void addEmployeeToClass(String groupName, Employee employee);

    List<Employee> getEmployeesFromClass(String groupName);

    ClassEmployee getClassEmployeeByName(String groupName);

    void addClass(String name, ClassEmployee classEmployee);

    List<String> getGroupsNames();

}