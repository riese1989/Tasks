package app.repositories;

import java.util.ArrayList;
import java.util.Collection;

public interface Repo<T> {

  public boolean append(T t);

  public ArrayList<T> get();

  public int size();
}
