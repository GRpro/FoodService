package com.foodservice.dao;

import java.util.List;

/**
 *
 * @param <K> primary key
 * @param <E> entity
 */
public interface CRUD <K,E> {

    public K create(E object);
    public E get(K object);
    public List<E> getSome(Integer firstResult,Integer maxResults);
    public void update(E object);
    public void delete(E object);


}
