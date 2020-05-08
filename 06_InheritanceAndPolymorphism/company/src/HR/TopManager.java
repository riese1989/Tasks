package HR;

public class TopManager extends Company implements Employee {
    private Integer monthSalary;
    @Override
    public Integer getMonthSalary() {
        return this.monthSalary;
    }

    @Override
    public Employee newEmployee() {
        return new TopManager();
    }

}
