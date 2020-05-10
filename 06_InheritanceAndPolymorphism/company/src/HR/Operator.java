package HR;

public class Operator extends Employee {


    public Operator(int salary, Company company) {
        super(salary, company);
        monthSalary = salary;
    }

    @Override
    public Operator clone() {
        return new Operator(salary, company);
    }
}