package Tasks;
import Employees.Employee;
import General.Options;
import Inic.Iniciator;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Task implements Serializable {
    private static Integer countTasksAll;
    private static Integer noneAppTasks;
    private Employee assigned;
    private TaskStatus status;
    private String number;
    private Date dateResolved;
    private String comment;
    private String author;
    private Iniciator iniciator;
    private HashMap <Date, TaskStatus> history = new HashMap<>();

    public HashMap<Date, TaskStatus> getHistory() {
        return history;
    }

    public Task(String number, Employee assigned, TaskStatus status) {
        this.number = number;
        this.assigned = assigned;
        this.status = status;
        history.put(new Date(), status);
    }

    public Task(String number, Employee assigned, TaskStatus status, Date date, String author) {
        this.number = number;
        this.assigned = assigned;
        this.status = status;
        this.dateResolved = date;
        this.author = author;
        history.put(date,status);
        addIniciator(author);
    }

    public Task(String number, Employee assigned, TaskStatus status, Date date, String author, String comment, HashMap<Date, TaskStatus> history) {
        this.number = number;
        this.assigned = assigned;
        this.status = status;
        this.dateResolved = date;
        this.author = author;
        this.comment = comment;
        this.history = history;
        addIniciator(author);
    }

    public Iniciator getIniciator() {
        return iniciator;
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
        Task.noneAppTasks = noneAppTasks;
    }

    public static Integer getNoneAppTasks() {
        return noneAppTasks;
    }

    public static Integer getCountTasksAll() {
        return countTasksAll;
    }

    public static void setCountTasksAll(Integer countTasksAll) {
        Task.countTasksAll = countTasksAll;
    }

    public static Integer countAppTasks() {
        return countTasksAll - noneAppTasks;
    }

    private void addIniciator(String author) {
        Iniciator iniciator = Iniciator.searchIniciator(author);
        if (iniciator != null)  {
            this.iniciator = iniciator;
        }
        else    {
            this.iniciator = new Iniciator(author);
        }
        this.iniciator.addTask(this);
    }
}
