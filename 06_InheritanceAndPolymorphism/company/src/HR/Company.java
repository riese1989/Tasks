package HR;

import java.util.*;
import java.util.function.UnaryOperator;

public class Company {
    private Integer income;
    LinkedHashSet<Employee> list = new LinkedHashSet<>();

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getIncome() {
        System.out.println();
        return income;
    }

    public void hire(Employee employee)  {
        list.add(employee);
    }

    public void hireAll (Employee employee, int count) {
        for (int i = 1; i <= count; i++)    {
            list.add(employee.newEmployee());
        }
    }

    public void fire(Integer i)  {
    }
    public Integer size ()  {
        return list.size();
    }

    public List<Employee> getTopSalaryStaff(int count)  {
        return null;
    }
    List<Employee> getLowestSalaryStaff(int count)  {
        return null;
    }
}
