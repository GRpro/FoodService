package com.foodservice.services;

import com.foodservice.dao.SimpleUserDAO;
import com.foodservice.entities.data.SystemStatus;
import com.foodservice.entities.user.SimpleUser;
import com.foodservice.entities.data.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class SimpleUserService implements UserService<Integer, SimpleUser> {
    
    private SimpleUserDAO simpleUserDAO;

    @Autowired
    public void setSimpleUserDAO(SimpleUserDAO simpleUserDAO) {
        this.simpleUserDAO = simpleUserDAO;
    }

    @Override
    public Integer create(SimpleUser object) {
        object.setSystemStatus(SystemStatus.OFFLINE);
        object.setUserType(UserType.SIMPLE);
        return simpleUserDAO.create(object);
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleUser get(Integer key) {
        SimpleUser simpleUser = simpleUserDAO.get(key);
        return simpleUser;
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleUser getByEmail(String email) {
        SimpleUser simpleUser = simpleUserDAO.getByEmail(email);
        return simpleUser;
    }

    @Override
    public void update(SimpleUser object) {
        simpleUserDAO.update(object);
    }

    @Override
    public void delete(SimpleUser object) {
        simpleUserDAO.delete(object);
    }
}
