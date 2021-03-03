package app.General;

import app.Application;
import app.Employees.Employee;
import app.Employees.OperationsEmployee;
import app.Groups.EnumGroups;
import app.Groups.Group;
import app.Repositories.Access;
import app.Repositories.Repo;
import app.Tasks.AccessRepo;
import app.Tasks.Task;
import app.Tasks.TaskStatus;
import app.config.Config;
import app.config.FileConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component

public class JSONOperations {

  private SimpleDateFormat formatDate = Options.DATE_FORMAT;
  private JSONObject fullJSON = new JSONObject();
  private String filePath;
  private String fileGroups;
  private String filePathEmployee;
  private Access access;
  private Repo<Employee> employeeRepo;
  private ApplicationContext context = null;

  public JSONOperations(@Autowired FileConfig fileConfig,
      @Autowired ApplicationContext context,
      @Autowired Access access)  {
    filePath = fileConfig.getMapPath();
    fileGroups = fileConfig.getGroupPath();
    filePathEmployee = fileConfig.getPathEmployee();
    this.context = context;
    this.access = access;
    employeeRepo = access.getRepoEmpl();
  }


  public JSONObject getJSON(String path) throws IOException, ParseException {

    searchFile(path);
    FileReader reader = new FileReader(path);
    JSONParser jsonParser = new JSONParser();
    if (path.contains("map.json")) {
      fullJSON = (JSONObject) jsonParser.parse(reader);
      return fullJSON;
    }
    return (JSONObject) jsonParser.parse(reader);
  }

  public ArrayList<Group> getGroupsFromJSON() throws IOException, ParseException {
    ArrayList<Group> groups = new ArrayList<>();
    JSONObject objs = getJSON(fileGroups);
    Set maps = objs.keySet();
    for (Object map : maps) {
      String key = map.toString();
      EnumGroups enumGroups = EnumGroups.valueOf(key);
      String name = objs.get(key).toString();
      Group group = new Group(enumGroups, name);
      groups.add(group);

    }
    return groups;
  }

  public void JSONToArrayEmployee()
      throws IOException, ParseException, java.text.ParseException {
    JSONObject objs = getJSON(filePathEmployee);
    Set maps = objs.keySet();
    for (Object map : maps) {
      String family = map.toString();
      JSONObject obj = (JSONObject) objs.get(family);
      boolean status = Boolean.parseBoolean(obj.get("status").toString());
      boolean isCurrent = Boolean.parseBoolean((obj.get("current").toString()));
      JSONArray vacationsJSON = (JSONArray) obj.get("vacations");
      HashMap<Date, Date> vacations = new HashMap<>();
      for (Object vacationJSON : vacationsJSON) {
        String[] parts = vacationJSON.toString().split("\"");
        Date start = formatDate.parse(parts[3]);
        Date end = formatDate.parse(parts[7]);
        vacations.put(start, end);
      }
      Employee employee = context.getBean(Employee.class);
      employee.setFields(0, family, vacations, status, isCurrent);
      employeeRepo.append(employee);
    }
  }

  public void JSONToArrayTask()
      throws IOException, ParseException, java.text.ParseException {
    JSONObject objs = getJSON(filePath);
    Set maps = objs.keySet();
    for (Object map : maps) {
      JSONObject obj = (JSONObject) objs.get(map.toString());
      String number = map.toString();
      String assigned = obj.get("Assigned").toString();
      String author = newStringField(obj, "Author");
      String comment = newStringField(obj, "Comment");
      JSONArray history = (JSONArray) obj.get("History");
      String stringDate = history.get(history.size() - 1).toString().split("\"")[3];
      HashMap<Date, TaskStatus> historyTask = new HashMap<>();
      Date date = formatDate.parse(stringDate);
      for (int i = 0; i < history.size(); i++) {
        String[] str = history.get(i).toString().split("\"");
        Date dateHist = formatDate.parse(str[3]);
        TaskStatus status = Operations.convertToStatus(str[1]);
        historyTask.put(dateHist, status);
      }
      TaskStatus status = Operations.convertToStatus(obj.get("Current status").toString());
      OperationsEmployee operationsEmployee = context.getBean(OperationsEmployee.class);
      Employee employee = operationsEmployee.getEmployee(assigned);
      Task task = new Task(number, employee, status, date, author, comment, historyTask);
      employee.incCountAllTasksEmpl();
      access.getRepoTasks().append(task);
    }
  }

  //поиск в json
  public void makeJSON(Task task) {
    JSONObject taskJSON = new JSONObject();
    JSONObject obj = (JSONObject) fullJSON.get(task.getNumber());
    JSONArray historyTaskJSON = new JSONArray();
    JSONObject partHistoryTaskJSON = new JSONObject();
    if (obj != null) {
      historyTaskJSON = (JSONArray) obj.get("History");
    }
    partHistoryTaskJSON.put(task.getStatus(), formatDate.format(new Date()));
    historyTaskJSON.add(partHistoryTaskJSON);
    taskJSON.put("Assigned", task.getAssigned().getFamily());
    taskJSON.put("Author", task.getAuthor());
    taskJSON.put("Comment", task.getComment());
    taskJSON.put("Current status", task.getStatus().toString());
    taskJSON.put("History", historyTaskJSON);
    obj = taskJSON;
    fullJSON.put(task.getNumber(), taskJSON);
  }

  //запись в json
  public void writeJSON() throws IOException {
    searchFile(filePath);
    try (FileWriter file = new FileWriter(filePath)) {
      file.write(fullJSON.toString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //поиск файла
  public static boolean searchFile(String path) throws IOException {
    File f = new File(path);
    if (!f.exists()) {
      f.createNewFile();
      return false;
    }
    return true;
  }

  private static String newStringField(JSONObject obj, String field) {
    String returnValue;
    try {
      returnValue = obj.get(field).toString();
    } catch (Exception ex) {
      returnValue = "";
    }
    return returnValue;
  }
}
