package app.config;

public class FileConfig {

  private static int i = 0;
  private String pathEmployee;
  private String mapPath;
  private String groupPath;

  public FileConfig(String pathEmployee, String mapPath, String groupPath) {
    this.pathEmployee = pathEmployee;
    this.mapPath = mapPath;
    this.groupPath = groupPath;
    String className = new Exception().getStackTrace()[1].getClassName();
    System.out.println(className);
  }


  public String getPathEmployee() {
    return pathEmployee;
  }

  public String getMapPath() {
    return mapPath;
  }

  public String getGroupPath() {
    return groupPath;
  }
}
