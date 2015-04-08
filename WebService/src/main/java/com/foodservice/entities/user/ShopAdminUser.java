package com.foodservice.entities.user;

import com.foodservice.entities.data.LazyClonable;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@javax.persistence.Table(name = "shop_admin_user")
public class ShopAdminUser extends User implements LazyClonable<ShopAdminUser> {

    @Lob
    private String contactData;

    public String getContactData() {
        return contactData;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }

    @Override
    public ShopAdminUser clone() {
        ShopAdminUser object = new ShopAdminUser();
        object.setId(this.getId());
        object.setUserType(this.getUserType());
        object.setPassword(this.getPassword());
        object.setEmail(this.getEmail());
        object.setDob(this.getDob());
        object.setGender(this.getGender());
        object.setFirstName(this.getFirstName());
        object.setLastName(this.getLastName());
        object.setPersonalData(this.getPersonalData());
        object.setPhotoId(this.getPhotoId());
        object.setSystemStatus(this.getSystemStatus());
        object.setContactData(this.getContactData());
        return object;
    }
}
