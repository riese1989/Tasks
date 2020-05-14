import HR.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int salaryOperator = 10000;
        int salaryManager = 30000;
        int salaryTopManager = 100000;
        int incomeCompany = 10000000;
        int countOperator = 180;
        int countManager = 200;
        int countTopManager = 25;
        Company company = new Company();
        company.setIncome(incomeCompany);
        ArrayList <Employee> listHireAll = new ArrayList<>();
        for (int i = 1; i<=countOperator; i++)  {
            listHireAll.add(new Operator(salaryOperator, company));
        }
        for (int i = 1; i<=countManager; i++)  {
            listHireAll.add(new Manager(salaryManager, company));
        }
        for (int i = 1; i<=countTopManager; i++)  {
            listHireAll.add(new TopManager(salaryTopManager, company));
        }
        company.hireAll(listHireAll);
        company.fire(90);
        company.getTopSalaryStaff(5);
        System.out.println("==============");
        company.getLowestSalaryStaff(5);
        Manager manager = new FunctionManager (salaryManager, company);
    }
}
