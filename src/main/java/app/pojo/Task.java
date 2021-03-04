package app.pojo;

import app.enums.TaskStatus;

import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Task implements Pojo<Task> {

  private static int countTasksAll;
  private static int noneAppTasks;
  private Employee assigned;
  private TaskStatus status;
  private String number;
  private Date dateResolved;
  private String comment;
  private String author;
  private Iniciator iniciator;
  private HashMap<Date, TaskStatus> history = new HashMap<>();
  private ApplicationContext context;

  public Task(@Autowired ApplicationContext context) {
    this.context = context;
  }

  @Override
  public Task setField(String field, Object value) {
    switch (field) {
      case "assigned": {
        this.assigned = (Employee) value;
        break;
      }
      case "status": {
        this.status = (TaskStatus) value;
        break;
      }
      case "number": {
        this.number = (String) value;
        break;
      }
      case "dateResolved": {
        this.dateResolved = (Date) value;
        break;
      }
      case "comment": {
        this.comment = (String) value;
        break;
      }
      case "author": {
        this.author = (String) value;
        break;
      }
      case "iniciator": {
        this.iniciator = (Iniciator) value;
        break;
      }
      case "history": {
        this.history = (HashMap<Date, TaskStatus>) value;
        break;
      }
    }
    return this;
  }

  public HashMap<Date, TaskStatus> getHistory() {
    return history;
  }

  public Iniciator getIniciator() {
    return iniciator;
  }

  public static void setNoneAppTasks(int count) {
    noneAppTasks = count;
  }

  public String getComment() {
    return comment;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Employee getAssigned() {
    return assigned;
  }

  public void setAssigned(Employee assigned) {
    this.assigned = assigned;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public String getNumber() {
    return number;
  }

  public Date getDateResolved() {
    return dateResolved;
  }

  public static Integer getNoneAppTasks() {
    return noneAppTasks;
  }
}
