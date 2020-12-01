package Groups;

import java.util.ArrayList;
import java.util.Collections;

public class OperationGroups  {
    private static GroupComparator groupComparator = new GroupComparator();
    private static ArrayList<Group> listGroups = nameGroups();

    public static ArrayList<Group> getListGroups() {
        listGroups.sort(groupComparator);
        return listGroups;
    }

    private static ArrayList<Group> nameGroups()    {
        ArrayList<Group> groupArrayList = new ArrayList<>();
        groupArrayList.add(new Group(EnumGroups.CREDENTIALS_1HD, "1-HD Полномочия"));
        groupArrayList.add(new Group(EnumGroups.SAP_SM_2, "2-Поддержка SAP SM"));
        groupArrayList.add(new Group(EnumGroups.JIRA_3, "3-Поддержка Jira"));
        groupArrayList.add(new Group(EnumGroups.CO_1, "2-ЦО Х5"));
        groupArrayList.add(new Group(EnumGroups.COD_2, "2-Инфраструктурные сервисы ЦОД"));
        return groupArrayList;
    }
}
