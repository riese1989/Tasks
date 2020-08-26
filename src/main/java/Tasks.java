import java.util.Date;
import java.util.HashMap;

public class Tasks {
    private static Integer countTasksAll;
    private static Integer noneAppTasks;
    private Employee assigned;
    private TaskStatus status;
    private String number;
    private Date dateResolved;
    private String comment;
    private String author;

    public HashMap<Date, TaskStatus> getHistory() {
        return history;
    }

    private HashMap <Date, TaskStatus> history;

    public Tasks(String number, Employee assigned, TaskStatus status) {
        this.number = number;
        this.assigned = assigned;
        this.status = status;

    }


    public Tasks(String number, Employee assigned, TaskStatus status, Date date, String author) {
        this.number = number;
        this.assigned = assigned;
        this.status = status;
        this.dateResolved = date;
        this.author = author;
    }

    public Tasks(String number, Employee assigned, TaskStatus status, Date date, String author, String comment, HashMap<Date, TaskStatus> history) {
        this.number = number;
        this.assigned = assigned;
        this.status = status;
        this.dateResolved = date;
        this.author = author;
        this.comment = comment;
        this.history = history;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public static Integer countAppTasks() {
        return countTasksAll - noneAppTasks;
    }

    public static TaskStatus toStatus(String status) {
        switch (status) {
            case "DONE": {
                return TaskStatus.DONE;
            }
            case "NOTE_DONE": {
                return TaskStatus.NOTE_DONE;
            }
            case "WAITING": {
                return TaskStatus.WAITING;
            }
            case "NOT_US": {
                return TaskStatus.NOT_US;
            }
            case "TASK": {
                return TaskStatus.TASK;
            }
        }
        return TaskStatus.NOTE_DONE;
    }

    public static String statusToString(TaskStatus status)  {
        switch (status) {
            case DONE:   {
                return "DONE";
            }
            case NOTE_DONE: {
                return "NOTE_DONE";
            }
            case NOT_US: {
                return "NOT_US";
            }
            case WAITING: {
                return "WAITING";
            }
            case TASK: {
                return "TASK";
            }
        }
        return "";
    }
}
