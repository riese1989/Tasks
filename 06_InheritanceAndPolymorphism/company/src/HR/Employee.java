package HR;

public class Employee {
    protected int number;
    protected int salary;
    protected Company company;

    public Employee (int salary, Company company)
    {
        this.company = company;
        this.salary = salary;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Employee clone() {
        return new Employee(salary, company);
    }

    public double getMonthSalary() {
        return 0;
    }
}
