package Employees;

import General.JSONOperations;
import Main.Main;
import Tasks.Task;
import Tasks.TaskStatus;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Stream;

import static Employees.Employee.listEmployees;
import static General.Operations.compareDate;
import static Tasks.OperationsTask.searchInHistory;

public class OperationsEmployee {

    public static Integer getCounter()  {
        return counter();
    }

    public static void readEmployeesFromJSON(String fileEmployees) throws ParseException, java.text.ParseException, IOException {
        JSONOperations.JSONToArrayEmployee();
    }

    private static Integer counter() {
        Integer count = 0;
        Employee empl = getEmployee("pestov");
        for (Task task : Employee.listTasks) {
            if (task.getAssigned() == empl && compareDate(task.getDateResolved()) && task.getStatus() != TaskStatus.NOTE_DONE) {
                count++;
            }
        }
        return count;
    }

    public static Employee getEmployee  (String family) {
        for (Employee employee : listEmployees) {
            if (employee.getFamily().equals(family))    {
                return employee;
            }
        }
        return new Employee(0, family, null, true);
    }

    public static void stat() {
        System.out.println("\nСегодняшний счёт");
        for (Employee employee : listEmployees) {
            long count = countAssignTaskNow(employee);
            System.out.println(employee.getFamily() + " " + count);
        }
        System.out.println("\nОбщий счёт");
        for (Employee employee : listEmployees) {
            long countTasks = Employee.listTasks.stream().filter(t -> t.getAssigned().getFamily().equals(employee.getFamily())).count();
            System.out.println(employee.getFamily() + " " + countTasks);
        }
        System.out.println("\nВсего назначено обращений " + Employee.listTasks.size() + "\n");
    }

    private static long countAssignTaskNow(Employee employee)    {
        long count = 0;
        for(Task task : Employee.listTasks)    {
            if (task.getAssigned() == employee && searchInHistory(task))    {
                count++;
            }
        }
        return count;
    }

    public static Employee tasksOfAuthor(String author) {
        for (Employee employee : listEmployees) {
            Long count = Employee.listTasks.stream().filter(t -> t.getAssigned() == employee && t.getAuthor().equals(author)).count();
            employee.setCountTasksOfAuthor(count);
        }
        Stream<Employee> stream = listEmployees.stream();
        Employee empl = stream.max(Comparator.comparing(Employee::getCountTasksOfAuthor)).get();
        return empl;
    }
}
