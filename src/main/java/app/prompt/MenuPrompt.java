package app.prompt;

import app.menu.Menu;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static app.general.Operations.scanLine;
import static app.prompt.AnalizPrompt.analiz;

@Component
public class MenuPrompt {

  private Menu menu;

  public MenuPrompt(@Autowired Menu menu) {
    this.menu = menu;
  }

  public void menuPrompt() throws IOException, ParseException {
    System.out.println("==================");
    boolean flag = true;
    while (flag) {
      System.out.println("Меню подсказок");
      System.out.println("1. Анализ - разработка АР - Оценка");
      System.out.println("q: Вернуться к общему меню");
      String command = scanLine();
      switch (command) {
        case "1": {
          analiz();
        }
        case "q": {
          menu.menu();
          break;
        }
        default: {
          flag = false;
          System.out.println("Неправильная комманда");
        }
      }
    }
  }
}
