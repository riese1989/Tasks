package app.Employees;

import app.General.JSONOperations;
import app.Repositories.Access;
import app.Repositories.Repo;
import app.Tasks.AccessRepo;
import app.Tasks.OperationsTask;
import app.Tasks.Task;
import app.Tasks.TaskStatus;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Stream;

import static app.General.Operations.compareDate;

public class OperationsEmployee {

  private Repo<Employee> repoEmployees = AccessRepo.getRepoEmpl();

  public Integer getCounter() {
    return counter();
  }

  public boolean readEmployeesFromJSON() {
    try {
      JSONOperations.JSONToArrayEmployee();
      return true;
    } catch (ParseException | java.text.ParseException | IOException ex) {
      ex.printStackTrace();
      return false;
    }
  }

  private Integer counter() {
    Integer count = 0;
    Employee employee = Access.getRepoEmpl().getCurrentEmployee();
    for (Task task : Access.getRepoTasks().get()) {
      if (task.getAssigned() == employee && compareDate(task.getDateResolved())
          && task.getStatus() != TaskStatus.NOTE_DONE) {
        count++;
      }
    }
    return count;
  }

  public Employee getEmployee(String family) {
    for (Employee employee : repoEmployees.get()) {
      if (employee.getFamily().equals(family)) {
        return employee;
      }
    }
    return new Employee(0, family, null, true, false);
  }

  public boolean stat() {
    System.out.println("\nЗа сегодня назначено");
    for (Employee employee : repoEmployees.get()) {
      if (employee.getStatus()) {
        long count = countAssignTaskNow(employee);
        System.out.println(employee.getFamily() + " " + count);
      }
    }
    System.out.println("\nОбщий счёт");
    for (Employee employee : repoEmployees.get()) {
      long countTasks = Access.getRepoTasks().get().stream()
          .filter(t -> t.getAssigned().getFamily().equals(employee.getFamily())).count();
      System.out.println(employee.getFamily() + " " + countTasks);
    }
    System.out.println("\nВсего назначено обращений " + Access.getRepoTasks().get().size() + "\n");
    return true;
  }

  private long countAssignTaskNow(Employee employee) {
    long count = 0;
    OperationsTask operationsTask = new OperationsTask();
    for (Task task : Access.getRepoTasks().get()) {
      if (task.getAssigned().getFamily().equals(employee.getFamily()) && operationsTask
          .searchInHistory(task)) {
        count++;
      }
    }
    return count;
  }

  public Employee tasksOfAuthor(String author) {
    for (Employee employee : repoEmployees.get()) {
      Long count = Access.getRepoTasks().get().stream()
          .filter(t -> t.getAssigned() == employee && t.getAuthor().equals(author)).count();
      employee.setCountTasksOfAuthor(count);
    }
    Stream<Employee> stream = repoEmployees.get().stream();
    Employee empl = stream.max(Comparator.comparing(Employee::getCountTasksOfAuthor)).get();
    return empl;
  }
}
