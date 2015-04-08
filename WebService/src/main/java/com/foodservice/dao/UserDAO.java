package com.foodservice.dao;

public interface UserDAO<K,E> extends CRUD<K,E>{
    public E getByEmail(String email);
}
