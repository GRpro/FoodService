package com.foodservice.entities.user;

import com.foodservice.entities.data.LazyClonable;
import com.foodservice.entities.data.State;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "manager_user")
public class ManagerUser extends User implements LazyClonable<ManagerUser> {

    @Transient
    @ManyToOne(targetEntity = ShopAdminUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_admin_user_id", insertable = false, updatable = false)
    private ShopAdminUser shopAdminUser;

    /** foreign key */
    @Column(name = "shop_admin_user_id")
    private Integer shopAdminUserId;

    /**
     * Determines wether the shop administrator
     * accepts this manager as employee
     */
    @Enumerated(EnumType.STRING)
    private State state;


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getShopAdminUserId() {
        return shopAdminUserId;
    }

    public void setShopAdminUserId(Integer shopAdminUserId) {
        this.shopAdminUserId = shopAdminUserId;
    }

//    public ShopAdminUser getShopAdminUser() {
//        return shopAdminUser;
//    }
//
//    public void setShopAdminUser(ShopAdminUser shopAdminUser) {
//        this.shopAdminUser = shopAdminUser;
//    }

    @Override
    public ManagerUser clone() {
        ManagerUser object = new ManagerUser();
        object.setId(this.getId());
        object.setState(this.getState());
        object.setUserType(this.getUserType());
        object.setPassword(this.getPassword());
        object.setEmail(this.getEmail());
        object.setDob(this.getDob());
        object.setShopAdminUserId(this.getShopAdminUserId());
        object.setGender(this.getGender());
        object.setFirstName(this.getFirstName());
        object.setLastName(this.getLastName());
        object.setPersonalData(this.getPersonalData());
        object.setPhotoId(this.getPhotoId());
        object.setSystemStatus(this.getSystemStatus());
        return object;
    }
}
