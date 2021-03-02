package app.others;

import app.config.FileConfig;
import java.io.IOException;

import app.Employees.OperationsEmployee;
import app.General.JSONOperations;
import app.General.Options;
import app.Menu.Menu;
import org.json.simple.parser.ParseException;
import org.springframework.context.ApplicationContext;

public class Start {

    public void begin(ApplicationContext context) throws IOException, ParseException, java.text.ParseException {
        OperationsEmployee operationsEmployee = context.getBean(OperationsEmployee.class);
        FileConfig configuration = context.getBean(FileConfig.class);
        String filePath = configuration.getMapPath();
        operationsEmployee.readEmployeesFromJSON();
        JSONOperations.JSONToArrayTask();
        Options.setFullJSON(JSONOperations.getJSON(filePath));
        Menu.menu();
    }
}
