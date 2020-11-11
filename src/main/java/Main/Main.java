package Main;

import java.io.IOException;
import java.text.SimpleDateFormat;

import Employees.OperationsEmployee;
import General.JSONOperations;
import Menu.Menu;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Main {
    public static JSONObject fullJSON = new JSONObject();
    public static String filePath = "map.json";
    public static String filePathVacations = "vacations.json";
    public static String fileEmployees = "Employees.txt";
    public static String fileEmployeesJSON = "Employees.json";
    public static SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.y HH:mm:ss z");

    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        OperationsEmployee.readEmployeesFromJSON(fileEmployees);
        JSONOperations.JSONToArrayTask();
        fullJSON = JSONOperations.getJSON(filePath);
        Menu menu = new Menu();
        menu.menu();
    }
}
