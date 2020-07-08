import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    static Employee pestov = new Employee(0, "pestov");
    static Employee batanov = new Employee(0, "batanov");

    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            System.out.println("Введите команду\n");
            System.out.println("1. Назначение новых обращений");
            System.out.println("2. Решение моих обращений");
            System.out.println("3. В ожидание");
            System.out.println("4. Задание");
            System.out.println("5. Не к нам");
            System.out.println("6. Вывод моих обращений");
            System.out.println("q. Выход из программы");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "1": {
                    enterTasks();
                    break;
                }
                case "2": {
                    solveMyTasks();
                    break;
                }
                case "3": {
                    switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(), pestov)), TaskStatus.WAITING);
                    break;
                }
                case "4": {
                    switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(), pestov)), TaskStatus.TASK);
                    break;
                }
                case "5": {
                    switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(), pestov)), TaskStatus.NOT_US);
                    break;
                }
                case "6": {
                    printTasks();
                    break;
                }
                case "q": {
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("Неправильная команда");
                    break;
                }

            }
        }
    }

    public static void enterTasks() {

        String str = "========================";
        Tasks.setCountTasksAll(customScanner("Сколько всего обращений?"));
        Tasks.setNoneAppTasks(customScanner("Сколько неназначенных?"));
        pestov.setCountTaskOne(customScanner("Сколько на мне?"));
        pestov.setTaskWaiting(customScanner("Сколько у меня в ожидании?"));
        pestov.setTasksWithTasks(customScanner("Сколько у меня с заданиями"));
        batanov.setCountTaskOne(Tasks.getCountTasksAll() - Tasks.getNoneAppTasks() - pestov.getCountTaskOne());
        batanov.setTaskWaiting(customScanner("Сколько у Миши в ожидании"));
        batanov.setTasksWithTasks(customScanner("Сколько у Миши с заданиями"));
        System.out.println(str + "\nРезультат\n" + str);
        if (Tasks.getNoneAppTasks() > 0) {
            Employee.listTasks = new ArrayList<>();
        }
        for (int i = 1; i <= Tasks.getNoneAppTasks(); i++) {
            String number = enterCorrectNumber();
            if (number.equals("exit")) {
                break;
            }
            if (number.equals("nu")) {
                continue;
            }
            Employee empl = choiceAssignTask(pestov, batanov);
            Employee.listTasks.add(new Tasks(number, empl, TaskStatus.NOTE_DONE));
            System.out.println("Назначено на " + empl.getFamily());
            empl.setCountTaskOne(empl.getCountTaskOne() + 1);
            Employee.setAppCountTask(Employee.getAppCountTask() + 1);
            log(" " + empl.getFamily() + " назначен ", number, "NaTasks");
        }
    }

    public static Employee choiceAssignTask(Employee empl1, Employee empl2) {
        if ((int) (Math.random() * 2) == 0) {
            if (empl1.getCountTaskOne() <= Tasks.countAppTasks() / 2) {
                return empl1;
            } else {
                return empl2;
            }
        } else {
            if (empl2.getCountTaskOne() <= Tasks.countAppTasks() / 2) {
                return empl2;
            }
        }
        return empl1;
    }

    public static void solveMyTasks() {
        String number = enterCorrectNumber();
        if (!number.equals("exit")) {
            Tasks task = Employee.listTasks.get(searchAndCreateTask(number, pestov));
            switchStatus(task, TaskStatus.DONE);
        }
    }

    public static void printTasks() {
        for (Tasks task : Employee.listTasks) {
            String str = "****************\n";
            if (task.getAssigned().getFamily().equals("pestov")) {
                System.out.println(str + "Номер " + task.getNumber() + "\n" +
                        "Статус  " + task.getStatus() + "\n" +
                        "Дата решения " + task.getDateResolved());
            }
        }
    }

    public static Integer customScanner(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void log(String message, String number, String nameLogger) {
        Logger logger = LogManager.getLogger(nameLogger);
        logger.info(message + number);
    }

    private static Integer searchAndCreateTask(String number, Employee empl) {
        Tasks task = returnTask(number);
        if (task != null) {
            return Employee.listTasks.indexOf(task);
        }
        task = new Tasks(number, empl, TaskStatus.NOTE_DONE);
        Employee.listTasks.add(task);
        return Employee.listTasks.indexOf(task);
    }

    private static Tasks returnTask(String number) {
        for (Tasks taskEmpl : Employee.listTasks) {
            if (taskEmpl.getNumber().equals(number)) {
                return taskEmpl;
            }
        }
        return null;
    }

    private static void switchStatus(Tasks task, TaskStatus status) {
        int index = Employee.listTasks.indexOf(task);
        String stat = "";
        task.setStatus(status);
        task.setDateResolved(new Date());
        Employee.listTasks.set(index, task);
        if (status == TaskStatus.TASK) {
            log(" выписано задание ", task.getNumber(), "TTasks");
            stat = "выписано задание";
        }
        if (status == TaskStatus.WAITING) {
            log(" переведен в ожидание ", task.getNumber(), "WTasks");
            stat = "ожидание";
        }
        if (status == TaskStatus.DONE) {
            log(" решён ", task.getNumber(), "STasks");
            stat = "решен";
        }
        if (status == TaskStatus.NOT_US) {
            log(" переквалифицировано ", task.getNumber(), "NTasks");
            stat = "переквалифицировано";
        }
        System.out.println("У " + task.getNumber() + " статус переключен на " + stat);
    }

    private static String enterCorrectNumber() {
        for (; ; ) {
            System.out.println("Введите номер");
            Scanner scanner = new Scanner(System.in);
            String number = scanner.nextLine();
            String[] splitNubmer = number.split("-");
            if (number.equals("q")) {
                return "exit";
            }
            if (number.equals("nu")) {
                System.out.println("Функция переключения статуса вызвана");
                switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(), pestov)), TaskStatus.NOT_US);
                return "nu";
            }
            if ((number.length() == 13 && splitNubmer.length == 2) || (number.length() == 17 && splitNubmer.length == 3)) {
                Tasks task = returnTask(number);
                if (task != null) {
                    System.out.println("Данное обращение уже назначено на " + task.getAssigned().getFamily());
                } else {
                    return number;
                }
            } else {
                System.out.println("Номер неверный");
            }
        }
    }
}
