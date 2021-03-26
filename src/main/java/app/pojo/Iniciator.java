package app.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Iniciator implements Pojo<Iniciator> {

  private String name;
  private int countTasks = 0;
  public static ArrayList<Iniciator> listIniciators = new ArrayList<>();

  @Override
  public boolean equals(Object obj) {
    Iniciator iniciator = (Iniciator) obj;
    return name.equals(iniciator.getName());
  }

  public void incCountTasks() {
    countTasks++;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCountTasks() {
    return countTasks;
  }

  public static Iniciator searchIniciator(String name) {
    String newFamilyInic = returnNewSirname(name);
    String oldNameInic;
    for (Iniciator iniciator : listIniciators) {
      oldNameInic = iniciator.getName();
      if (oldNameInic.equals(name) || oldNameInic.equals(newFamilyInic)) {
        if (newFamilyInic != null) {
          iniciator.setName(newFamilyInic);
        }
        return iniciator;
      }
    }
    return null;
  }


  public static String returnNewSirname(String name) {
    HashMap<String, String> oldSirname = new HashMap<>();
    oldSirname.put("SvFedorova", "Svetlana.Kostikova");
    oldSirname.put("Olga.Frolkova", "Olga.HernandezCruz");
    return oldSirname.get(name);
  }

  private Iniciator circleInic(String name) {
    for (Iniciator iniciator : listIniciators) {
      if (iniciator.getName().equals(name)) {
        return iniciator;
      }
    }
    return null;
  }

  @Override
  public Iniciator setField(String field, Object value) {
    switch (field)  {
      case "name":  {
        name = (String) value;
      }
    }
    return this;
  }
}
