package app;

import app.General.Options;
import java.io.IOException;
import javax.swing.text.html.Option;
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
    Options.setContext(context);
    Start start = Options.context.getBean(Start.class);
    try {
      start.begin(context);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (java.text.ParseException e) {
      e.printStackTrace();
    }
  }
}
