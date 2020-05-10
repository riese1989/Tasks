package HR;

public class Manager extends Employee {

    public Manager(int salary, Company company) {
        super(salary, company);
    }

    @Override
    public Manager clone() {
        return new Manager(salary, company);
    }

    @Override
    public double funcSalary(int salary) {
        return salary + (company.getIncome() * 0.05 * Math.pow(Math.random(),3));
    }
}
