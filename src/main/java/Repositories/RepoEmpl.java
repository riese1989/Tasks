package Repositories;

import Employees.Employee;
import General.Options;

import java.util.ArrayList;

public class RepoEmpl implements Repo<Employee> {
    private ArrayList<Employee> employeesList = new ArrayList<>();

    @Override
    public boolean append(Employee employee)   {
        employeesList.add(employee);
        return true;
    }

    @Override
    public ArrayList<Employee> get() {
        Options options = new Options();
        return (ArrayList<Employee>) options.copy(employeesList);
    }
}
