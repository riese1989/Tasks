package Groups;

import General.JSONOperations;

import java.util.ArrayList;

public class OperationGroups  {
    private static GroupComparator groupComparator = new GroupComparator();
    private static ArrayList<Group> listGroups = nameGroups();

    public static ArrayList<Group> getListGroups() {
        listGroups.sort(groupComparator);
        return listGroups;
    }

    private static ArrayList<Group> nameGroups()    {
        ArrayList<Group> groupArrayList = null;
        try {
            JSONOperations jsonOperations = new JSONOperations();
            groupArrayList = jsonOperations.getGroupsFromJSON();
        }
        catch (Exception ex)    {
            System.out.println();
        }
        return groupArrayList;
    }
}
