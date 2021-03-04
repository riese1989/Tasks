package app.repositories;

import app.pojo.Group;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class RepoGroups implements Repo<Group> {

  List<Group> groups = new ArrayList<>();

  @Override
  public boolean append(Group group) {
    groups.add(group);
    return true;
  }

  @Override
  public ArrayList<Group> get() {
    return (ArrayList<Group>) groups;
  }

  @Override
  public int size() {
    return groups.size();
  }
}
