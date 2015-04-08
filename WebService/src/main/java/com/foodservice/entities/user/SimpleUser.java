package com.foodservice.entities.user;

import com.foodservice.entities.data.LazyClonable;
import com.foodservice.entities.Photo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "simple_user")
public class SimpleUser extends User implements LazyClonable<SimpleUser> {

    @OneToMany(targetEntity = Photo.class, fetch = FetchType.EAGER)
    @JoinTable(name = "simple_user_photo",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<Photo> photos = new ArrayList<>();

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public SimpleUser clone() {
        SimpleUser object = new SimpleUser();
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
        return object;
    }
}
