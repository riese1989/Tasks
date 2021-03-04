package app;

import app.general.Options;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import app.others.Start;

@SpringBootApplication
public class Application implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private ApplicationContext context = null;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    Start start = context.getBean(Start.class);
    try {
      start.begin();
    } catch (IOException | ParseException | java.text.ParseException e) {
      e.printStackTrace();
    }
  }
}
