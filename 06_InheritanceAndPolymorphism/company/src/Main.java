import HR.*;

public class Main {
    public static void main(String[] args) {
        int salaryOperator = 10000;
        int salaryManager = 30000;
        int salaryTopManager = 100000;
        Company company = new Company();
        Manager manager = new Manager(salaryManager);
        company.hire(manager);
        company.hireAll(manager, 100);
        company.fire(6);
        System.out.println(company.size());
    }
}
