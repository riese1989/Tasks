package HR;

import java.util.Comparator;

public class SalaryStaff {
    public static Comparator <Employee> getTopSalaryStaff()     {
        Comparator comp = (Comparator<Employee>) (o1, o2) -> {
            if (o1.getMonthSalary() < o2.getMonthSalary())  {
                System.out.println("нет");
                return -1;
            }
            if (o1.getMonthSalary() > o2.getMonthSalary())  {
                System.out.println("да");
                return 1;
            }
            return 0;
        };
        return comp;
    }
}
