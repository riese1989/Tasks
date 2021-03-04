package app.operations;

import app.enums.TaskStatus;
import app.pojo.Employee;
import app.general.JSONOperations;
import app.general.Operations;
import app.enums.EnumGroups;
import app.pojo.Group;
import app.pojo.Iniciator;
import app.repositories.Repo;
import app.repositories.RepoEmpl;
import app.repositories.RepoInic;
import app.repositories.RepoTasks;
import app.pojo.Task;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import static app.general.Operations.isNowDate;
import static app.general.Operations.scanInteger;

@Component
public class OperationsTask {

  private OperationsEmployee operationsEmployee = null;
  private JSONOperations jsonOperations = null;
  private RepoTasks repoTasks;
  private RepoEmpl repoEmpl;
  private ApplicationContext context;
  private RepoInic repoInic;
  private OperationGroups operationGroups;

  public OperationsTask(@Autowired ApplicationContext context) {
    this.operationsEmployee = context.getBean(OperationsEmployee.class);
    this.jsonOperations = context.getBean(JSONOperations.class);
    this.repoTasks = context.getBean(RepoTasks.class);
    this.repoEmpl = context.getBean(RepoEmpl.class);
    this.context = context;
    this.repoInic = context.getBean(RepoInic.class);
    this.operationGroups = context.getBean(OperationGroups.class);
  }

  public void getWaitTasks() {
    waitTasks();
  }

  private ArrayList<Task> waitTasks() {
    ArrayList<Task> arrWait = new ArrayList<>();
    Date date = new Date();
    Integer countDaysWait = 2;
    repoTasks.get().forEach((tasks -> {
      if (tasks.getStatus() == TaskStatus.WAITING &&
          !isDifTrue(date, tasks.getDateResolved(), countDaysWait)) {
        arrWait.add(tasks);
      }
    }));
    if (arrWait.size() > 0) {
      System.out.println("� ���� ��������� ���������� ��������:");
      arrWait.forEach(t -> {
        System.out.println(t.getNumber() + " " + t.getDateResolved());
      });
    }
    return arrWait;
  }

