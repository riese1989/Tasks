import HR.*;

public class Main {
    public static void main(String[] args) {
        int salaryOperator = 10000;
        int salaryManager = 30000;
        int salaryTopManager = 100000;
        int incomeCompany = 10000000;
        Company company = new Company();
        company.setIncome(incomeCompany);
        System.out.println(company.getIncome());
        TopManager topManager = new TopManager(salaryTopManager,company);
        company.hireAll(topManager, 5);
        Manager manager = new Manager(salaryManager, company);
        company.hireAll(manager,6);
        //company.fire(6);
        company.listEmpoeeys();
    }
}
