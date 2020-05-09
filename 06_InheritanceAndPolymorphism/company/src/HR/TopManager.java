package HR;

public class TopManager extends Company implements Employee {
    private int salary;
    private int number;
    private Company company;

    public TopManager(int monthSalary, Company company) {
        this.company = company;
        this.salary = monthSalary;
    }

    @Override
    public double getMonthSalary() {
        if (company.getIncome() >= 10000000)  {
            double monthSalary = salary * 2.5;
            return monthSalary;
        }
        return salary;

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
    public TopManager clone() {
        return new TopManager(this.salary, this.company);
    }

}
