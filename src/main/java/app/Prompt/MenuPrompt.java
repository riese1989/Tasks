package app.Prompt;

import org.json.simple.parser.ParseException;

import java.io.IOException;

import static app.General.Operations.scanLine;
import static app.Menu.Menu.menu;
import static app.Prompt.AnalizPrompt.analiz;

public class MenuPrompt {

  public static void menuPrompt() throws IOException, ParseException {
    System.out.println("==================");
    boolean flag = true;
    while (flag) {
      System.out.println("���� ���������");
      System.out.println("1. ������ - ���������� �� - ������");
      System.out.println("q: ��������� � ������ ����");
      String command = scanLine();
      switch (command) {
        case "1": {
          analiz();
        }
        case "q": {
          menu();
          break;
        }
        default: {
          flag = false;
          System.out.println("������������ ��������");
        }
      }
    }
  }
}
