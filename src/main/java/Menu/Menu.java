package Menu;

import Employees.Employee;
import Employees.OperationsEmployee;
import General.Operations;
import Tasks.OperationsTask;
import Tasks.TaskStatus;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static General.Operations.scanLine;
import static Prompt.MenuPrompt.menuPrompt;
import static Tasks.OperationsTask.*;
import static Inic.OperationsIniciators.*;

public class Menu {

  public static void menu() throws IOException, ParseException {
    boolean flag = true;
    while (flag) {
      OperationsTask operationsTask = new OperationsTask();
      OperationsEmployee operationsEmployee = new OperationsEmployee();
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
          printTasks();
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
          menuPrompt();
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
