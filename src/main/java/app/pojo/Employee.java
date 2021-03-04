package app.pojo;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Employee implements Pojo<Employee> {

  private static Integer countTasks = 0;
  private Integer countTaskOne = 0;
  private String family;
  private HashMap<Date, Date> vacations = new HashMap<>();
  private Long countTasksOfAuthor;
  private boolean status;
  private boolean isCurrent;
  private ApplicationContext context;

  public Employee(@Autowired ApplicationContext context) {
    this.context = context;
  }

  @Override
  public Employee setField(String field, Object value) {
    switch (field) {
      case "countTaskOne": {
        this.countTaskOne = (Integer) value;
        break;
      }
      case "family": {
        this.family = (String) value;
        break;
      }
      case "vacations": {
        this.vacations = (HashMap<Date, Date>) value;
        break;
      }
      case "status": {
        this.status = (boolean) value;
        break;
      }
      case "isCurrent": {
        this.isCurrent = (boolean) value;
        break;
      }
    }
    return this;
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

  public void incTaskOfThisSession() {
  }

  public boolean currentVacation() {
    Date date = new Date();
    for (Map.Entry<Date, Date> vacation : vacations.entrySet()) {
      long start = vacation.getKey().getTime();
      long end = vacation.getValue().getTime();
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

  public void incCountAllTasksEmpl() {
  }

  public boolean getIsCurrent() {
    return isCurrent;
  }

}
