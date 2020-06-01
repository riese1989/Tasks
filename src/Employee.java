public class Employee {
    private static Integer appCountTask;
    private Integer  countTaskOne;
    private String family;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
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

    public Employee(Integer countTaskOne, String family) {
        this.countTaskOne = countTaskOne;
        this.family = family;
    }
    public Employee(String family) {
        this.family = family;
        this.countTaskOne = 0;
    }
}
