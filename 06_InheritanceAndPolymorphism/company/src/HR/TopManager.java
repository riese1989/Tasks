package HR;

public class TopManager extends Employee {
    public TopManager(int salary, Company company) {
        super(salary, company);
    }

    @Override
    public TopManager clone() {
        return new TopManager(salary, company);
    }

    @Override
    public double funcSalary(int salary) {
        if (company.getIncome() >= 10000000)    {
            monthSalary = salary * 2;
        }
        else    {
            monthSalary = salary;
        }
        return monthSalary;
    }
}
