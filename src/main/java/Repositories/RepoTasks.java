package Repositories;

import General.Options;
import Tasks.Task;
import Tasks.TaskStatus;

import java.util.ArrayList;
import java.util.Date;

public class RepoTasks implements Repo<Task> {

  private ArrayList<Task> listTasks = new ArrayList<>();

  @Override
  public boolean append(Task task) {
    listTasks.add(task);
    return true;
  }

  @Override
  public ArrayList<Task> get() {
    return listTasks;
  }

  public int getIndex(Task taskEmpl) {
    return listTasks.indexOf(taskEmpl);
  }
}
