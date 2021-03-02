package app.Employees;

import app.Repositories.RepoTasks;
import app.Tasks.Task;
import app.Tasks.TaskStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Employee {

  private static Integer countTasks = 0;
  private Integer countTaskOne = 0;
  private Integer activeTask = 0;
  private String family;
  private Integer taskWaiting = 0;
  private Integer tasksWithTasks = 0;
  private HashMap<Date, Date> vacations = new HashMap<>();
  private Long countTasksOfAuthor;
  private Integer taskOfThisSession = 0;
  private boolean status;
  private Integer countAllTasksEmpl = 0;
  private RepoTasks repoTasks;
  private boolean isCurrent;

  public HashMap<Date, Date> getVacations() {
    return vacations;
  }

  public void setVacations(HashMap<Date, Date> vacations) {
    this.vacations = vacations;
  }

  public Employee(Integer countTaskOne, String family, HashMap<Date, Date> vacations,
      boolean status, boolean isCurrent) {
    this.countTaskOne = countTaskOne;
    this.family = family;
    this.vacations = vacations;
    this.status = status;
    this.isCurrent = isCurrent;
  }

  public void setTasksWithTasks(Integer tasksWithTasks) {
    this.tasksWithTasks = tasksWithTasks;
  }

  public void setTaskWaiting(Integer taskWaiting) {
    this.taskWaiting = taskWaiting;
  }

  public String getFamily() {
    return family;
  }

  public static Integer getCountTasks() {
    return countTasks;
  }

  public static void setCountTasks(Integer countTasks) {
    Employee.countTasks = countTasks;
  }

  public void setCountTaskOne(Integer countTaskOne) {
    this.countTaskOne = countTaskOne;
  }

  public Integer getCountTaskOne() {
    return countTaskOne;
  }

  public Integer getCountActiveTasks() {
    return countTaskOne - taskWaiting - tasksWithTasks;
  }

  public void incTaskOfThisSession() {
    this.taskOfThisSession++;
  }

  public Integer getTaskWithStatus(Employee employee, TaskStatus status) {
    //flag = true когда надо искать все обращения
    Integer count = 0;
    for (Task task : repoTasks.get()) {
      if ((task.getStatus() == status ||
          (status == TaskStatus.ALL &&
              task.getStatus() != TaskStatus.DONE &&
              task.getStatus() != TaskStatus.NOT_US)) &&
          task.getAssigned() == employee) {
        count++;
      }
    }
    return count;
  }

  public Integer getActiveTask() {
    activeTask = countTaskOne - taskWaiting - tasksWithTasks;
    return activeTask;
  }

  public Integer getTaskOfThisSession() {
    return taskOfThisSession;
  }

  public void setActiveTask(Integer activeTask) {
    this.activeTask = activeTask;
  }

  public boolean currentVacation() {
    Date date = new Date();
    for (Map.Entry<Date, Date> vacation : vacations.entrySet()) {
      Long start = vacation.getKey().getTime();
      Long end = vacation.getValue().getTime();
      if (date.getTime() >= start && date.getTime() <= end) {
        return true;
      }
    }
    return false;
  }

  public Long getCountTasksOfAuthor() {
    return countTasksOfAuthor;
  }

  public void setCountTasksOfAuthor(Long countTasksOfAuthor) {
    this.countTasksOfAuthor = countTasksOfAuthor;
  }

  public boolean getStatus() {
    return status;
  }

  public Integer getCountAllTasksEmpl() {
    return countAllTasksEmpl;
  }

  public void incCountAllTasksEmpl() {
    countAllTasksEmpl++;
  }

  public boolean getIsCurrent() {
    return isCurrent;
  }
}
