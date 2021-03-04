package app.others;

import app.config.FileConfig;
import java.io.IOException;

import app.operations.OperationsEmployee;
import app.general.JSONOperations;
import app.general.Options;
import app.menu.Menu;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private JSONOperations jsonOperations;
    private Menu menu;
    private ApplicationContext context;

    public Start(@Autowired ApplicationContext context)  {
        this.jsonOperations = context.getBean(JSONOperations.class);
        this.menu = context.getBean(Menu.class);
        this.context = context;
    }

    public void begin() throws IOException, ParseException, java.text.ParseException {
        OperationsEmployee operationsEmployee = context.getBean(OperationsEmployee.class);
        FileConfig configuration = context.getBean(FileConfig.class);
        String filePath = configuration.getMapPath();
        operationsEmployee.readEmployeesFromJSON();
        jsonOperations.JSONToArrayTask();
        Options.setFullJSON(jsonOperations.getJSON(filePath));
        menu.menu();
    }
}
