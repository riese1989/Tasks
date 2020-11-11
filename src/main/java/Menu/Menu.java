package Menu;

import Employees.Employee;
import Employees.OperationsEmployee;
import Tasks.OperationsTask;
import Tasks.TaskStatus;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static Employees.OperationsEmployee.*;
import static General.Operations.scanLine;
import static Tasks.OperationsTask.*;
import static Inic.OperationsIniciators.*;

public class Menu {
    public static void menu() throws IOException, ParseException {
        boolean flag = true;
        while (flag) {
            OperationsTask.getWaitTasks();
            System.out.println("�� ����������� ���� � ��������� " + OperationsEmployee.getCounter() + " ���������");
            System.out.println("������� �������\n");
            System.out.println("1. �������������� ���������� ����� ���������");
            System.out.println("2. ������� ���� ���������");
            System.out.println("3. � ��������");
            System.out.println("4. �������");
            System.out.println("5. �� � ���");
            System.out.println("6. ����� ���� ���������");
            System.out.println("7. ������ ���������� ���������");
            System.out.println("8. ����� ���������");
            System.out.println("9. ����������");
            System.out.println("10. ��� �����������");
            System.out.println("q. ����� �� ���������");
            String command = scanLine();
            switch (command) {
                case "1": {
                    enterTasks();
                    break;
                }
                case "2": {
                    solveMyTasks("");
                    break;
                }
                case "3": {
                    switchStatus(enterCorrectNumber(false), TaskStatus.WAITING);
                    break;
                }
                case "4": {
                    switchStatus(enterCorrectNumber(false), TaskStatus.TASK);
                    break;
                }
                case "5": {
                    switchStatus(enterCorrectNumber(false), TaskStatus.NOT_US);
                    break;
                }
                case "6": {
                    printTasks();
                    break;
                }
                case "7": {
                    manualAssignment();
                    break;
                }
                case "8": {
                    search();
                    break;
                }
                case "9": {
                    stat();
                    break;
                }

                case "10":  {
                    top();
                    break;
                }

                case "q": {
                    flag = false;
                    break;
                }
                default: {
                    if (isCorrectNumber(command)) {
                        solveMyTasks(command);
                    } else {
                        System.out.println("������������ �������");
                    }
                    break;
                }

            }
        }
    }
}
