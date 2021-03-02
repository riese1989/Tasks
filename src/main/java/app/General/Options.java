package app.General;

import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import org.springframework.context.ApplicationContext;

public class Options {
  private static JSONObject fullJSON = new JSONObject();
  private static final String FOLDER_PATH = "json/";
  public static final String FILE_PATH = FOLDER_PATH + "map.json";
  public static final String FILE_EMPLOYEES_JSON = FOLDER_PATH + "app.Employees.json";
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.y HH:mm:ss z");
  public static final String FILE_GROUPS = FOLDER_PATH + "app.Groups.json";
  public static ApplicationContext context;

  public static JSONObject getFullJSON() {
    return fullJSON;
  }

  public static void setFullJSON(JSONObject fullJSON) {
    Options.fullJSON = fullJSON;
  }

}
