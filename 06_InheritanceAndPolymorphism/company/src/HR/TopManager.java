package HR;

public class TopManager extends Company implements Employee {
    private Integer monthSalary;
    @Override
    public int getMonthSalary() {
        return this.monthSalary;
    }

    @Override
    public Employee newEmployee() {
        return new TopManager();
    }

}
