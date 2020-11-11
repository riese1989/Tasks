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
            System.out.println("За сегодняшний день я обработал " + OperationsEmployee.getCounter() + " обращений");
            System.out.println("Введите команду\n");
            System.out.println("1. Автоматическое назначение новых обращений");
            System.out.println("2. Решение моих обращений");
            System.out.println("3. В ожидание");
            System.out.println("4. Задание");
            System.out.println("5. Не к нам");
            System.out.println("6. Вывод моих обращений");
            System.out.println("7. Ручное назначение обращений");
            System.out.println("8. Поиск обращения");
            System.out.println("9. Статистика");
            System.out.println("10. Топ инициаторов");
            System.out.println("q. Выход из программы");
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
                        System.out.println("Неправильная команда");
                    }
                    break;
                }

            }
        }
    }
}
