import java.util.ArrayList;
import java.util.HashMap;

public class Iniciator {
    private String name;
    private ArrayList<Tasks> tasksInic = new ArrayList<>();
    private Integer countTasks = tasksInic.size();
    public static ArrayList<Iniciator> listIniciators = new ArrayList<>();

    public Iniciator(String name) {
        this.name = name;
        countTasks = 0;
        listIniciators.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountTasks() {
        return countTasks;
    }


    public ArrayList<Tasks> getTasksInic() {
        return tasksInic;
    }

    public void setTasksInic(ArrayList<Tasks> tasksInic) {
        this.tasksInic = tasksInic;
    }

    public void addTask (Tasks task)    {
        tasksInic.add(task);
        countTasks++;
    }

    public static Iniciator searchIniciator (String name)  {
        for (Iniciator iniciator : listIniciators)  {
            if (iniciator.getName().equals(name))   {
                return iniciator;
            }
        }
        return null;
    }
}
