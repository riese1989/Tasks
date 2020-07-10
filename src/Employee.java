import java.util.ArrayList;

public class Employee {
    private static Integer appCountTask = 0;
    private Integer  countTaskOne = 0;
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
}
