package myProject.model;

public class Request {
    private Integer numberVacationDays;
    private Double averageSalary;

    public Request (){}

    public Request(Integer numberVacationDays, Double averageSalary) {
        this.numberVacationDays = numberVacationDays;
        this.averageSalary = averageSalary;
    }

    public Integer getNumberVacationDays() {
        return numberVacationDays;
    }

    public void setNumberVacationDays(Integer userId) {
        this.numberVacationDays = userId;
    }

    public Double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(Double averageSalary) {
        this.averageSalary = averageSalary;
    }

    @Override
    public String toString() {
        return "Request{" +
                "average salary: " + averageSalary +
                '\'' + ", number vacation days=" + numberVacationDays +
                '}';
    }
}
