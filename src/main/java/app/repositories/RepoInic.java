package app.repositories;

import app.pojo.Iniciator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
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
    for (Iniciator iniciator : iniciators)  {
      if (iniciator.getName().equals(name)) {
        return iniciator;
      }
    }
    return context.getBean(Iniciator.class);
  }
}
