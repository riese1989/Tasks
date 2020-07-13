import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class ParseJSON {
    public static JSONObject getJSON() throws IOException, ParseException {
        if (!Main.searchFile(Main.filePath)) {
            return new JSONObject();
        }
        FileReader reader = new FileReader(Main.filePath);
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(reader);
    }

    public static void JSONtoArray() throws IOException, ParseException, java.text.ParseException {
        JSONObject objs = getJSON();
        Set maps = objs.keySet();
        for (Object map : maps) {
            JSONObject obj = (JSONObject) objs.get(map.toString());
            String number = map.toString();
            String assigned = obj.get("Assigned").toString();
            JSONArray history = (JSONArray) obj.get("History");
            String stringDate = history.get(history.size()-1).toString().split("\"")[3];
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.y HH:mm:ss z");
           // 13.07.2020 10:01:31 MSK
            Date date = format.parse(stringDate);
            TaskStatus status = Tasks.toStatus(obj.get("Current status").toString());
            Tasks task = new Tasks(number, Employee.getEmployee(assigned), status, date);
            Employee.listTasks.add(task);
        }
    }
}
