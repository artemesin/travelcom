package common.solutions.repo;

import cities.domain.City;

import java.util.Collection;

public  interface BaseRepo <T, ID> {
    void deleteById(ID id);

    void printAll();

    T findById(ID id, City[] cities);

    void update(T entity);

    T insert(T entity);

    void insert(Collection<T> items);
}
