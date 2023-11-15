package zad.comparators;

import java.util.Comparator;

import zad.employees.Employee;

public class EmployeeLastnameComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getNazwisko().compareTo(o2.getNazwisko());
    }

}
