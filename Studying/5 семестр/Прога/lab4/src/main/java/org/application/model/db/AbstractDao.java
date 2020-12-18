package org.application.model.db;

import java.util.List;

public interface AbstractDao<T, K> {

    void save(T t);

    T getByID(K k);

    List<T> getAll();

    void deleteById(K id);

    void update(T t);

    void close();
}
