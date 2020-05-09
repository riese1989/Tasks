package HR;

public class Manager extends Company implements Employee {
    private int number;
    private int salary;
    private Company company;

    public Manager (int salary, Company company)    {
        this.company = company;
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return salary + (company.getIncome() * 0.05 * Math.pow(Math.random(),3));
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
    public Manager clone() {
        return new Manager(salary, company);
    }


}
