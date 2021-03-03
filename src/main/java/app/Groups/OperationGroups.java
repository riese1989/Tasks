package app.Groups;

import app.General.JSONOperations;

import app.General.Options;
import java.util.ArrayList;

public class OperationGroups {

  private static GroupComparator groupComparator = new GroupComparator();
  private ArrayList<Group> listGroups = nameGroups();

  public ArrayList<Group> getListGroups() {
    listGroups.sort(groupComparator);
    return listGroups;
  }

  private ArrayList<Group> nameGroups() {
    ArrayList<Group> groupArrayList = null;
    try {
      JSONOperations jsonOperations = Options.context.getBean(JSONOperations.class);
      groupArrayList = jsonOperations.getGroupsFromJSON();
    } catch (Exception ex) {
      System.out.println();
    }
    return groupArrayList;
  }
}
