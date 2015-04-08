package com.foodservice.services;

import com.foodservice.entities.user.User;

public interface UserService<K, E extends User> {
    
    public K create(E object);
    public E get(K key);
    public E getByEmail(String email);
    public void update(E object);
    public void delete(E object);
}
