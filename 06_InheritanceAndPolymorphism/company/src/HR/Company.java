package HR;

import java.util.*;
import java.util.function.UnaryOperator;

public class Company {
    private int income;
    HashSet<Employee> list = new HashSet<Employee>();

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getIncome() {
        System.out.println();
        return income;
    }

    public void hire(Employee employee)  {
        employee.setNumber(list.size());
        list.add(employee);
    }

    public void hireAll (Employee employee, int count) {
        for (int i = 1; i <= count; i++)    {
            Employee employeeNew = employee.clone();
            employeeNew.setNumber(list.size());
            list.add(employeeNew);
        }
    }

    public void fire(int i)  {
        if (i < list.size())   {
            for (Employee employee: list)   {
                if (employee.getNumber() == i)  {
                    list.remove(employee);
                    break;
                }
            }
        }
        else    {
            System.out.println("Такого работника нет");
        }
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
