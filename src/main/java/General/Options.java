package General;

import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;

public class Options<T> {

  private static JSONObject fullJSON = new JSONObject();
  private static final String FOLDER_PATH = "json/";
  public static final String FILE_PATH = FOLDER_PATH + "map.json";
  public static final String FILE_EMPLOYEES_JSON = FOLDER_PATH + "Employees.json";
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.y HH:mm:ss z");
  public static final String FILE_GROUPS = FOLDER_PATH + "Groups.json";

  public static JSONObject getFullJSON() {
    return fullJSON;
  }

  public static void setFullJSON(JSONObject fullJSON) {
    Options.fullJSON = fullJSON;
  }

}
