package app.Repositories;

import app.Employees.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RepoEmpl implements Repo<Employee> {

  private ArrayList<Employee> employeesList = new ArrayList<>();
  private ApplicationContext context;

  public RepoEmpl(@Autowired ApplicationContext context)  {
    this.context = context;
  }

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
    Employee employee = context.getBean(Employee.class);
    employee.setFields(0,"", new HashMap<>(),false, false);
    return employee;
  }
}
