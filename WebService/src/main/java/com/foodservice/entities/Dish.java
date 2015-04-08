package com.foodservice.entities;


import com.foodservice.entities.data.LazyClonable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "dish")
public class Dish implements LazyClonable<Dish> {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String name;
    private Float price;
    private String type;
    private String description;

    @Transient
    @ManyToOne(targetEntity = Shop.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private Shop shop;

    /** foreign key */
    @Column(name = "shop_id")
    private Integer shopId;

    @Transient
    @OneToMany(targetEntity = Photo.class, fetch = FetchType.LAZY)
    @JoinTable(name = "dish_photo",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Photo> photos = new ArrayList<>();

    /** foreign key without any checking */
    @Column(name = "primary_photo_id")
    private Integer primaryPhotoId;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Shop getShop() {
//        return shop;
//    }
//
//    public void setShop(Shop shop) {
//        this.shop = shop;
//    }

    public Integer getPrimaryPhotoId() {
        return primaryPhotoId;
    }

    public void setPrimaryPhotoId(Integer primaryPhotoId) {
        this.primaryPhotoId = primaryPhotoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (id != null ? !id.equals(dish.id) : dish.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", shopId=" + shopId +
                ", photos=" + photos +
                '}';
    }

    @Override
    public Dish clone() {
        Dish dish = new Dish();
        dish.setId(this.getId());
        dish.setDescription(this.getDescription());
        dish.setName(this.getName());
        dish.setPrice(this.getPrice());
        dish.setPrimaryPhotoId(this.getPrimaryPhotoId());
        dish.setShopId(this.getShopId());
        dish.setType(this.getType());
        return dish;
    }
}
