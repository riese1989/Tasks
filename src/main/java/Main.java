
import java.io.IOException;
import java.text.SimpleDateFormat;

import Employees.OperationsEmployee;
import General.JSONOperations;
import General.Options;
import Menu.Menu;
import org.json.simple.parser.ParseException;

public class Main {


    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        String filePath = Options.FILE_PATH;
        OperationsEmployee.readEmployeesFromJSON();
        JSONOperations.JSONToArrayTask();
        Options.setFullJSON(JSONOperations.getJSON(filePath));
        Menu.menu();
    }
}
