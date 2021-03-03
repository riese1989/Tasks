package app.Employees;

import app.Application;
import app.General.JSONOperations;
import app.General.Operations;
import app.Repositories.Access;
import app.Repositories.Repo;
import app.Repositories.RepoEmpl;
import app.Repositories.RepoTasks;
import app.Tasks.AccessRepo;
import app.Tasks.OperationsTask;
import app.Tasks.Task;
import app.Tasks.TaskStatus;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import static app.General.Operations.compareDate;

@Component
public class OperationsEmployee {

  private ApplicationContext context;
  private RepoEmpl repoEmpl;
  private RepoTasks repoTasks;

  public OperationsEmployee(@Autowired ApplicationContext context)  {
    this.context = context;
    repoEmpl = context.getBean(RepoEmpl.class);
    repoTasks = context.getBean(RepoTasks.class);
  }

  public Integer getCounter() {
    return counter();
  }

  public boolean readEmployeesFromJSON() {
    try {
      JSONOperations jsonOperations = context.getBean(JSONOperations.class);
      jsonOperations.JSONToArrayEmployee();
      return true;
    } catch (ParseException | java.text.ParseException | IOException ex) {
      ex.printStackTrace();
      return false;
    }
  }

  private Integer counter() {
    Integer count = 0;
    Employee employee = repoEmpl.getCurrentEmployee();
    for (Task task : repoTasks.get()) {
      if (task.getAssigned() == employee && compareDate(task.getDateResolved())
          && task.getStatus() != TaskStatus.NOTE_DONE) {
        count++;
      }
    }
    return count;
  }

  public Employee getEmployee(String family) {
    for (Employee employee : repoEmpl.get()) {
      if (employee.getFamily().equals(family)) {
        return employee;
      }
    }
    Employee employee = context.getBean(Employee.class);
    employee.setFields(0, family, null, true, false);
    return employee;
  }

  public boolean stat() {
    System.out.println("\nЗа сегодня назначено");
    for (Employee employee : repoEmpl.get()) {
      if (employee.getStatus()) {
        long count = countAssignTaskNow(employee);
        System.out.println(employee.getFamily() + " " + count);
      }
    }
    System.out.println("\nОбщий счёт");
    for (Employee employee : repoEmpl.get()) {
      long countTasks = repoTasks.get().stream()
          .filter(t -> t.getAssigned().getFamily().equals(employee.getFamily())).count();
      System.out.println(employee.getFamily() + " " + countTasks);
    }
    System.out.println("\nВсего назначено обращений " + repoTasks.get().size() + "\n");
    return true;
  }

  private long countAssignTaskNow(Employee employee) {
    OperationsTask operationsTask = context.getBean(OperationsTask.class);
    long count = 0;
    for (Task task : repoTasks.get()) {
      if (task.getAssigned().getFamily().equals(employee.getFamily()) && operationsTask
          .searchInHistory(task)) {
        count++;
      }
    }
    return count;
  }

  public Employee tasksOfAuthor(String author) {
    for (Employee employee : repoEmpl.get()) {
      Long count = repoTasks.get().stream()
          .filter(t -> t.getAssigned() == employee && t.getAuthor().equals(author)).count();
      employee.setCountTasksOfAuthor(count);
    }
    Stream<Employee> stream = repoEmpl.get().stream();
    Employee empl = stream.max(Comparator.comparing(Employee::getCountTasksOfAuthor)).get();
    return empl;
  }
}
