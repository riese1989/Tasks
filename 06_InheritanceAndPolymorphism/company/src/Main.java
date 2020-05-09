import HR.*;

public class Main {
    public static void main(String[] args) {
        int salaryOperator = 10000;
        int salaryManager = 30000;
        int salaryTopManager = 100000;
        int incomeCompany = 10000000;
        Company company = new Company();
        company.setIncome(incomeCompany);
        Manager manager = new Manager(salaryManager, company);
        company.hireAll(manager,1);
        //company.fire(6);
        company.listEmpoeeys();
    }
}
