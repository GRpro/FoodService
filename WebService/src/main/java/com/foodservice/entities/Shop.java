package com.foodservice.entities;

import com.foodservice.entities.data.LazyClonable;
import com.foodservice.entities.user.ShopAdminUser;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = {"shopAdminUser", "photos"})
@Entity
@javax.persistence.Table(name = "shop")
public class Shop implements LazyClonable<Shop> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Embedded
    private Location location;

    @Lob
    private String description;


    @Transient
    @ManyToOne(targetEntity = ShopAdminUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_admin_user_id", insertable = false, updatable = false)
    private ShopAdminUser shopAdminUser;

    /** foreign key */
    @Column(name = "shop_admin_user_id")
    private Integer shopAdminUserId;

    @OneToMany(targetEntity = Photo.class, fetch = FetchType.EAGER)
    @JoinTable(name = "shop_photo",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<Photo> photos = new ArrayList<>();


    /** foreign key without any checking */
    @Column(name = "primary_photo_id")
    private Integer primaryPhotoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPrimaryPhotoId() {
        return primaryPhotoId;
    }

    public void setPrimaryPhotoId(Integer primaryPhotoId) {
        this.primaryPhotoId = primaryPhotoId;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (id != shop.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "primaryPhotoId=" + primaryPhotoId +
                ", shopAdminUserId=" + shopAdminUserId +
                ", description='" + description + '\'' +
                ", location=" + location +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public Shop clone() {
        Shop shop = new Shop();
        shop.setId(getId());
        shop.setDescription(getDescription());
        shop.setLocation(getLocation());
        shop.setPrimaryPhotoId(getPrimaryPhotoId());
        shop.setShopAdminUserId(getShopAdminUserId());
        shop.setName(getName());
        return shop;
    }
}
