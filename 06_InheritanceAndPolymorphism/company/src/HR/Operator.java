package HR;

public class Operator extends Company implements Employee {
    private int monthSalary;
    private int number;

    private Operator(int monthSalary) {
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
    public Operator clone() {
        return new Operator(this.monthSalary);
    }
}