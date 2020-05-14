package HR;

public class FunctionManager extends Manager {
    public FunctionManager(int salary, Company company) {
        super(salary, company);
    }
    public Manager createManager (int salary, Company company)  {
        return new Manager (salary, company);
    }
}
