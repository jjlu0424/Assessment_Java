package com.inventory.persistence;

import java.util.ArrayList;
import java.util.List;

public interface Dao <T> {

    boolean configure(String dbPath);

    T get(int id);

    ArrayList<T> getAll();

    boolean insert(T t);

    boolean update(T t, T updatedObject);

    boolean delete(int id);

}
