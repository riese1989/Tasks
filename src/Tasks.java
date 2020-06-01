public class Tasks {
    private static Integer countTasksAll;
    private static Integer noneAppTasks;
    private TaskStatus status;
    private Employee employee;
    private String number;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static Integer getNoneAppTasks() {
        return noneAppTasks;
    }

    public static void setNoneAppTasks(Integer noneAppTasks) {
        Tasks.noneAppTasks = noneAppTasks;
    }

    public static Integer getCountTasksAll() {
        return countTasksAll;
    }

    public static void setCountTasksAll(Integer countTasksAll) {
        Tasks.countTasksAll = countTasksAll;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    public void switchStatus()  {
        if (this.getStatus() == TaskStatus.DONE)    {
            this.setStatus(TaskStatus.NOTE_DONE);
        }
        else    {
            this.setStatus(TaskStatus.DONE);
        }
    }
}
