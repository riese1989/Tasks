package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration

public class FileConfig {

  private Config config;

  @Value("${spring.file.employee.path}")
  private String pathEmployee;

  @Value("${map.path}")
  private String mapPath;

  @Value("${spring.file.group.path}")
  private String groupPath;

  public FileConfig(@Autowired Config config) {
    this.config = config;
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
