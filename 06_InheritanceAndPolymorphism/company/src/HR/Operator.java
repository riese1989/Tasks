package HR;

public class Operator extends Employee {


    public Operator(int salary, Company company) {
        super(salary, company);
    }

    @Override
    public float funcSalary(int salary) {
        return salary;
    }
}