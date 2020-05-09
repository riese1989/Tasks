package HR;

public class Manager extends Company implements Employee {
    private int number;
    private int monthSalary;

    public Manager (int monthSalary)    {
        this.monthSalary = monthSalary;
    }

    @Override
    public float getMonthSalary(int bonus) {
        return this.monthSalary;
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
        return new Manager(this.monthSalary);
    }


}
