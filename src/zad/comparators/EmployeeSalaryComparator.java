package zad.comparators;

import java.util.Comparator;
import zad.employees.Employee;

public class EmployeeSalaryComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o1.getWynagrodzenie(), o2.getWynagrodzenie());
    }

}
