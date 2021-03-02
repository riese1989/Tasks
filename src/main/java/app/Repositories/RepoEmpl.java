package app.Repositories;

import app.Employees.Employee;

import java.util.ArrayList;
import java.util.HashMap;

public class RepoEmpl implements Repo<Employee> {

  private static ArrayList<Employee> employeesList = new ArrayList<>();

  @Override
  public boolean append(Employee employee) {
    employeesList.add(employee);
    return true;
  }

  @Override
  public ArrayList<Employee> get() {
    return employeesList;
  }

  public Employee getCurrentEmployee()  {
    for (Employee employee : employeesList) {
      if (employee.getIsCurrent())  {
        return employee;
      }
    }
    return new Employee(0,"", new HashMap<>(),false, false);
  }
}
