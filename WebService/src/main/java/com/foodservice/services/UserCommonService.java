package com.foodservice.services;

import com.foodservice.dao.UserCommonDAO;
import com.foodservice.entities.data.SystemStatus;
import com.foodservice.entities.user.User;
import com.foodservice.entities.data.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserCommonService {

    private UserCommonDAO userCommonDAO;

    @Autowired
    public void setUserCommonDAO(UserCommonDAO userCommonDAO) {
        this.userCommonDAO = userCommonDAO;
    }

    public SystemStatus[] getSystemStatus(Integer[] identifiers) {
        return userCommonDAO.getSystemStatus(identifiers);
    }

    public boolean changeSystemStatus(Integer id, SystemStatus status) {
        return userCommonDAO.changeSystemStatus(id, status);
    }

    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userCommonDAO.getByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<User> getMessageRelatedUsers(Integer id, int firstResult, int maxResults, UserType userType) {
        return userCommonDAO.getMessageRelatedUsers(id, firstResult, maxResults, userType);
    }
}
