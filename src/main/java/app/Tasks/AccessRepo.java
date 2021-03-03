package app.Tasks;

import app.Repositories.Access;
import app.Repositories.RepoEmpl;
import app.Repositories.RepoTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessRepo {

  private RepoEmpl repoEmpl;
  private RepoTasks repoTasks;

  public AccessRepo (@Autowired RepoEmpl repoEmpl,
      @Autowired RepoTasks repoTasks) {
    this.repoEmpl = repoEmpl;
    this.repoTasks = repoTasks;
  }

  public RepoEmpl getRepoEmpl() {
    return repoEmpl;
  }

  public RepoTasks getRepoTasks() {
    return repoTasks;
  }
}
