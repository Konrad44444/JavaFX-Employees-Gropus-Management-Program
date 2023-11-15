package zad.employees;

public class Employee implements Comparable<Employee> {
    private String imie;
    private String nazwisko;
    private EmployeeCondition stanPracownika;
    private Integer rokUrodzenia;
    private Double wynagrodzenie;

    public Employee(String imie, String nazwisko, EmployeeCondition stanPracownika, Integer rokUrodzenia,
            Double wynagrodzenie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanPracownika = stanPracownika;
        this.rokUrodzenia = rokUrodzenia;
        this.wynagrodzenie = wynagrodzenie;
    }

    @Override
    public int compareTo(Employee o) {
        return this.nazwisko.compareTo(o.getNazwisko());
    }

    public String getImie() {
        return this.imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return this.nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public EmployeeCondition getStanPracownika() {
        return this.stanPracownika;
    }

    public void setStanPracownika(EmployeeCondition stanPracownika) {
        this.stanPracownika = stanPracownika;
    }

    public Integer getRokUrodzenia() {
        return this.rokUrodzenia;
    }

    public void setRokUrodzenia(Integer rokUrodzenia) {
        this.rokUrodzenia = rokUrodzenia;
    }

    public Double getWynagrodzenie() {
        return this.wynagrodzenie;
    }

    public void setWynagrodzenie(Double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    @Override
    public String toString() {
        return "{" +
                " imie='" + getImie() + "'" +
                ", nazwisko='" + getNazwisko() + "'" +
                ", stanPracownika='" + getStanPracownika() + "'" +
                ", rokUrodzenia='" + getRokUrodzenia() + "'" +
                ", wynagrodzenie='" + getWynagrodzenie() + "'" +
                "}";
    }

}
