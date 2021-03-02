package app.config;

import app.Employees.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import app.others.*;

@Configuration


//@PropertySource("classpath:application.properties")
public class Config {

  @Autowired
  private Environment env;

  @Value("${spring.file.employee.path}")
  private String pathEmployee;

  @Value("${map.path}")
  private String mapPath;

  @Value("${spring.file.group.path}")
  private String groupPath;

  @Bean
  public FileConfig fileConfig()  {
    return new FileConfig(pathEmployee, mapPath, groupPath);
  }

  @Bean
  public OperationsEmployee operationsEmployee()  {
    return new OperationsEmployee();
  }

  @Bean
  public Start start()  {
    return new Start();
  }

}
