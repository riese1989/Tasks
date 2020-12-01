package Groups;

public class Group {
    private EnumGroups enumGroups;
    private String name;

    public Group(EnumGroups enumGroups, String name) {
        this.enumGroups = enumGroups;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
