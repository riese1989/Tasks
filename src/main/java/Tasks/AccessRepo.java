package Tasks;

import Repositories.RepoEmpl;
import Repositories.RepoTasks;

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
