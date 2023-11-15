package zad.employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import zad.comparators.EmployeeLastnameComparator;
import zad.comparators.EmployeeSalaryComparator;

public class ClassEmployeeImpl implements ClassEmployee {
    String nazwaGrupy;
    int maxIlosc;
    List<Employee> pracownicy;

    public ClassEmployeeImpl(String nazwaGrupy, int maxIlosc) {
        this.nazwaGrupy = nazwaGrupy;
        this.maxIlosc = maxIlosc;
        this.pracownicy = new ArrayList<>();
    }

    @Override
    public String getNazwaGrupy() {
        return this.nazwaGrupy;
    }

    @Override
    public Integer getMaxIlosc() {
        return this.maxIlosc;
    }

    @Override
    public List<Employee> getPracownicy() {
        return this.pracownicy;
    }

    @Override
    public void addEmployee(Employee pracownik) {
        Optional<Employee> optional = findEmployee(pracownik);

        if (!optional.isPresent()) {
            if (pracownicy.size() < maxIlosc) {
                pracownicy.add(pracownik);
                System.out.println("Pracownik " + pracownik.getImie() + " " + pracownik.getNazwisko()
                        + " został dodany!");
            } else {
                System.out.println("Pracownik " + pracownik.getImie() + " " + pracownik.getNazwisko()
                        + " nie został dodany - limit wielkości grupy!");
            }
        } else {
            System.out.println("Pracownik " + pracownik.getImie() + " " + pracownik.getNazwisko()
                    + " nie został dodany - taki pracownik istieje!");
        }
    }

    @Override
    public void addSalary(Employee pracownik, double kwota) {
        Optional<Employee> optional = findEmployee(pracownik);

        if (optional.isPresent()) {
            Employee e = optional.get();
            e.setWynagrodzenie(kwota);
            System.out.println("Pracownikowi " + pracownik.getImie() + " " + pracownik.getNazwisko()
                    + " zostało dodane " + kwota + " do pensji!");
        } else {
            System.out.println("Pracownika " + pracownik.getImie() + " " + pracownik.getNazwisko()
                    + " nie ma w grupie - brak możliwości modyfikacji wynagrodzenia!");
        }
    }

    @Override
    public void removeEmployee(Employee pracownik) {
        if (pracownicy.remove(pracownik)) {
            System.out.println("Pracownik " + pracownik.getImie() + " " + pracownik.getNazwisko()
                    + " został usunięty z grupy!");
        } else {
            System.out.println("Pracownik " + pracownik.getImie() + " " + pracownik.getNazwisko()
                    + " nie został usunięty z grupy - takiego pracownika w niej nie było!");
        }
    }

    @Override
    public void changeCondition(Employee pracownik, EmployeeCondition stan) {
        Optional<Employee> optional = findEmployee(pracownik);

        if (optional.isPresent()) {
            Employee e = optional.get();
            EmployeeCondition tempStan = e.getStanPracownika();
            e.setStanPracownika(stan);

            System.out.println("Stan pracownika " + pracownik.getImie() + " " + pracownik.getNazwisko()
                    + " został zmieniony z " + tempStan + " na " + pracownik.getStanPracownika());
        } else {
            System.out.println("Pracownika " + pracownik.getImie() + " " + pracownik.getNazwisko()
                    + " nie ma w grupie - brak możliwości zmiany stanu!");
        }
    }

    @Override
    public Employee search(String nazwisko) {
        Employee o1 = new Employee(null, nazwisko, null, null, null);
        EmployeeLastnameComparator employeeComparator = new EmployeeLastnameComparator();

        for (Employee p : pracownicy) {
            if (employeeComparator.compare(p, o1) == 0) {
                return p;
            }
        }

        return null;
    }

    @Override
    public List<Employee> searchPartial(String nazwisko) {
        List<Employee> res = new ArrayList<>();

        for (Employee p : pracownicy) {
            if (p.getNazwisko().toLowerCase().contains(nazwisko.toLowerCase())) {
                res.add(p);
            }
        }

        return res;
    }

    @Override
    public int countByCondition(EmployeeCondition condition) {
        return (int) pracownicy.stream().filter(e -> e.getStanPracownika().equals(condition)).count();
    }

    @Override
    public void sumary() {
        System.out.println("Wszyscy pracownicy:");
        pracownicy.forEach(System.out::println);
    }

    @Override
    public List<Employee> sortByLastname() {
        List<Employee> p = new ArrayList<>(pracownicy);
        Collections.sort(p, Employee::compareTo);
        return p;
    }

    @Override
    public List<Employee> sortBySalary() {
        List<Employee> p = new ArrayList<>(pracownicy);
        Collections.sort(p, new EmployeeSalaryComparator());
        return p;
    }

    @Override
    public Employee max() {
        return Collections.max(pracownicy, new EmployeeSalaryComparator());
    }

    // --- moje metody ---
    private Optional<Employee> findEmployee(Employee pracownik) {
        return pracownicy.stream()
                .filter(e -> e.getNazwisko().equals(pracownik.getNazwisko()) && e.getImie().equals(pracownik.getImie()))
                .findFirst();
    }

    @Override
    public int getListSize() {
        return pracownicy.size();
    }

    @Override
    public double getFilling() {
        return (double) pracownicy.size() / maxIlosc * 100.00;
    }
}
