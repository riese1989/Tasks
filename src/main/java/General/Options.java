package General;

import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;

public class Options {
    private static JSONObject fullJSON = new JSONObject();
    public static final String FILE_PATH = "map.json";
    public static final String FILE_EMPLOYEES_JSON = "Employees.json";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.y HH:mm:ss z");
    public static final String FILE_GROUPS = "Groups.json";

    public static JSONObject getFullJSON() {
        return fullJSON;
    }

    public static void setFullJSON(JSONObject fullJSON) {
        Options.fullJSON = fullJSON;
    }
}
