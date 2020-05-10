package HR;

public abstract class Employee {
    protected int number;
    protected int salary;
    protected Company company;
    protected  float monthSalary;

    public Employee(int salary, Company company) {
        this.company = company;
        this.salary = salary;
        monthSalary = funcSalary(salary);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public abstract Employee clone();

    public abstract float funcSalary (int salary);

    public float getMonthSalary() {return monthSalary;}

}
