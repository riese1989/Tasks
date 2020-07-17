import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Main {
    static Employee pestov = new Employee(0, "pestov");
    static Employee batanov = new Employee(0, "batanov");
    static JSONObject fullJSON = new JSONObject();
    static String filePath = "map.json";


    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        Employee.employees.add(pestov);
        Employee.employees.add(batanov);
        ParseJSON.JSONtoArray();
        fullJSON = ParseJSON.getJSON();
        menu();
    }

    public static void menu() throws IOException, ParseException, java.text.ParseException {
        boolean flag = true;
        while (flag) {
            waitTasks();
            System.out.println("������� �������\n");
            System.out.println("1. ���������� ����� ���������");
            System.out.println("2. ������� ���� ���������");
            System.out.println("3. � ��������");
            System.out.println("4. �������");
            System.out.println("5. �� � ���");
            System.out.println("6. ����� ���� ���������");
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
        Tasks.setCountTasksAll(customScanner("������� ����� ���������?"));
        Tasks.setNoneAppTasks(customScanner("������� �������������?"));
        System.out.println(str + "\n���������\n" + str);
        for (int i = 1; i <= Tasks.getNoneAppTasks(); i++) {
            String number = enterCorrectNumber(true);
            if (number.equals("exit")) {
                break;
            }
            if (number.equals("nu")) {
                continue;
            }
            Employee empl = choiceAssignTask(pestov, batanov);
            Tasks task = new Tasks(number, empl, TaskStatus.NOTE_DONE, new Date());
            Employee.listTasks.add(task);
            System.out.println("��������� �� " + empl.getFamily());
            empl.setCountTaskOne(empl.getCountTaskOne() + 1);
            Employee.setAppCountTask(Employee.getAppCountTask() + 1);
            log(" " + empl.getFamily() + " �������� ", number, "NaTasks");
            makeJSON(task);
            writeJSON();
        }
    }

    //����� �����������
    public static Employee choiceAssignTask(Employee empl1, Employee empl2) {
        if ((int) (Math.random() * 2) == 0) {
            return empl1;
        }
        return empl2;
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
            Tasks task = Employee.listTasks.get(searchAndCreateTask(number, pestov));
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
    public static Integer customScanner(String message) {
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
        makeJSON(task);
        writeJSON();
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
                switchStatus(Employee.listTasks.get(searchAndCreateTask(enterCorrectNumber(false), pestov)), TaskStatus.NOT_US);
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

    //������ � json
    private static void writeJSON() throws IOException {
        searchFile(filePath);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(fullJSON.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //����� � json
    public static void makeJSON(Tasks task) {
        JSONObject taskJSON = new JSONObject();
        JSONObject obj = (JSONObject) fullJSON.get(task.getNumber());
        JSONArray historyTaskJSON = new JSONArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss z");
        JSONObject partHistoryTaskJSON = new JSONObject();
        if (obj != null) {
            historyTaskJSON = (JSONArray) obj.get("History");
        }
        partHistoryTaskJSON.put(task.getStatus(), dateFormat.format(new Date()));
        historyTaskJSON.add(partHistoryTaskJSON);
        taskJSON.put("Assigned", task.getAssigned().getFamily());
        taskJSON.put("Current status", task.getStatus().toString());
        taskJSON.put("History", historyTaskJSON);
        obj = taskJSON;
        fullJSON.put(task.getNumber(), taskJSON);
    }

    //����� �����
    public static boolean searchFile(String path) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
            return false;
        }
        return true;
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
                    (date.getTime() - tasks.getDateResolved().getTime() >= countDaysWait * 24 * 60 * 60 * 1000)) {
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
}
