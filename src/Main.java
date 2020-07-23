import java.io.IOException;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Main {
    static Employee pestov = new Employee(0, "pestov");
    static Employee batanov = new Employee(0, "batanov");
    static JSONObject fullJSON = new JSONObject();
    static String filePath = "map.json";
    static String filePathVactions = "vacations.json";


    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        Employee.employees.add(pestov);
        Employee.employees.add(batanov);
        JSONOperations.JSONtoArray();
        JSONOperations.JSONtoHashMap(pestov);
        JSONOperations.JSONtoHashMap(batanov);
        fullJSON = JSONOperations.getJSON(filePath);
        menu();
    }

    public static void menu() throws IOException, ParseException, java.text.ParseException {
        boolean flag = true;
        while (flag) {
            waitTasks();
            System.out.println("Введите команду\n");
            System.out.println("1. Назначение новых обращений");
            System.out.println("2. Решение моих обращений");
            System.out.println("3. В ожидание");
            System.out.println("4. Задание");
            System.out.println("5. Не к нам");
            System.out.println("6. Вывод моих обращений");
            System.out.println("7. Сколько сегодня я решил");
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
                    switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), pestov)), TaskStatus.WAITING);
                    break;
                }
                case "4": {
                    switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), pestov)), TaskStatus.TASK);
                    break;
                }
                case "5": {
                    switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), pestov)), TaskStatus.NOT_US);
                    break;
                }
                case "6": {
                    printTasks();
                    break;
                }
                case "7": {
                    countSolvedTasksNov();
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

    //ввод номера обращения
    public static void enterTasks() throws IOException, ParseException {
        String str = "========================";
        Tasks.setNoneAppTasks(scanInteger("Сколько неназначенных?"));
        System.out.println(str + "\nРезультат\n" + str);
        for (int i = 1; i <= Tasks.getNoneAppTasks(); i++) {
            String number = enterCorrectNumber(true);
            if (number.equals("exit")) {
                break;
            }
            if (number.equals("nu")) {
                continue;
            }
            Employee empl = choiceAssignTask(pestov, batanov);
            System.out.println("Инициатор");
            Tasks task = new Tasks(number, empl, TaskStatus.NOTE_DONE, new Date(), scanLine());
            Employee.listTasks.add(task);
            System.out.println("Назначено на " + empl.getFamily());
            empl.setCountTaskOne(empl.getCountTaskOne() + 1);
            Employee.setAppCountTask(Employee.getAppCountTask() + 1);
            log(" " + empl.getFamily() + " назначен ", number, "NaTasks");
            JSONOperations.makeJSON(task);
            JSONOperations.writeJSON();
        }
    }

    //выбор исполнителя
    public static Employee choiceAssignTask(Employee empl1, Employee empl2) {
        if (empl1.currentVacation())    {
            return empl2;
        }
        if (empl2.currentVacation())    {
            return empl1;
        }
        if ((int) (Math.random() * 2) == 0) {
            return empl1;
        }
        return empl2;
    }

    //решение обращения
    public static void solveMyTasks(String haveNumber) throws IOException {
        String number = "";
        if (!haveNumber.equals("")) {
            number = haveNumber;
        } else {
            number = enterCorrectNumber(false);
        }
        if (!number.equals("exit")) {
            Tasks task = Employee.listTasks.get(searchAndCreateTask(number, pestov));
            switchStatus(task, TaskStatus.DONE);
        }
    }

    //распечатывание моих обращений
    public static void printTasks() {
        for (Tasks task : Employee.listTasks) {
            String str = "****************\n";
            if (task.getAssigned().getFamily().equals("pestov") &&
                    task.getStatus() != TaskStatus.DONE &&
                    task.getStatus() != TaskStatus.NOT_US) {
                System.out.println(str + "Номер " + task.getNumber() + "\n" +
                        "Статус  " + task.getStatus());
            }
        }
    }

    //сканнер числа ввода из коммандной строки
    public static Integer scanInteger(String message) {
        String string;
        for (; ; ) {
            System.out.println(message);
            string = scanLine();
            if (string.matches("[-+]?\\d+")) {
                return Integer.parseInt(string);
            }
            System.out.println("Вы ввели не число");
        }
    }

    //логирование
    private static void log(String message, String number, String nameLogger) {
        Logger logger = LogManager.getLogger(nameLogger);
        logger.info(message + number);
    }

    //поиск обращения в Employess.listTasks
    private static Integer searchAndCreateTask(String number, Employee empl) {
        Tasks task = returnTask(number);
        if (task != null) {
            return Employee.listTasks.indexOf(task);
        }
        task = new Tasks(number, empl, TaskStatus.NOTE_DONE);
        Employee.listTasks.add(task);
        return Employee.listTasks.indexOf(task);
    }

    //вовзращает обращение
    private static Tasks returnTask(String number) {
        for (Tasks taskEmpl : Employee.listTasks) {
            if (taskEmpl.getNumber().equals(number)) {
                return taskEmpl;
            }
        }
        return null;
    }

    //переключение статуса
    private static void switchStatus(Tasks task, TaskStatus status) throws IOException {
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
        JSONOperations.makeJSON(task);
        JSONOperations.writeJSON();
        System.out.println("У " + task.getNumber() + " статус переключен на " + stat);
    }

    //правильность ввода номера обращения
    private static String enterCorrectNumber(boolean flag) throws IOException {
        for (; ; ) {
            System.out.println("Введите номер");
            String number = scanLine();
            if (number.equals("q")) {
                return "exit"; //проверить
            }
            if (number.equals("nu")) {
                System.out.println("Функция переключения статуса вызвана");
                switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), pestov)), TaskStatus.NOT_US);
                return "nu";
            }
            if (isCorrectNumber(number)) {
                Tasks task = returnTask(number);
                if (task != null && flag == true) {
                    System.out.println("Данное обращение уже назначено на " + task.getAssigned().getFamily());
                } else {
                    return number;
                }
            } else {
                System.out.println("Номер неверный");
            }
        }
    }

    //сканнер строки ввода из коммандной строки
    private static String scanLine() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        log(string, "", "ETasks");
        return string;
    }


    private static boolean isCorrectNumber(String number) {
        String[] splitNubmer = number.split("-");
        number = number.replace(" ", "");
        if ((number.length() == 13 && splitNubmer.length == 2) || (number.length() == 17 && splitNubmer.length == 3)) {
            return true;
        }
        return false;
    }

    private static void waitTasks() {
        ArrayList<Tasks> arrWait = new ArrayList<>();
        Date date = new Date();
        Integer countDaysWait = 2;

        Employee.listTasks.forEach((tasks -> {
            if (tasks.getStatus() == TaskStatus.WAITING &&
                    !isDifTrue(date, tasks.getDateResolved(), countDaysWait)) {
                arrWait.add(tasks);
            }
        }));
        if (arrWait.size() > 0) {
            System.out.println("У этих обращений просрочено ожидание:");
            arrWait.forEach(tasks -> {
                System.out.println(tasks.getNumber() + " " + tasks.getDateResolved());
            });
        }
    }

    private static boolean isDifTrue (Date date, Date taskDate, Integer countWaitDays)  {
        Integer difference = 0;
        Calendar calNov = new GregorianCalendar();
        Calendar calStart = new GregorianCalendar();
        calStart.setTime(taskDate);
        Integer dayOfWeekStart = calStart.get(Calendar.DAY_OF_WEEK);
        Integer dayOfWeekNov = calNov.get(Calendar.DAY_OF_WEEK);
        Long differenceDate = date.getTime() - taskDate.getTime();
        difference = dayOfWeekNov - dayOfWeekStart;
        if (dayOfWeekStart == 5 || dayOfWeekStart == 6)   {
            difference = dayOfWeekNov + 5 - dayOfWeekStart;
            differenceDate -= 2 * 24 * 60 * 60 * 1000;
        }

        if(differenceDate >= countWaitDays * 24 * 60 * 60 * 1000 && difference >= 2)    {
            return false;
        }
        return true;
    }

    private static void countSolvedTasksNov()   {
        
    }
}
