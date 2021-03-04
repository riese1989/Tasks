package app.operations;

import app.general.JSONOperations;
import app.comparators.GroupComparator;
import app.pojo.Group;
import java.util.ArrayList;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class OperationGroups {

  private static GroupComparator groupComparator = new GroupComparator();
  private ArrayList<Group> listGroups;
  private ApplicationContext context;

  public OperationGroups(@Autowired ApplicationContext context)  {
    this.context = context;
    listGroups = nameGroups();
  }

  public ArrayList<Group> getListGroups() {
    listGroups.sort(groupComparator);
    return listGroups;
  }

  private ArrayList<Group> nameGroups() {
    ArrayList<Group> groupArrayList = null;
    try {
      JSONOperations jsonOperations = context.getBean(JSONOperations.class);
      groupArrayList = jsonOperations.getGroupsFromJSON();
    } catch (Exception ex) {
      System.out.println();
    }
    return groupArrayList;
  }
}
