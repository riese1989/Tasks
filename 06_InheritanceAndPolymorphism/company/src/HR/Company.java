package HR;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.UnaryOperator;

public class Company {
    private int income;
    ArrayList<Employee> list = new ArrayList<>();

    public void setIncome(int income) {
        this.income = income;
    }


    public int getIncome() {
        return this.income;
    }

    public void hire(Employee employee) {
        employee.setNumber(list.size());
        list.add(employee);
    }

    public void hireAll(Employee employee, int count) {
        for (int i = 1; i <= count; i++) {
            Employee employeeNew = employee.clone();
            employeeNew.setNumber(list.size());
            list.add(employeeNew);
        }
    }

    public void fire(int i) {
        if (i < list.size()) {
            for (Employee employee : list) {
                if (employee.getNumber() == i) {
                    list.remove(employee);
                    break;
                }
            }
        } else {
            System.out.println("Такого работника нет");
        }
    }

    public int size() {
        return list.size();
    }

    public void listEmpoeeys() {
        for (Employee employee : list) {
            System.out.println(employee.getNumber() + " " + employee.getMonthSalary());
        }
    }

    public List<Employee> getTopSalaryStaff(int count) {
        SalaryStaff salaryStaff = new SalaryStaff();
        ArrayList<Employee> listSorts = new ArrayList<Employee>();
        listSorts = list;
        listSorts.sort(SalaryStaff.getTopSalaryStaff());
        int i = 1;
        System.out.println(count + " самых больших зарплат");
        for (Employee listSort : listSorts) {
            if (i <= count) {
                System.out.println(i + ". "+listSort.getMonthSalary());
            }
            i++;
        }
        return listSorts;
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        SalaryStaff salaryStaff = new SalaryStaff();
        ArrayList<Employee> listSorts = new ArrayList<Employee>();
        listSorts = list;
        listSorts.sort(SalaryStaff.getLowestSalaryStaff());
        int i = 1;
        System.out.println(count + " самых маленьких зарплат");
        for (Employee listSort : listSorts) {
            if (i <= count) {
                System.out.println(i + ". "+listSort.getMonthSalary());
            }
            i++;
        }
        return listSorts;
    }

}

