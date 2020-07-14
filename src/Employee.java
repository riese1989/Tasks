import java.util.ArrayList;

public class Employee {
    private static Integer appCountTask = 0;
    private Integer  countTaskOne = 0;
    private Integer activeTask = 0;
    private String family;
    private Integer taskWaiting = 0;
    private Integer tasksWithTasks = 0;
    public static ArrayList<Tasks> listTasks = new ArrayList<>();
    public static ArrayList<Employee> employees = new ArrayList<>();

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

    public static Integer getAppCountTask() {
        return appCountTask;
    }

    public static void setAppCountTask(Integer appCountTask) {
        Employee.appCountTask = appCountTask;
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

    public static Employee getEmployee  (String family) {
        for (Employee employee : employees) {
            if (employee.getFamily().equals(family))    {
                return  employee;
            }
        }
        return new Employee(0, family);
    }

    public static Integer getTaskWithStatus (Employee employee, TaskStatus status)   {
        //flag = true когда надо искать все обращения
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

    public void setActiveTask(Integer activeTask) {
        this.activeTask = activeTask;
    }

}
