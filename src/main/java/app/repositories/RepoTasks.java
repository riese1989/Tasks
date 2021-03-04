package app.repositories;

import app.pojo.Task;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
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

  @Override
  public int size() {
    return listTasks.size();
  }

  public int getIndex(Task taskEmpl) {
    return listTasks.indexOf(taskEmpl);
  }
}
