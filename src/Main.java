import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        while(flag) {
            System.out.println("Введите команду\n");
            System.out.println("1. Назначение новых обращений");
            System.out.println("2. Решение моих обращений");
            System.out.println("3. Вывод моих обращений");
            System.out.println("q. Выход из программы");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command)  {
                case "1": {
                    enterTasks();
                    break;
                }
                case "2":   {
                    solveMyTasks();
                    break;
                }
                case "3":   {
                    printTasks();
                    break;
                }
                case "q":   {
                    flag = false;
                    break;
                }
                default:    {
                    System.out.println("??????? ???????? ???????");
                    break;
                }

            }
        }
    }

    public static void enterTasks() {

        String str = "=======================";
        Tasks.setCountTasksAll(customScanner("Сколько всего обращений?"));
        Tasks.setNoneAppTasks(customScanner("Сколько неназначенных?"));
        Employee pestov = new Employee(customScanner("Сколько на мне?"), "pestov");
        pestov.setTaskWaiting(customScanner("Сколько у меня в ожидании?"));
        pestov.setTasksWithTasks(customScanner("Сколько у меня с заданиями"));
        Employee batanov = new Employee(Tasks.getCountTasksAll() -Tasks.getNoneAppTasks() - pestov.getCountTaskOne(), "batanov");
        batanov.setTaskWaiting(customScanner("Сколько у Миши в ожидании"));
        batanov.setTasksWithTasks(customScanner("Сколько у Миши с заданиями"));
        System.out.println(str + "\nРезультат\n" + str);
        if (Tasks.getNoneAppTasks() > 0)    {
            Employee.listTasks = new ArrayList<>();
        }
        for (int i = 1; i <=Tasks.getNoneAppTasks(); i++)    {
            System.out.println("Введите номер");
            Scanner scanner = new Scanner(System.in);
            String number = scanner.nextLine();
            Employee empl = choiceAssignTask(pestov, batanov);
            Employee.listTasks.add(new Tasks(number,empl, TaskStatus.NOTE_DONE));
            System.out.println("Назначено на " + empl.getFamily());
            empl.setCountTaskOne(empl.getCountTaskOne()+1);
            Employee.setAppCountTask(Employee.getAppCountTask()+1);
        }
    }

    public static Employee choiceAssignTask(Employee empl1, Employee empl2) {
        if ((int) (Math.random() * 2) == 0) {
            if (empl1.getCountTaskOne() <= Tasks.countAppTasks()/2) {
                return empl1;
            }
            else    {
                return empl2;
            }
        }
        else    {
            if (empl2.getCountTaskOne() <= Tasks.countAppTasks()/2) {
                return empl2;
            }
        }
        return empl1;
    }

    public static void solveMyTasks()   {
        System.out.println("Введите номер");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        for (Tasks task : Employee.listTasks)    {
            if (task.getStatus() == TaskStatus.NOTE_DONE && task.getNumber().equals(number) && task.getAssigned().getFamily().equals("pestov"))    {
                int index = Employee.listTasks.indexOf(task);
                task.setStatus(TaskStatus.DONE);
                task.setDateResolved(new Date());
                Employee.listTasks.set(index,task);
                Logger logger = LogManager.getLogger("STasks");
                logger.info(" решён "+ task.getNumber());
                break;
            }
        }

    }

    public static void printTasks() {
        for(Tasks task: Employee.listTasks) {
            String str = "****************\n";
            if(task.getAssigned().getFamily().equals("pestov")) {
                System.out.println(str + "Номер " + task.getNumber() + "\n" +
                                         "Статус  " + task.getStatus() + "\n" +
                        "Дата решения " + task.getDateResolved());
            }
        }
    }

    public static Integer customScanner(String message)   {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
