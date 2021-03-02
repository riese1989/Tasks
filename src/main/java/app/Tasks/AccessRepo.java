package app.Tasks;

import app.Repositories.RepoEmpl;
import app.Repositories.RepoTasks;

public class AccessRepo {

  private static RepoEmpl repoEmpl = new RepoEmpl();
  private static RepoTasks repoTasks = new RepoTasks();

  public static RepoEmpl getRepoEmpl() {
    return repoEmpl;
  }

  public static RepoTasks getRepoTasks() {
    return repoTasks;
  }
}
