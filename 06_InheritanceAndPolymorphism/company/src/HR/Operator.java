package HR;

public class Operator extends Company implements Employee {
    private Integer monthSalary;
    @Override
    public int getMonthSalary() {
        return this.monthSalary;
    }

    @Override
    public Employee newEmployee() {
        return new Operator();
    }
}