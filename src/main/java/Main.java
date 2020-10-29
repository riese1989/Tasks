import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


public class Main {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss z");
    static JSONObject fullJSON = new JSONObject();
    static String filePath = "map.json";
    static String filePathVactions = "vacations.json";
    static String fileEmployees = "Employees.txt";


    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        readEmployees();
        JSONOperations.JSONtoArray();
        fullJSON = JSONOperations.getJSON(filePath);

        menu();
    }

    public static void menu() throws IOException, ParseException, java.text.ParseException {
        boolean flag = true;
        while (flag) {
            waitTasks();
            System.out.println("За сегодняшний день я обработал " + counter() + " обращений");
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
                    switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), Employee.getEmployee("pestov"))), TaskStatus.WAITING);
                    break;
                }
                case "4": {
                    switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), Employee.getEmployee("pestov"))), TaskStatus.TASK);
                    break;
                }
                case "5": {
                    switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), Employee.getEmployee("pestov"))), TaskStatus.NOT_US);
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
            System.out.println("Инициатор");
            String author = scanLine();
            Employee empl = choiceAssignTask(author);
            empl.setCountTaskOne(empl.getCountTaskOne() + 1);
            empl.incTaskOfThisSession();
            Tasks task = new Tasks(number, empl, TaskStatus.NOTE_DONE, new Date(), author);
            Employee.listTasks.add(task);
            System.out.println("Назначено на " + empl.getFamily());
            Employee.setCountTasks(Employee.getCountTasks() + 1);
            log(" " + empl.getFamily() + " назначен ", number, "NaTasks");
            JSONOperations.makeJSON(task);
            JSONOperations.writeJSON();
        }
    }

    //выбор исполнителя
    public static Employee choiceAssignTask(String author) {
        Integer size = Employee.employees.size();
        for (; ; ) {
            Integer random = (int) (Math.random() * size);
            Employee empl = nowTaskOfAuthor(author);
            if (empl != null)   {
                return empl;
            }
            empl = Employee.employees.get(random);
            if (empl.currentVacation()) {
                random = (int) (Math.random() * size);
                continue;
            }
            return empl;
        }
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
            Tasks task = Employee.listTasks.get(searchAndCreateTask(number, Employee.getEmployee("pestov")));
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
        HashMap<Integer, HashMap<Groups, String>> nameGroups = setNameGroups();
        int index = Employee.listTasks.indexOf(task);
        String stat = "";
        task.setStatus(status);
        task.setDateResolved(new Date());
        if (status != TaskStatus.TASK) {
            System.out.println("Комментарий ");
            task.setComment(scanLine());
        }
        Employee.listTasks.set(index, task);
        if (status == TaskStatus.TASK) {
            String names = new String();
            for(Integer i = 1; i <= nameGroups.size(); i++) {
                names += i + " " + getValues(nameGroups.get(i)) + "\n";
            }
            Integer group = scanInteger("На какую группу назначить?\n" + names);
            task.setComment(getValues(nameGroups.get(group)));
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
            if (task.getAuthor() == null)  {
                System.out.println("Инициатор");
                String author = scanLine();
                task.setAuthor(author);
            }
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
                switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), Employee.getEmployee("pestov"))), TaskStatus.NOT_US);
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
        ArrayList<Tasks> arrWait = new ArrayList<Tasks>();
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

    private static boolean isDifTrue(Date date, Date taskDate, Integer countWaitDays) {
        Integer difference = 0;
        Calendar calNov = new GregorianCalendar();
        Calendar calStart = new GregorianCalendar();
        calStart.setTime(taskDate);
        Integer dayOfWeekStart = calStart.get(Calendar.DAY_OF_WEEK);
        Integer dayOfWeekNov = calNov.get(Calendar.DAY_OF_WEEK);
        Long differenceDate = date.getTime() - taskDate.getTime();
        difference = dayOfWeekNov - dayOfWeekStart;
        if (dayOfWeekStart == 5 || dayOfWeekStart == 6) {
            difference = dayOfWeekNov + 5 - dayOfWeekStart;
            differenceDate -= 2 * 24 * 60 * 60 * 1000;
        }

        if (differenceDate >= countWaitDays * 24 * 60 * 60 * 1000 && difference >= 2) {
            return false;
        }
        return true;
    }

    private static void manualAssignment() throws IOException {
        Employee assignee = null;
        Integer i = 0;
        Integer enterNumber = 0;
        Tasks task;
        boolean flag = true;
        boolean flag2 = false;
        task = Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), assignee));
        task.setDateResolved(new Date());
        if (task.getAssigned() != null) {
            System.out.println("Сейчас оно назначено на " + task.getAssigned().getFamily());
        }

        while (flag) {
            if (enterNumber < i + 1 && enterNumber != 0) {
                assignee = Employee.employees.get(enterNumber - 1);
                task.setAssigned(assignee);
                task.setStatus(TaskStatus.NOTE_DONE);
                System.out.println("Обращение " + task.getNumber() + " назначено на " + assignee.getFamily());
                flag = false;
                continue;
            }
            if (enterNumber == 0 && flag2 == true && enterNumber > i - 1) {
                System.out.println("Значение должно быть больше 0 и меньше " + (i - 1));
            }
            for (Employee empl : Employee.employees) {
                i++;
                System.out.println(i + " " + empl.getFamily());
                flag2 = true;
            }
            enterNumber = scanInteger("На кого назначить это обращение (введите номер)?");
        }
        JSONOperations.makeJSON(task);
        JSONOperations.writeJSON();
    }

    private static void readEmployees() {
        try {
            File file = new File(fileEmployees);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                Employee empl = new Employee(0, line);
                Employee.employees.add(empl);
                JSONOperations.JSONtoHashMap(empl);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Integer counter() {
        Integer count = 0;
        Employee empl = Employee.getEmployee("pestov");
        for (Tasks task : Employee.listTasks) {
            if (task.getAssigned() == empl && compareDate(task.getDateResolved()) && task.getStatus() != TaskStatus.NOTE_DONE) {
                count++;
            }
        }
        return count;
    }

    private static boolean compareDate(Date dateResolved) {
        Calendar calNow = dateToCalendar(new Date());
        Calendar calResolved = dateToCalendar(dateResolved);
        if ((calNow.get(Calendar.DAY_OF_MONTH) == calResolved.get(Calendar.DAY_OF_MONTH)) &&
                (calNow.get(Calendar.MONTH) == calResolved.get(Calendar.MONTH)) &&
                (calNow.get(Calendar.YEAR) == calResolved.get(Calendar.YEAR))) {
            return true;
        }
        return false;
    }

    private static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Employee tasksOfAuthor(String author) {
        for (Employee employee : Employee.employees) {
            Long count = Employee.listTasks.stream().filter(t -> t.getAssigned() == employee && t.getAuthor().equals(author)).count();
            employee.setCountTasksOfAuthor(count);
        }
        Stream<Employee> stream = Employee.employees.stream();
        Employee empl = stream.max(Comparator.comparing(Employee::getCountTasksOfAuthor)).get();
        return empl;
    }

    public static void search() throws IOException {
        String number = enterCorrectNumber(false);
        Tasks task = returnTask(number);
        if (task != null) {
            System.out.println("Текущий статус " + Tasks.statusToString(task.getStatus()));
            String author = task.getAuthor();
            if (author != null) {
                System.out.println("Автор " + task.getAuthor());
            }
            System.out.println("Назначен на " + task.getAssigned().getFamily());
            System.out.println("История изменения статусов:");
            HashMap<Date, TaskStatus> history = task.getHistory();
            for (Map.Entry<Date, TaskStatus> hist : history.entrySet()) {
                String status = Tasks.statusToString(hist.getValue());
                String date = hist.getKey().toString();
                System.out.println("Статус " + status);
                System.out.println("Дата " + date);
                System.out.println();
            }
        } else {
            System.out.println("Обращение не найдено");
        }
    }

    private static void stat() {
        System.out.println("\nСегодняшний счёт");
        for (Employee employee : Employee.employees) {
            long count = countAssignTaskNow(employee);
            System.out.println(employee.getFamily() + " " + count);
        }
        System.out.println("\nОбщий счёт");
        for (Employee employee : Employee.employees) {
            long countTasks = Employee.listTasks.stream().filter(t -> t.getAssigned().getFamily().equals(employee.getFamily())).count();
            System.out.println(employee.getFamily() + " " + countTasks);
        }
        System.out.println("\nВсего назначено обращений " + Employee.listTasks.size() + "\n");
    }

    private static void top() {
        IniciatorComparator iniciatorComparator = new IniciatorComparator();
        Iniciator.listIniciators.sort(iniciatorComparator);
        Integer limit = 0;
        while (true)    {
            limit = scanInteger("Введите лимит");
            if (limit > 0)  {
                break;
            }
            System.out.println("Лимит должен быть больше 0");
        }
        System.out.println("Топ-" + limit + " инициаторов");
        Integer i = 1;
        for (Iniciator iniciator : Iniciator.listIniciators)    {
            if (iniciator.getName().equals("")) {
                continue;
            }
            System.out.println(i + " " + iniciator.getName() + " " + iniciator.getCountTasks());
            i++;
            if (i > limit)    {
                break;
            }
        }

    }

    private static boolean searchInHistory (Tasks task) {
        boolean flag = false;
        HashMap <Date, TaskStatus> history = task.getHistory();
        for (Map.Entry<Date, TaskStatus> map : history.entrySet())  {
            if(map.getValue() == TaskStatus.NOTE_DONE && isNowDate(map.getKey())) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    private static boolean isNowDate (Date date)    {
        long dayInMill = 24 * 60 * 60 * 1000;
        long startDay = ((new Date()).getTime())/dayInMill * dayInMill;
        long endDay = startDay + dayInMill;
        long dateTime = date.getTime();
        return dateTime >= startDay && dateTime <= endDay;
    }

    private static long countAssignTaskNow(Employee employee)    {
        long count = 0;
        for(Tasks task : Employee.listTasks)    {
            if (task.getAssigned() == employee && searchInHistory(task))    {
                count++;
            }
        }
        return count;
    }

    private static HashMap<Integer, HashMap<Groups, String>> setNameGroups() {
        HashMap<Integer, HashMap<Groups, String>> nameGroups = new HashMap<>();
        HashMap<Groups, String> maps = new HashMap<>();
        maps.put(Groups.CREDENTIALS_1HD, "1-HD Полномочия");
        maps.put(Groups.SAP_SM_2, "2-Поддержка SAP SM");
        maps.put(Groups.JIRA_3, "3-Поддержка Jira");
        maps.put(Groups.CO_1, "2-ЦО Х5");
        Integer i = 1;
        for (Map.Entry<Groups, String> map : maps.entrySet())    {
            HashMap<Groups, String> buffer = new HashMap<>();
            buffer.put(map.getKey(), map.getValue());
            nameGroups.put(i , buffer);
            i++;
        }
        return nameGroups;
    }

    private static String getValues(HashMap <Groups, String> map) {
        for (Map.Entry <Groups, String> entry : map.entrySet())    {
            return entry.getValue();
        }
        return null;
    }

    private static Employee nowTaskOfAuthor (String author) {
        long count = Employee.listTasks.stream().filter(t -> t.getAuthor().equals(author) && isNowDate(t.getDateResolved())).count();
        if (count > 0)  {
            Tasks task = Employee.listTasks.stream().filter(t -> t.getAuthor().equals(author) && isNowDate(t.getDateResolved())).findFirst().get();
            return task.getAssigned();
        }
        return null;
    }

}
