import java.util.ArrayList;
import java.util.Date;

public class Tasks {
    private static Integer countTasksAll;
    private static Integer noneAppTasks;
    private Employee assigned;
    private TaskStatus status;
    private String number;
    private Date dateResolved;

    public Tasks(String number, Employee assigned, TaskStatus status) {
        this.number = number;
        this.assigned = assigned;
        this.status = status;

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

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
    }


    public static void setNoneAppTasks(Integer noneAppTasks) {
        Tasks.noneAppTasks = noneAppTasks;
    }
    public static Integer getNoneAppTasks() {
        return noneAppTasks;
    }

    public static Integer getCountTasksAll() {
        return countTasksAll;
    }

    public static void setCountTasksAll(Integer countTasksAll) {
        Tasks.countTasksAll = countTasksAll;
    }

    public static Integer countAppTasks()   {
        return countTasksAll - noneAppTasks;
    }
}
