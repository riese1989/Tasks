import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Employee {
    private static Integer countTasks = 0;
    private Integer  countTaskOne = 0;
    private Integer activeTask = 0;
    private String family;
    private Integer taskWaiting = 0;
    private Integer tasksWithTasks = 0;
    public static ArrayList<Tasks> listTasks = new ArrayList<>();
    public static ArrayList<Employee> employees = new ArrayList<>();
    private HashMap<Date, Date> vacations = new HashMap<>();
    private  Long countTasksOfAuthor;
    private Integer taskOfThisSession = 0;

    public HashMap<Date, Date> getVacations() {
        return vacations;
    }

    public void setVacations(HashMap<Date, Date> vacations) {
        this.vacations = vacations;
    }

    public Employee(Integer countTaskOne, String family) {
        this.countTaskOne = countTaskOne;
        this.family = family;
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

    public Integer getCountActiveTasks()    {
        return countTaskOne - taskWaiting - tasksWithTasks;
    }

    public void incTaskOfThisSession()  {
        this.taskOfThisSession++;
    }

    public static Employee getEmployee  (String family) {
        for (Employee employee : employees) {
            if (employee.getFamily().equals(family))    {
                return  employee;
            }
        }
        return new Employee(0, family);
    }

    public static Integer getTaskWithStatus (Employee employee, TaskStatus status)   {
        //flag = true ����� ���� ������ ��� ���������
        Integer count = 0;
        for (Tasks task : Employee.listTasks)   {
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

    public boolean currentVacation()    {
        Date date = new Date();
        for (Map.Entry <Date, Date> vacation : vacations.entrySet()) {
            Long start = vacation.getKey().getTime();
            Long end = vacation.getValue().getTime();
            if (date.getTime() >= start && date.getTime() <= end)   {
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


}