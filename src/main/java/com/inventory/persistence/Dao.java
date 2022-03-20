package com.inventory.persistence;

import com.inventory.logic.DTO;

import java.util.ArrayList;

public interface Dao <T extends DTO> {

    boolean configure(String dbPath);

    T get(int id);

    ArrayList<T> getAll();

    boolean insert(T t);

    boolean update(T t, T updatedObject);

    boolean delete(int id);

}
