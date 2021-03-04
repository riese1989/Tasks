package app.menu;

import app.operations.OperationsEmployee;
import app.prompt.MenuPrompt;
import app.operations.OperationsTask;
import app.enums.TaskStatus;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import static app.general.Operations.scanLine;
import static app.operations.OperationsIniciators.*;

@Component
public class Menu {

  private ApplicationContext context;

  public Menu(@Autowired ApplicationContext context) {
    this.context = context;
  }

  public void menu() throws IOException, ParseException {
    boolean flag = true;
    OperationsTask operationsTask = context.getBean(OperationsTask.class);
    OperationsEmployee operationsEmployee = context.getBean(OperationsEmployee.class);
    MenuPrompt menuPrompt = context.getBean(MenuPrompt.class);
    String numberTask = "";
    TaskStatus status;
    while (flag) {
      operationsTask.getWaitTasks();
      System.out.println(
          "�� ����������� ���� � ��������� " + operationsEmployee.getCounter() + " ���������");
      System.out.println("������� �������\n");
      System.out.println("1. �������������� ���������� ����� ���������");
      System.out.println("2. ������� ���� ���������");
      System.out.println("3. � ��������");
      System.out.println("4. �������");
      System.out.println("5. �� � ���");
      System.out.println("6. ����� ���� ���������");
      System.out.println("7. ��������������");
      System.out.println("8. ����� ���������");
      System.out.println("9. ����������");
      System.out.println("10. ��� �����������");
      System.out.println("11. ������ ���������");
      System.out.println("q. ����� �� ���������");
      String command = scanLine();
      if (!command.equals("1")) {
        numberTask = operationsTask.enterCorrectNumber(false);
      }
      switch (command) {
        case "1": {
          operationsTask.enterTasks();
          break;
        }
        case "2": {
          operationsTask.solveMyTasks("");
          break;
        }
        case "3": {
          status = TaskStatus.WAITING;
          operationsTask.switchStatus(numberTask, status);
          break;
        }
        case "4": {
          status = TaskStatus.TASK;
          operationsTask.switchStatus(numberTask, status);
          break;
        }
        case "5": {
          status = TaskStatus.NOT_US;
          operationsTask.switchStatus(numberTask, status);
          break;
        }
        case "6": {
          operationsTask.printTasks();
          break;
        }
        case "7": {
          operationsTask.manualAssignment();
          break;
        }
        case "8": {
          operationsTask.search();
          break;
        }
        case "9": {
          operationsEmployee.stat();
          break;
        }

        case "10": {
          top();
          break;
        }

        case "11": {
          menuPrompt.menuPrompt();
        }

        case "q": {
          flag = false;
          break;
        }
        default: {
          if (operationsTask.isCorrectNumber(command)) {
            operationsTask.solveMyTasks(command);
          } else {
            System.out.println("������������ �������");
          }
          break;
        }
      }
    }
  }
}
