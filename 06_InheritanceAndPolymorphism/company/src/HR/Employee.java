package HR;

public interface Employee {
    float getMonthSalary(int bonus);
    int getNumber();
    void setNumber(int number);
    Employee clone ();
}
