package Repositories;

import java.util.ArrayList;

public interface Repo<T> {
    public boolean append(T t);
    public ArrayList<T> get();
}
