package app.Repositories;

import app.Tasks.Task;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
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
