package com.foodservice.services;

import com.foodservice.entities.data.SystemStatus;
import com.foodservice.entities.data.UserType;
import com.foodservice.entities.user.ShopAdminUser;
import com.foodservice.dao.ShopAdminUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ShopAdminUserService implements UserService<Integer, ShopAdminUser> {


    private ShopAdminUserDAO shopAdminUserDAO;

    @Autowired
    public void setShopAdminUserDAO(ShopAdminUserDAO shopAdminUserDAO) {
        this.shopAdminUserDAO = shopAdminUserDAO;
    }


    @Override
    public Integer create(ShopAdminUser object) {
        //code to check for right email
        object.setSystemStatus(SystemStatus.OFFLINE);
        object.setUserType(UserType.SHOP_ADMIN);
        return shopAdminUserDAO.create(object);
    }

    public Integer create(ShopAdminUser object, String shopAdminUserEmail) {
        //code to check for right email
        object.setSystemStatus(SystemStatus.OFFLINE);
        object.setUserType(UserType.SHOP_ADMIN);
        return shopAdminUserDAO.create(object);
    }

    @Override
    @Transactional(readOnly = true)
    public ShopAdminUser get(Integer key) {
        ShopAdminUser managerUser = shopAdminUserDAO.get(key);
        return managerUser;
    }

    @Override
    @Transactional(readOnly = true)
    public ShopAdminUser getByEmail(String email) {
        ShopAdminUser managerUser = shopAdminUserDAO.getByEmail(email);
        return managerUser;
    }

    @Override
    public void update(ShopAdminUser object) {
        shopAdminUserDAO.update(object);
    }

    @Override
    public void delete(ShopAdminUser object) {
        shopAdminUserDAO.delete(object);
    }
}
