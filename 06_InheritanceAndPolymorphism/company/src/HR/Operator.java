package HR;

public class Operator extends Company implements Employee {
    private Integer monthSalary;
    @Override
    public Integer getMonthSalary() {
        return this.monthSalary;
    }

    @Override
    public Employee newEmployee() {
        return new Operator();
    }
}