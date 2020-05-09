package HR;

public class Operator extends Company implements Employee {
    private int salary;
    private int number;
    private Company company;

    public Operator(int salary, Company company) {
        this.company = company;
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return this.salary;
    }


    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Operator clone() {
        return new Operator(this.salary, this.company);
    }
}