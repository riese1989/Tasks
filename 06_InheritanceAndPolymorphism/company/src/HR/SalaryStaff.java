package HR;

import java.util.Comparator;

public class SalaryStaff {
    public static Comparator <Employee> getTopSalaryStaff()     {
        Comparator comp = (Comparator<Employee>) (o1, o2) -> {
            if (o1.getMonthSalary() < o2.getMonthSalary())  {
                return 1;
            }
            if (o1.getMonthSalary() > o2.getMonthSalary())  {
                return -1;
            }
            return 0;
        };
        return comp;
    }

    public static Comparator <Employee> getLowestSalaryStaff()     {
        Comparator comp = (Comparator<Employee>) (o1, o2) -> {
            if (o1.getMonthSalary() < o2.getMonthSalary())  {
                return -1;
            }
            if (o1.getMonthSalary() > o2.getMonthSalary())  {
                return 1;
            }
            return 0;
        };
        return comp;
    }
}
