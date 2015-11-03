package com.project.database.dao;

import java.util.List;

/**
 * Created by jedaka on 03.11.2015.
 */
public interface GenericDAO<T> {

    boolean save(T object);
    boolean update(T object);
    boolean delete(T object);
    List<T> getAll();
    T getById(int id);
    T getByPK(String PK);

}
