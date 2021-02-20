package Inic;

import java.util.Comparator;

public class IniciatorComparator implements Comparator<Iniciator> {

  @Override
  public int compare(Iniciator o1, Iniciator o2) {
    if (o1.getCountTasks().equals(o2.getCountTasks())) {
      return 0;
    }
    if (o1.getCountTasks() < o2.getCountTasks()) {
      return 1;
    } else {
      return -1;
    }
  }
}
