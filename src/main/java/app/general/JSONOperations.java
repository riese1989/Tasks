package app.general;

import app.pojo.Employee;
import app.operations.OperationsEmployee;
import app.enums.EnumGroups;
import app.pojo.Group;
import app.pojo.Iniciator;
import app.repositories.Access;
import app.repositories.Repo;
import app.pojo.Task;
import app.enums.TaskStatus;
import app.config.FileConfig;
import app.repositories.RepoGroups;
import app.repositories.RepoInic;
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
  private String fileFamiliesPath;
  private Access access;
  private Repo<Employee> employeeRepo;
  private ApplicationContext context = null;
  private RepoInic repoInic;

  public JSONOperations(@Autowired FileConfig fileConfig,
      @Autowired ApplicationContext context,
      @Autowired Access access) {
    filePath = fileConfig.getMapPath();
    fileGroups = fileConfig.getGroupPath();
    filePathEmployee = fileConfig.getPathEmployee();
    fileFamiliesPath = fileConfig.getFamiliesPath();
    this.context = context;
    this.access = access;
    employeeRepo = access.getRepoEmpl();
    repoInic = context.getBean(RepoInic.class);
  }

  public HashMap<String, String> getFamilies() throws IOException, ParseException {
    JSONObject objs = getJSON(fileFamiliesPath);
    HashMap<String, String> families = new HashMap<>();
    Set maps = objs.keySet();
    for (Object map : maps) {
      String oldFamily = map.toString();
      String newFamily = objs.get(map).toString();
      families.put(oldFamily,newFamily);
    }
    return families;
  }

  public JSONObject getJSON(String path) throws IOException, ParseException {

    searchFile(path);
    FileReader reader = new FileReader(path);
    JSONParser jsonParser = new JSONParser();
    if (path.contains("Map.json")) {
      fullJSON = (JSONObject) jsonParser.parse(reader);
      return fullJSON;
    }
    return (JSONObject) jsonParser.parse(reader);
  }

  public ArrayList<Group> getGroupsFromJSON() throws IOException, ParseException {
    JSONObject objs = getJSON(fileGroups);
    Repo<Group> repo = context.getBean(RepoGroups.class);
    Set maps = objs.keySet();
    for (Object map : maps) {
      String key = map.toString();
      EnumGroups enumGroups = EnumGroups.valueOf(key);
      String name = objs.get(key).toString();
      Group group = context.getBean(Group.class);
      group.setField("enumGroups", enumGroups).setField("name", name);
      repo.append(group);
    }
    return repo.get();
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
      employee.setField("countTaskOne", 0)
          .setField("family", family)
          .setField("vacations", vacations)
          .setField("status", true)
          .setField("isCurrent", isCurrent);
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
      Task task = context.getBean(Task.class);
      task.setField("number", number)
          .setField("assigned", employee)
          .setField("status", status)
          .setField("dateResolved", new Date())
          .setField("author", author)
          .setField("comment", comment)
          .setField("history", historyTask)
          .setField("dateResolved", date);
      employee.incCountAllTasksEmpl();
      Iniciator iniciator = repoInic.getIniciatorRepo(author).setField("name", author);
      iniciator.incCountTasks();
      repoInic.append(iniciator);
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
