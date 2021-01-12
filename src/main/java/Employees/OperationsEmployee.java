package Employees;

import General.JSONOperations;
import Tasks.OperationsTask;
import Tasks.Task;
import Tasks.TaskStatus;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Stream;

import static Employees.Employee.listEmployees;
import static General.Operations.compareDate;

public class OperationsEmployee {

    public Integer getCounter()  {
        return counter();
    }

    public boolean readEmployeesFromJSON() {
        try {
            JSONOperations.JSONToArrayEmployee();
            return true;
        }
        catch (ParseException | java.text.ParseException | IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Integer counter() {
        Integer count = 0;
        Employee empl = getEmployee("pestov");
        for (Task task : Employee.listTasks) {
            if (task.getAssigned() == empl && compareDate(task.getDateResolved()) && task.getStatus() != TaskStatus.NOTE_DONE) {
                count++;
            }
        }
        return count;
    }

    public Employee getEmployee  (String family) {
        for (Employee employee : listEmployees) {
            if (employee.getFamily().equals(family))    {
                return employee;
            }
        }
        return new Employee(0, family, null, true);
    }

    public boolean stat() {
        System.out.println("\nСегодняшний счёт");
        for (Employee employee : listEmployees) {
            if(employee.getStatus()) {
                long count = countAssignTaskNow(employee);
                System.out.println(employee.getFamily() + " " + count);
            }
        }
        System.out.println("\nОбщий счёт");
        for (Employee employee : listEmployees) {
            long countTasks = Employee.listTasks.stream().filter(t -> t.getAssigned().getFamily().equals(employee.getFamily())).count();
            System.out.println(employee.getFamily() + " " + countTasks);
        }
        System.out.println("\nВсего назначено обращений " + Employee.listTasks.size() + "\n");
        return true;
    }

    private long countAssignTaskNow(Employee employee)    {
        long count = 0;
        OperationsTask operationsTask = new OperationsTask();
        for(Task task : Employee.listTasks)    {
            if (task.getAssigned() == employee && operationsTask.searchInHistory(task))    {
                count++;
            }
        }
        return count;
    }

    public Employee tasksOfAuthor(String author) {
        for (Employee employee : listEmployees) {
            Long count = Employee.listTasks.stream().filter(t -> t.getAssigned() == employee && t.getAuthor().equals(author)).count();
            employee.setCountTasksOfAuthor(count);
        }
        Stream<Employee> stream = listEmployees.stream();
        Employee empl = stream.max(Comparator.comparing(Employee::getCountTasksOfAuthor)).get();
        return empl;
    }
}
