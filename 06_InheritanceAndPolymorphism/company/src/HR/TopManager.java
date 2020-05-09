package HR;

public class TopManager extends Company implements Employee {
    private int monthSalary;
    private int number;

    private TopManager(int monthSalary) {
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
    public TopManager clone() {
        return new TopManager(this.monthSalary);
    }

}
