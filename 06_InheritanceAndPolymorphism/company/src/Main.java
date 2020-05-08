import HR.*;

public class Main {
        public static void main(String[] args) {
        Company company = new Company();
        Manager manager = new Manager();
        company.hire(manager);
        company.hireAll(manager,5);
        System.out.println(company.size());
    }
}
