package HR;

public abstract class Employee {
    protected int number;
    protected int salary;
    protected Company company;
    protected  double monthSalary;

    public Employee(int salary, Company company) {
        this.company = company;
        this.salary = salary;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public abstract Employee clone();

    public double getMonthSalary() {return monthSalary;}

}
