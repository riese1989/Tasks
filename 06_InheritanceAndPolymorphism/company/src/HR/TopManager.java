package HR;

public class TopManager extends Employee {
    public TopManager(int salary, Company company) {
        super(salary, company);
    }

    public double getMonthSalary() {
        return salary *2;
    }

//    @Override
//    public double getMonthSalary() {
//        if (company.getIncome() >= 10000000)  {
//            double monthSalary = salary * 2.5;
//            return monthSalary;
//        }
//        return salary;
//
//    }

}
