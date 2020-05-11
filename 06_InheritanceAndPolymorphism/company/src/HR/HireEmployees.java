package HR;

import java.util.ArrayList;

public class HireEmployees extends Company {
    public void hire(Employee employee) {
        employee.setNumber(list.size());
        list.add(employee);
    }

    public void hireAll(ArrayList<Employee> listEmployees) {
        for (Employee employee : listEmployees) {
            employee.setNumber(list.size());
            list.add(employee);
        }
    }
}
