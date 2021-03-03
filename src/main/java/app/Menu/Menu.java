package app.Menu;

import app.Employees.OperationsEmployee;
import app.General.Operations;
import app.Prompt.MenuPrompt;
import app.Tasks.OperationsTask;
import app.Tasks.TaskStatus;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import static app.General.Operations.scanLine;
import static app.Tasks.OperationsTask.*;
import static app.Inic.OperationsIniciators.*;

@Component
public class Menu {

  private ApplicationContext context;

  public Menu (@Autowired ApplicationContext context) {
    this.context = context;
  }

  public void menu() throws IOException, ParseException {
    boolean flag = true;
    OperationsTask operationsTask = context.getBean(OperationsTask.class);
    OperationsEmployee operationsEmployee = context.getBean(OperationsEmployee.class);
    MenuPrompt menuPrompt = context.getBean(MenuPrompt.class);
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
          operationsTask.switchStatus(operationsTask.enterCorrectNumber(false), TaskStatus.WAITING);
          break;
        }
        case "4": {
          operationsTask.switchStatus(operationsTask.enterCorrectNumber(false), TaskStatus.TASK);
          break;
        }
        case "5": {
          operationsTask.switchStatus(operationsTask.enterCorrectNumber(false), TaskStatus.NOT_US);
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