  private boolean isDifTrue(Date date, Date taskDate, Integer countWaitDays) {
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

  //���� ������ ���������
  public void enterTasks() throws IOException, ParseException {
    String str = "========================";
    Task.setNoneAppTasks(Operations.scanInteger("������� �������������?"));
    System.out.println(str + "\n���������\n" + str);
    for (int i = 1; i <= Task.getNoneAppTasks(); i++) {
      String number = enterCorrectNumber(true);
      if (number.equals("exit")) {
        break;
      }
      if (number.equals("nu")) {
        continue;
      }
      System.out.println("���������");
      String author = Operations.scanLine();
      Iniciator iniciator = repoInic.getIniciatorRepo(author);
      iniciator.setField("name", author).incCountTasks();
      repoInic.append(iniciator);
      Employee empl = choiceAssignTask(author);
      empl.setCountTaskOne(empl.getCountTaskOne() + 1);
      empl.incTaskOfThisSession();
      Task task = context.getBean(Task.class);
      task.setField("number", number)
          .setField("assigned", empl)
          .setField("status", TaskStatus.NOTE_DONE)
          .setField("dateResolved", new Date())
          .setField("author", author);
      repoTasks.append(task);
      System.out.println("��������� �� " + empl.getFamily());
      Employee.setCountTasks(Employee.getCountTasks() + 1);
      Operations.log(" " + empl.getFamily() + " �������� ", number, "NaTasks");
      jsonOperations.makeJSON(task);
      jsonOperations.writeJSON();
    }
  }

  //������������ ����� ������ ���������
  public String enterCorrectNumber(boolean flag) throws IOException {
    for (; ; ) {
      System.out.println("������� �����");
      String number = Operations.scanLine();
      if (number.equals("q")) {
        return "exit"; //���������
      }
      if (number.equals("nu")) {
        System.out.println("������� ������������ ������� �������");
        switchStatus(enterCorrectNumber(false), TaskStatus.NOT_US);
        return "nu";
      }
      if (isCorrectNumber(number)) {
        Task task = returnTask(number);
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

  public boolean isCorrectNumber(String number) {
    String[] splitNubmer = number.split("-");
    number = number.replace(" ", "");
    if ((number.length() == 13 && splitNubmer.length == 2) || (number.length() == 17
        && splitNubmer.length == 3)) {
      return true;
    }
    return false;
  }

  //����� ��������� � Employess.listTasks
  public Integer searchAndCreateTask(String number, Employee empl) {
    Task task = returnTask(number);
    if (task != null) {
      return repoTasks.getIndex(task);
    }
    task.setField("number", number)
        .setField("assigned", empl)
        .setField("status", TaskStatus.NOTE_DONE);
    repoTasks.append(task);
    return repoTasks.getIndex(task);
  }

  //���������� ���������
  private Task returnTask(String number) {
    for (Task taskEmpl : repoTasks.get()) {
      if (taskEmpl.getNumber().equals(number)) {
        return taskEmpl;
      }
    }
    return null;
  }

  //����� �����������
  public Employee choiceAssignTask(String author) {
    int size = repoEmpl.size();
    for (; ; ) {
      int random = (int) (Math.random() * size);
      Employee empl = nowTaskOfAuthor(author);
      if (empl != null) {
        return empl;
      }
      empl = repoEmpl.get().get(random);
      if (empl.currentVacation() || !empl.getStatus()) {
        continue;
      }
      return empl;
    }
  }

  private Employee nowTaskOfAuthor(String author) {
    long count = repoTasks.get().stream()
        .filter(t -> t.getAuthor().equals(author) && isNowDate(t.getDateResolved())).count();
    if (count > 0) {
      Task task = repoTasks.get().stream()
          .filter(t -> t.getAuthor().equals(author) && isNowDate(t.getDateResolved())).findFirst()
          .get();
      return task.getAssigned();
    }
    return null;
  }

  //������������ �������
  public void switchStatus(String numberTask, TaskStatus status) throws IOException {
    Employee pestov = operationsEmployee.getEmployee("pestov");
    Task task = repoTasks.get().get(searchAndCreateTask(numberTask, pestov));
    //HashMap<Integer, HashMap<EnumGroups, String>> nameGroups = setNameGroups();
    ArrayList<Group> nameGroups = operationGroups.getListGroups();
    //int index = Access.getRepoTasks().get().indexOf(task);
    String stat = "";
    task.setField("status", status)
        .setField("dateResolved", new Date());
    if (status != TaskStatus.TASK) {
      System.out.println("����������� ");
      task.setField("comment", Operations.scanLine());
    }
    if (status == TaskStatus.TASK) {
      StringBuilder names = new StringBuilder();
      for (int i = 1; i <= nameGroups.size(); i++) {
        names.append(i).append(" ").append(nameGroups.get(i - 1).getName()).append("\n");
      }
      int group = Operations.scanInteger("�� ����� ������ ���������?\n" + names);
      if (group > nameGroups.size() || group < 1) {
        System.out.println("�������� id ������");
      } else {
        task.setField("comment", nameGroups.get(group - 1).getName());
        Operations.log(" �������� ������� ", task.getNumber(), "TTasks");
        stat = "�������� �������";
      }
    }
    if (status == TaskStatus.WAITING) {
      Operations.log(" ��������� � �������� ", task.getNumber(), "WTasks");
      stat = "��������";
    }
    if (status == TaskStatus.DONE) {
      Operations.log(" ����� ", task.getNumber(), "STasks");
      stat = "�����";
    }
    if (status == TaskStatus.NOT_US) {
      if (task.getAuthor() == null) {
        System.out.println("���������");
        String author = Operations.scanLine();
        task.setAuthor(author);
      }
      Operations.log(" ������������������� ", task.getNumber(), "NTasks");
      stat = "�������������������";
    }
    jsonOperations.makeJSON(task);
    jsonOperations.writeJSON();
    System.out.println("� " + task.getNumber() + " ������ ���������� �� " + stat);
  }

  //������� ���������
  public void solveMyTasks(String haveNumber) throws IOException {
    String number = "";
    if (!haveNumber.equals("")) {
      number = haveNumber;
    } else {
      number = enterCorrectNumber(false);
    }
    if (!number.equals("exit")) {
      Employee employee = repoEmpl.getCurrentEmployee();
      int index = searchAndCreateTask(number, employee);
      Task task = repoTasks.get().get(index);
      task.setField("assigned", employee);
      switchStatus(task.getNumber(), TaskStatus.DONE);
    }
  }

  //�������������� ���� ���������
  public boolean printTasks() {
    for (Task task : repoTasks.get()) {
      String str = "****************\n";
      if (task.getAssigned().getFamily().equals("pestov") &&
          task.getStatus() != TaskStatus.DONE &&
          task.getStatus() != TaskStatus.NOT_US) {
        System.out.println(str + "����� " + task.getNumber() + "\n" +
            "������  " + task.getStatus());
      }
    }
    return true;
  }

  public boolean manualAssignment() {
    Employee assignee = null;
    Integer i = 0;
    Integer enterNumber = 0;
    Task task;
    boolean flag = true;
    boolean flag2 = false;
    try {
      task = repoTasks.get()
          .get(searchAndCreateTask(enterCorrectNumber(false), assignee));
    } catch (IOException ex) {
      ex.printStackTrace();
      return false;
    }
    task.setField("dateResolved", new Date());
    if (task.getAssigned() != null) {
      System.out.println("������ ��� ��������� �� " + task.getAssigned().getFamily());
    }
    ArrayList<Employee> trueEmpl = new ArrayList<>();
    while (flag) {
      for (Employee empl : repoEmpl.get()) {
        if (empl.getStatus()) {
          i++;
          System.out.println(i + " " + empl.getFamily());
          trueEmpl.add(empl);
          flag2 = true;
        }
      }
      enterNumber = scanInteger("�� ���� ��������� ��� ��������� (������� �����)?");
      if (enterNumber < i + 1 && enterNumber != 0) {
        assignee = trueEmpl.get(enterNumber - 1);
        task.setField("assigned", assignee)
            .setField("status", TaskStatus.NOTE_DONE);
        System.out
            .println("��������� " + task.getNumber() + " ��������� �� " + assignee.getFamily());
        flag = false;
        continue;
      }
      if (enterNumber == 0 && flag2 && enterNumber > i - 1) {
        System.out.println("�������� ������ ���� ������ 0 � ������ " + (i - 1));
      }
    }
    jsonOperations.makeJSON(task);
    try {
      jsonOperations.writeJSON();
      return true;
    } catch (IOException ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public boolean search() throws IOException {
    String number = enterCorrectNumber(false);
    Task task = returnTask(number);
    if (task != null) {
      System.out.println("������� ������ " + Operations.statusToString(task.getStatus()));
      String author = task.getAuthor();
      if (author != null) {
        System.out.println("����� " + task.getAuthor());
      }
      System.out.println("�������� �� " + task.getAssigned().getFamily());
      System.out.println("������� ��������� ��������:");
      HashMap<Date, TaskStatus> history = task.getHistory();
      for (Map.Entry<Date, TaskStatus> hist : history.entrySet()) {
        String status = Operations.statusToString(hist.getValue());
        String date = hist.getKey().toString();
        System.out.println("������ " + status);
        System.out.println("���� " + date);
        System.out.println();
      }
      return true;
    } else {
      System.out.println("��������� �� �������");
      return false;
    }
  }

  public boolean searchInHistory(Task task) {
    boolean flag = false;
    HashMap<Date, TaskStatus> history = task.getHistory();
    for (Map.Entry<Date, TaskStatus> map : history.entrySet()) {
      if (map.getValue() == TaskStatus.NOTE_DONE && isNowDate(map.getKey())) {
        flag = true;
        break;
      }
    }
    return flag;
  }
}
