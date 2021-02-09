package Repositories;

import General.Options;
import Tasks.Task;
import Tasks.TaskStatus;

import java.util.ArrayList;

public class RepoTasks implements Repo<Task> {
    private ArrayList<Task> listTasks = new ArrayList<>();
    @Override
    public boolean append(Task task) {
        listTasks.add(task);
        return true;
    }

    @Override
    public ArrayList<Task> get() {
        Options options = new Options();
        return (ArrayList<Task>) options.copy(listTasks);
    }

    public int getIndex(Task taskEmpl)  {
        int i = 0;
        for (Task task : listTasks) {
            if (task.getNumber().equals(taskEmpl.getNumber()))  {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean setStatus(Task task)  {
        for (Task taskFromList : listTasks) {
            if (taskFromList.getNumber().equals(task.getNumber()))  {
                taskFromList.setStatus(task.getStatus());
            }
        }
        return true;
    }
}
