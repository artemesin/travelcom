package common.solutions.service;




import common.buisness.exceptions.BasicTravelCheckedException;

import java.util.Collection;

public interface BaseService<T, ID> {

    T insert(T entity);

    void insert(Collection<T> items);

    void update(T entity);

    T findById(ID id);

    void delete(T entity) throws BasicTravelCheckedException;

    void deleteById(ID id);

    void printAll();

}
