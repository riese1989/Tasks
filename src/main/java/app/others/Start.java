package app.others;

import app.config.FileConfig;
import java.io.IOException;

import app.Employees.OperationsEmployee;
import app.General.JSONOperations;
import app.General.Options;
import app.Menu.Menu;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private JSONOperations jsonOperations;
    private Menu menu;

    public Start(@Autowired JSONOperations jsonOperations, @Autowired Menu menu)  {
        this.jsonOperations = jsonOperations;
        this.menu = menu;
    }

    public void begin(ApplicationContext context) throws IOException, ParseException, java.text.ParseException {
        OperationsEmployee operationsEmployee = context.getBean(OperationsEmployee.class);
        FileConfig configuration = context.getBean(FileConfig.class);
        String filePath = configuration.getMapPath();
        operationsEmployee.readEmployeesFromJSON();
        jsonOperations.JSONToArrayTask();
        Options.setFullJSON(jsonOperations.getJSON(filePath));
        menu.menu();
    }
}
