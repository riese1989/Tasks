package app.repositories;

import app.general.JSONOperations;
import app.pojo.Iniciator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class RepoInic implements Repo<Iniciator> {
  private LinkedHashSet<Iniciator> iniciators = new LinkedHashSet<>();
  private ApplicationContext context;

  public RepoInic(@Autowired ApplicationContext context)  {
    this.context = context;
  }

  @Override
  public boolean append(Iniciator iniciator) {
    iniciators.add(iniciator);
    return true;
  }

  @Override
  public ArrayList<Iniciator> get() {
    return new ArrayList<>(iniciators);
  }

  @Override
  public int size() {
    return iniciators.size();
  }

  public Iniciator getIniciatorRepo(String name) {
    String newFamilyInic = returnNewSirname(name);
    for (Iniciator iniciator : iniciators)  {
      if (iniciator.getName().equals(name) || iniciator.getName().equals(newFamilyInic)) {
        if (newFamilyInic != null)  {
          iniciator.setField("name", newFamilyInic);
        }
        return iniciator;
      }
    }
    return context.getBean(Iniciator.class);
  }

  public String returnNewSirname(String name) {
    JSONOperations jsonOperations = context.getBean(JSONOperations.class);
    HashMap<String, String> oldSirname = new HashMap<>();
    try {
      oldSirname = jsonOperations.getFamilies();
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    return oldSirname.get(name);
  }
}
