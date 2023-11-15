package zad.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassContainerImpl implements ClassContainer {
    private Map<String, ClassEmployee> grupy;

    public ClassContainerImpl() {
        grupy = new HashMap<>();
    }

    @Override
    public void addClass(String name, int capacity) {
        if (grupy.containsKey(name)) {
            System.out.println(
                    "Nie można dodać do zbioru grupy o nazwie '" + name + "' - grupa o takiej nazwie już istnieje!");
        } else {
            grupy.put(name, new ClassEmployeeImpl(name, capacity));
            System.out.println("Dodano grupę o nazwie '" + name + "' o pojemności " + capacity + " do zbioru grup!");
        }
    }

    @Override
    public void removeClass(String name) {
        ClassEmployee removed = grupy.remove(name);

        if (removed != null) {
            System.out.println("Usunięto ze zbioru grupę o nazwie '" + name + "'!");
        } else {
            System.out.println("Usuwanie grupy nie powiodło się - grupy o nazwie '" + name + "' nie było w zbiorze!");
        }
    }

    @Override
    public List<ClassEmployee> findEmpty() {
        List<ClassEmployee> res = new ArrayList<>();

        for (ClassEmployee e : grupy.values()) {
            if (e.getListSize() == 0)
                res.add(e);
        }

        return res;
    }

    @Override
    public void summary() {
        System.out.println("Grupy w klasie ClassContainer:");

        for (String name : grupy.keySet()) {
            System.out.println("Grupa o nazwie " + name + " i zapełnieniu " + grupy.get(name).getFilling() + "%");
        }
    }

    // --- Moje metody
    @Override
    public void addEmployeeToClass(String groupName, Employee employee) {
        if (grupy.containsKey(groupName)) {
            System.out.println("Dodawanie pracownika do grupy '" + groupName + "'!");
            grupy.get(groupName).addEmployee(employee);
        } else {
            System.out.println("Nie można dodać pracownika do grupy '" + groupName +
                    "', ponieważ taka grupa nie istnieje!");
        }

    }

    @Override
    public List<Employee> getEmployeesFromClass(String groupName) {
        return grupy.get(groupName).getPracownicy();
    }

    @Override
    public ClassEmployee getClassEmployeeByName(String groupName) {
        return grupy.get(groupName);
    }

    @Override
    public void addClass(String name, ClassEmployee classEmployee) {
        if (grupy.containsKey(name)) {
            System.out.println(
                    "Nie można dodać do zbioru grup grupy o nazwie '" + name
                            + "' - grupa o takiej nazwie już istnieje!");
        } else {
            grupy.put(name, classEmployee);
            System.out.println("Dodano grupę o nazwie '" + name + "'!");
        }
    }

    @Override
    public List<String> getGroupsNames() {
        return grupy.keySet().stream().collect(Collectors.toList());
    }

}
