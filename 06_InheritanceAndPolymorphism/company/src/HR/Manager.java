package HR;

public class Manager extends Employee {

    public Manager(int salary, Company company) {
        super(salary, company);
    }

    public double getMonthSalary() {
        return salary + (company.getIncome() * 0.05 * Math.pow(Math.random(),3));
    }

//    @Override
//    public double getMonthSalary() {
//        return salary + (company.getIncome() * 0.05 * Math.pow(Math.random(),3));
//    }


}
