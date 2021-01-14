package Repositories;

import General.Options;
import Tasks.Task;

import java.util.ArrayList;

public class RepoTasks implements Repo<Task> {
    private ArrayList<Task> listTasks = new ArrayList<>();
    @Override
    public boolean add(Task task) {
        listTasks.add(task);
        return true;
    }

    @Override
    public ArrayList<Task> get() {
        Options options = new Options();
        return (ArrayList<Task>) options.copy(listTasks);
    }
}
