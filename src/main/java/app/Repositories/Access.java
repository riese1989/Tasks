package app.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Access {

  private RepoTasks repoTasks;
  private RepoEmpl repoEmpl;

  public Access (@Autowired RepoTasks repoTasks,
      @Autowired RepoEmpl repoEmpl) {
    this.repoEmpl = repoEmpl;
    this.repoTasks = repoTasks;
  }

  public RepoTasks getRepoTasks() {
    return repoTasks;
  }

  public RepoEmpl getRepoEmpl() {
    return repoEmpl;
  }
}
