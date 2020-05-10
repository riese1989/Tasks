import HR.*;

public class Main {
    public static void main(String[] args) {
        int salaryOperator = 10000;
        int salaryManager = 30000;
        int salaryTopManager = 100000;
        int incomeCompany = 10000000;
        Company company = new Company();
        company.setIncome(incomeCompany);
        Employee manager = new Manager(salaryManager, company);
        Employee topManager = new TopManager(salaryTopManager, company);
        company.hireAll(manager,5);
        company.hireAll(topManager, 2);
        //company.fire(90);
        company.listEmpoeeys();
        company.getTopSalaryStaff(9);
        //company.listEmpoeeys();
    }
}
