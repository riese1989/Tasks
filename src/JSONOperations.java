import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class JSONOperations {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss z");
    public static JSONObject getJSON(String path) throws IOException, ParseException {
//        if (searchFile(Main.filePath)) {
//            return new JSONObject();
//        }
        searchFile(path);
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(reader);
    }

    public static void JSONtoArray() throws IOException, ParseException, java.text.ParseException {
        JSONObject objs = getJSON(Main.filePath);
        Set maps = objs.keySet();
        for (Object map : maps) {
            JSONObject obj = (JSONObject) objs.get(map.toString());
            String number = map.toString();
            String assigned = obj.get("Assigned").toString();
            String author = newStringField(obj, "Author");
            String comment = newStringField(obj, "Comment");
            JSONArray history = (JSONArray) obj.get("History");
            String stringDate = history.get(history.size()-1).toString().split("\"")[3];
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.y HH:mm:ss z");
            Date date = format.parse(stringDate);
            TaskStatus status = Tasks.toStatus(obj.get("Current status").toString());
            Tasks task = new Tasks(number, Employee.getEmployee(assigned), status, date, author, comment);
            Employee.listTasks.add(task);
        }
    }

    //поиск в json
    public static void makeJSON(Tasks task) {
        JSONObject taskJSON = new JSONObject();
        JSONObject obj = (JSONObject) Main.fullJSON.get(task.getNumber());
        JSONArray historyTaskJSON = new JSONArray();
        JSONObject partHistoryTaskJSON = new JSONObject();
        if (obj != null) {
            historyTaskJSON = (JSONArray) obj.get("History");
        }
        partHistoryTaskJSON.put(task.getStatus(), dateFormat.format(new Date()));
        historyTaskJSON.add(partHistoryTaskJSON);
        taskJSON.put("Assigned", task.getAssigned().getFamily());
        taskJSON.put("Author", task.getAuthor());
        taskJSON.put("Comment", task.getComment());
        taskJSON.put("Current status", task.getStatus().toString());
        taskJSON.put("History", historyTaskJSON);
        obj = taskJSON;
        Main.fullJSON.put(task.getNumber(), taskJSON);
    }

    //запись в json
    static void writeJSON() throws IOException {
        searchFile(Main.filePath);
        try (FileWriter file = new FileWriter(Main.filePath)) {
            file.write(Main.fullJSON.toString());
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

    public static HashMap<Date, Date> JSONtoHashMap(Employee employee) throws IOException, ParseException, java.text.ParseException {
        HashMap<Date, Date> vacations = new HashMap<>();
        JSONObject objs = getJSON(Main.filePathVactions);
        Set maps = objs.keySet();
        for (Object map : maps) {
            JSONObject obj = (JSONObject) objs.get(map.toString());
            String familyJSON = map.toString();
            if (familyJSON.equals(employee.getFamily()))  {
                JSONArray vacationsFamilyJSON = (JSONArray)obj.get("vacations");
                for (Object vacationFamily : vacationsFamilyJSON)   {
                    String[] parts = vacationFamily.toString().split("\"");
                    Date start = dateFormat.parse(parts[3]);
                    Date end = dateFormat.parse(parts[7]);
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
