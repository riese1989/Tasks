public class Tasks {
    private static Integer countTasksAll;
    private static Integer noneAppTasks;

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
