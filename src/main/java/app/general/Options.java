package app.general;

import app.config.Config;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import org.springframework.context.ApplicationContext;

public class Options {
  private static JSONObject fullJSON = new JSONObject();
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.y HH:mm:ss z");
  public static ApplicationContext context;

  public static JSONObject getFullJSON() {
    return fullJSON;
  }

  public static void setFullJSON(JSONObject fullJSON) {
    Options.fullJSON = fullJSON;
  }

}
