public class Main {
    public static void main(String[] args) {
        Tasks.setCountTasksAll(23);
        Tasks.setNoneAppTasks(5);
        Employee pestov = new Employee(5, "pestov");
        Employee batanov = new Employee(Tasks.getCountTasksAll()-pestov.getCountTaskOne() - Tasks.getNoneAppTasks(),
                "batanov");
        Employee.setAppCountTask(pestov.getCountTaskOne() + batanov.getCountTaskOne());
        while (!Employee.getAppCountTask().equals(Tasks.getCountTasksAll()))    {
            if ( (int) Math.random() == 0 && pestov.getCountTaskOne() <= Tasks.getCountTasksAll() / 2)  {
                System.out.println(pestov.getFamily());
                pestov.setCountTaskOne(pestov.getCountTaskOne()+1);
            }
            else    {
                System.out.println(batanov.getFamily());
                batanov.setCountTaskOne(batanov.getCountTaskOne()+1);
            }
         Employee.setAppCountTask(Employee.getAppCountTask()+1);
        }
    }
}
