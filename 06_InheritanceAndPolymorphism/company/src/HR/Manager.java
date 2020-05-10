package HR;

public class Manager extends Employee {

    public Manager(int salary, Company company) {
        super(salary, company);
        monthSalary = salary + (company.getIncome() * 0.05 * Math.pow(Math.random(),3));
    }

    @Override
    public Manager clone() {
        return new Manager(salary, company);
    }
}
