package HR;

public class Operator extends Employee {


    public Operator(int salary, Company company) {
        super(salary, company);
    }

    public double getMonthSalary() {
        return salary;
    }

//    @Override
//    public double getMonthSalary() {
//        return this.salary;
//    }

}