package com.foodservice.entities;

import com.foodservice.entities.data.LazyClonable;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "eating_table")
public class Table implements LazyClonable<Table> {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private Integer size;
    private String description;

    @Transient
    @ManyToOne(targetEntity = Shop.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private Shop shop;

    /** foreign key */
    @Column(name = "shop_id")
    private Integer shopId;

    @Transient
    @OneToOne(targetEntity = Photo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private Photo photo;

    /** foreign key */
    @Column(name = "photo_id")
    private Integer photoId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Shop getShop() {
//        return shop;
//    }
//
//    public void setShop(Shop shop) {
//        this.shop = shop;
//    }

//    public Photo getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(Photo photo) {
//        this.photo = photo;
//    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        if (id != null ? !id.equals(table.id) : table.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", size=" + size +
                ", description='" + description + '\'' +
                ", shopId=" + shopId +
                ", photoId=" + photoId +
                '}';
    }

    @Override
    public Table clone() {
        Table table = new Table();
        table.setId(this.getId());
        table.setDescription(this.getDescription());
        table.setPhotoId(this.getPhotoId());
        table.setShopId(this.getShopId());
        table.setSize(this.getSize());
        return table;
    }
}
