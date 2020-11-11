package General;

import Employees.Employee;
import Employees.OperationsEmployee;
import Tasks.Task;
import Tasks.TaskStatus;
import jdk.nashorn.api.scripting.ScriptUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class JSONOperations {
    private static SimpleDateFormat formatDate = Options.getFormatDate();
    private static JSONObject fullJSON = Options.getFullJSON();
    private static String filePath = Options.getFilePath();
    private static String filePathVacations = Options.getFilePathVacations();

    public static JSONObject getJSON(String path) throws IOException, ParseException {
        searchFile(path);
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(reader);
    }

    public static void JSONToArrayEmployee() throws IOException, ParseException, java.text.ParseException {
        JSONObject objs = getJSON(Options.getFileEmployeesJSON());
        Set maps = objs.keySet();
        for (Object map : maps) {
            String family = map.toString();
            JSONObject obj = (JSONObject) objs.get(family);
            boolean status = Boolean.parseBoolean(obj.get("status").toString());
            JSONArray vacationsJSON = (JSONArray) obj.get("vacations");
            HashMap<Date, Date> vacations = new HashMap<>();
            for (Object vacationJSON : vacationsJSON)   {
                String[] parts = vacationJSON.toString().split("\"");
                Date start = formatDate.parse(parts[3]);
                Date end = formatDate.parse(parts[7]);
                vacations.put(start,end);
            }
            Employee.listEmployees.add(new Employee(0,family,vacations, status));
        }
    }

    public static void JSONToArrayTask() throws IOException, ParseException, java.text.ParseException {
        JSONObject objs = getJSON(Options.getFilePath());
        Set maps = objs.keySet();
        for (Object map : maps) {
            JSONObject obj = (JSONObject) objs.get(map.toString());
            String number = map.toString();
            String assigned = obj.get("Assigned").toString();
            String author = newStringField(obj, "Author");
            String comment = newStringField(obj, "Comment");
            JSONArray history = (JSONArray) obj.get("History");
            String stringDate = history.get(history.size()-1).toString().split("\"")[3];
            HashMap <Date, TaskStatus> historyTask = new HashMap<>();
            Date date = formatDate.parse(stringDate);
            for (int i = 0; i < history.size(); i++)    {
                String[] str = history.get(i).toString().split("\"");
                Date dateHist = formatDate.parse(str[3]);
                TaskStatus status = Operations.convertToStatus(str[1]);
                historyTask.put(dateHist, status);
            }
            TaskStatus status = Operations.convertToStatus(obj.get("Current status").toString());
            Task task = new Task(number, OperationsEmployee.getEmployee(assigned), status, date, author, comment, historyTask);
            Employee.listTasks.add(task);
        }
    }

    //поиск в json
    public static void makeJSON(Task task) {
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
    public static void writeJSON() throws IOException {
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

    public static HashMap<Date, Date> JSONtoHashMapVacations(Employee employee) throws IOException, ParseException, java.text.ParseException {
        HashMap<Date, Date> vacations = new HashMap<>();
        JSONObject objs = getJSON(filePathVacations);
        Set maps = objs.keySet();
        for (Object map : maps) {
            JSONObject obj = (JSONObject) objs.get(map.toString());
            String familyJSON = map.toString();
            if (familyJSON.equals(employee.getFamily()))  {
                JSONArray vacationsFamilyJSON = (JSONArray)obj.get("vacations");
                for (Object vacationFamily : vacationsFamilyJSON)   {
                    String[] parts = vacationFamily.toString().split("\"");
                    Date start = formatDate.parse(parts[3]);
                    Date end = formatDate.parse(parts[7]);
                    vacations.put(start,end);
                }

            }
        }
        employee.setVacations(vacations);
        return vacations;

    }

    private static String newStringField (JSONObject obj, String field) {
        String returnValue;
        try {
            returnValue = obj.get(field).toString();
        }
        catch (Exception ex)    {
            returnValue = "";
        }
        return returnValue;
    }
}
