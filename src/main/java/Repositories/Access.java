package Repositories;

public class Access {
    private static RepoTasks repoTasks = new RepoTasks();
    private static RepoEmpl repoEmpl = new RepoEmpl();

    public static RepoTasks getRepoTasks() {
        return repoTasks;
    }

    public static RepoEmpl getRepoEmpl() {
        return repoEmpl;
    }
}
