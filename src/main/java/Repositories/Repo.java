package Repositories;

import java.util.ArrayList;

public interface Repo<T> {
    public boolean add(T t);
    public ArrayList<T> get();
}
