package app.repositories;

import app.pojo.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class RepoEmpl implements Repo<Employee> {

  private ArrayList<Employee> employeesList = new ArrayList<>();
  private ApplicationContext context;

  public RepoEmpl(@Autowired ApplicationContext context) {
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

  @Override
  public int size() {
    return employeesList.size();
  }

  public Employee getCurrentEmployee() {
    for (Employee employee : employeesList) {
      if (employee.getIsCurrent()) {
        return employee;
      }
    }
    Employee employee = context.getBean(Employee.class);
    employee.setField("countTaskOne", 0)
        .setField("family", "")
        .setField("vacations", new HashMap<>())
        .setField("status", false)
        .setField("isCurrent", false);
    return employee;
  }
}
