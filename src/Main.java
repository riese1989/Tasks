import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Main {
    static JSONObject fullJSON = new JSONObject();
    static String filePath = "map.json";
    static String filePathVactions = "vacations.json";
    static String fileEmployees = "Employees.txt";


    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        readEmployees();
        JSONOperations.JSONtoArray();
//        JSONOperations.JSONtoHashMap(Employee.getEmployee("pestov"));
//        JSONOperations.JSONtoHashMap(Employee.getEmployee("batanov"));
        fullJSON = JSONOperations.getJSON(filePath);
        System.out.println("�� ����������� ���� � ��������� " + counter() + " ���������");
        menu();
    }

    public static void menu() throws IOException, ParseException, java.text.ParseException {
        boolean flag = true;
        while (flag) {
            waitTasks();
            System.out.println("������� �������\n");
            System.out.println("1. �������������� ���������� ����� ���������");
            System.out.println("2. ������� ���� ���������");
            System.out.println("3. � ��������");
            System.out.println("4. �������");
            System.out.println("5. �� � ���");
            System.out.println("6. ����� ���� ���������");
            System.out.println("7. ������ ���������� ���������");
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

    //���� ������ ���������
    public static void enterTasks() throws IOException, ParseException {
        String str = "========================";
        Tasks.setNoneAppTasks(scanInteger("������� �������������?"));
        System.out.println(str + "\n���������\n" + str);
        for (int i = 1; i <= Tasks.getNoneAppTasks(); i++) {
            String number = enterCorrectNumber(true);
            if (number.equals("exit")) {
                break;
            }
            if (number.equals("nu")) {
                continue;
            }
            Employee empl = choiceAssignTask();
            System.out.println("���������");
            Tasks task = new Tasks(number, empl, TaskStatus.NOTE_DONE, new Date(), scanLine());
            Employee.listTasks.add(task);
            System.out.println("��������� �� " + empl.getFamily());
            empl.setCountTaskOne(empl.getCountTaskOne() + 1);
            Employee.setAppCountTask(Employee.getAppCountTask() + 1);
            log(" " + empl.getFamily() + " �������� ", number, "NaTasks");
            JSONOperations.makeJSON(task);
            JSONOperations.writeJSON();
        }
    }

    //����� �����������
    public static Employee choiceAssignTask() {
        Integer size = Employee.employees.size();
        for(;;) {
            Integer random = (int) (Math.random() * size);
            Employee empl = Employee.employees.get(random);
            if(empl.currentVacation())    {
                random = (int) (Math.random() * size);
                continue;
            }
            return empl;
        }
    }

    //������� ���������
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

    //�������������� ���� ���������
    public static void printTasks() {
        for (Tasks task : Employee.listTasks) {
            String str = "****************\n";
            if (task.getAssigned().getFamily().equals("pestov") &&
                    task.getStatus() != TaskStatus.DONE &&
                    task.getStatus() != TaskStatus.NOT_US) {
                System.out.println(str + "����� " + task.getNumber() + "\n" +
                        "������  " + task.getStatus());
            }
        }
    }

    //������� ����� ����� �� ���������� ������
    public static Integer scanInteger(String message) {
        String string;
        for (; ; ) {
            System.out.println(message);
            string = scanLine();
            if (string.matches("[-+]?\\d+")) {
                return Integer.parseInt(string);
            }
            System.out.println("�� ����� �� �����");
        }
    }

    //�����������
    private static void log(String message, String number, String nameLogger) {
        Logger logger = LogManager.getLogger(nameLogger);
        logger.info(message + number);
    }

    //����� ��������� � Employess.listTasks
    private static Integer searchAndCreateTask(String number, Employee empl) {
        Tasks task = returnTask(number);
        if (task != null) {
            return Employee.listTasks.indexOf(task);
        }
        task = new Tasks(number, empl, TaskStatus.NOTE_DONE);
        Employee.listTasks.add(task);
        return Employee.listTasks.indexOf(task);
    }

    //���������� ���������
    private static Tasks returnTask(String number) {
        for (Tasks taskEmpl : Employee.listTasks) {
            if (taskEmpl.getNumber().equals(number)) {
                return taskEmpl;
            }
        }
        return null;
    }

    //������������ �������
    private static void switchStatus(Tasks task, TaskStatus status) throws IOException {
        int index = Employee.listTasks.indexOf(task);
        String stat = "";
        task.setStatus(status);
        task.setDateResolved(new Date());
        System.out.println("����������� ");
        task.setComment(scanLine());
        Employee.listTasks.set(index, task);
        if (status == TaskStatus.TASK) {
            log(" �������� ������� ", task.getNumber(), "TTasks");
            stat = "�������� �������";
        }
        if (status == TaskStatus.WAITING) {
            log(" ��������� � �������� ", task.getNumber(), "WTasks");
            stat = "��������";
        }
        if (status == TaskStatus.DONE) {
            log(" ����� ", task.getNumber(), "STasks");
            stat = "�����";
        }
        if (status == TaskStatus.NOT_US) {
            log(" ������������������� ", task.getNumber(), "NTasks");
            stat = "�������������������";
        }
        JSONOperations.makeJSON(task);
        JSONOperations.writeJSON();
        System.out.println("� " + task.getNumber() + " ������ ���������� �� " + stat);
    }

    //������������ ����� ������ ���������
    private static String enterCorrectNumber(boolean flag) throws IOException {
        for (; ; ) {
            System.out.println("������� �����");
            String number = scanLine();
            if (number.equals("q")) {
                return "exit"; //���������
            }
            if (number.equals("nu")) {
                System.out.println("������� ������������ ������� �������");
                switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), Employee.getEmployee("pestov"))), TaskStatus.NOT_US);
                return "nu";
            }
            if (isCorrectNumber(number)) {
                Tasks task = returnTask(number);
                if (task != null && flag == true) {
                    System.out.println("������ ��������� ��� ��������� �� " + task.getAssigned().getFamily());
                } else {
                    return number;
                }
            } else {
                System.out.println("����� ��������");
            }
        }
    }

    //������� ������ ����� �� ���������� ������
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
            System.out.println("� ���� ��������� ���������� ��������:");
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
        if (task != null) {
            System.out.println("������ ��� ��������� �� " + task.getAssigned().getFamily());
        }

        while (flag) {
            if (enterNumber < i+1 && enterNumber != 0) {
                assignee = Employee.employees.get(enterNumber-1);
                task.setAssigned(assignee);
                task.setStatus(TaskStatus.NOTE_DONE);
                System.out.println("��������� " + task.getNumber() + "��������� �� " + assignee.getFamily());
                flag = false;
                continue;
            }
            if (enterNumber == 0 && flag2 == true && enterNumber > i-1) {
                System.out.println("�������� ������ ���� ������ 0 � ������ " + (i-1));
            }
            for (Employee empl : Employee.employees) {
                i++;
                System.out.println(i + " " + empl.getFamily());
                flag2 = true;
            }
            enterNumber = scanInteger("�� ���� ��������� ��� ��������� (������� �����)?");
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

    private static Integer counter()    {
        Integer count = 0;
        Employee empl = Employee.getEmployee("pestov");
        for (Tasks task : Employee.listTasks)   {
            if (task.getAssigned() == empl && compareDate(task.getDateResolved()))  {
                count++;
            }
        }
        return count;
    }

    private static boolean compareDate(Date dateResolved)  {
        Calendar calNow =dateToCalendar(new Date());
        Calendar calResolved = dateToCalendar(dateResolved);
        if ((calNow.get(Calendar.DAY_OF_MONTH) == calResolved.get(Calendar.DAY_OF_MONTH)) &&
                (calNow.get(Calendar.MONTH) == calResolved.get(Calendar.MONTH)) &&
                (calNow.get(Calendar.YEAR) == calResolved. get(Calendar.YEAR))) {
            return true;
        }
        return false;
    }

    private static Calendar dateToCalendar(Date date)   {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
