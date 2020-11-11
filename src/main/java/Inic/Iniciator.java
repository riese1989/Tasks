package Inic;

import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class Iniciator {
    private String name;
    private ArrayList<Task> taskInic = new ArrayList<>();
    private Integer countTasks = taskInic.size();
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


    public ArrayList<Task> getTaskInic() {
        return taskInic;
    }

    public void setTaskInic(ArrayList<Task> taskInic) {
        this.taskInic = taskInic;
    }

    public void addTask (Task task)    {
        taskInic.add(task);
        countTasks++;
    }

    public static Iniciator searchIniciator (String name)  {
        String newFamilyInic = returnNewSirname(name);
        String oldNameInic;
        for (Iniciator iniciator:listIniciators)    {
            oldNameInic = iniciator.getName();
            if (oldNameInic.equals(name) || oldNameInic.equals(newFamilyInic))   {
                if (newFamilyInic != null)  {
                    iniciator.setName(newFamilyInic);
                }
                return iniciator;
            }
        }
        return null;
    }

    private static boolean isTaskInInic (String name, String number)   {
        Iniciator glInic = null;
        for (Iniciator iniciator:listIniciators)    {
            if (iniciator.getName().equals(name))   {
                glInic = iniciator;
                break;
            }
        }
        if (glInic != null) {
            for (Task task : glInic.getTaskInic()) {
                if (task.getNumber().equals(number)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static String returnNewSirname (String name)  {
        HashMap<String, String> oldSirname = new HashMap<>();
        oldSirname.put("SvFedorova", "Svetlana.Kostikova");
        String newName = oldSirname.get(name);
        return newName;
    }

    private static Iniciator circleInic(String name)   {
        for (Iniciator iniciator : listIniciators)  {
            if (iniciator.getName().equals(name))   {
                return iniciator;
            }
        }
        return null;
    }
}
