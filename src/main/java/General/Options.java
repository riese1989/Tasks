package General;

import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;

public class Options {
    private static JSONObject fullJSON = new JSONObject();
    private static String filePath = "map.json";
    private static String filePathVacations = "vacations.json";
    private static String fileEmployees = "Employees.txt";
    private static String fileEmployeesJSON = "Employees.json";
    private static SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.y HH:mm:ss z");

    public static JSONObject getFullJSON() {
        return fullJSON;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static String getFilePathVacations() {
        return filePathVacations;
    }

    public static String getFileEmployees() {
        return fileEmployees;
    }

    public static String getFileEmployeesJSON() {
        return fileEmployeesJSON;
    }

    public static SimpleDateFormat getFormatDate() {
        return formatDate;
    }

    public static void setFullJSON(JSONObject fullJSON) {
        Options.fullJSON = fullJSON;
    }
}
